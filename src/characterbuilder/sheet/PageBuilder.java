package characterbuilder.sheet;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import javax.swing.JLabel;

public class PageBuilder {

    private static final int PAGE_WIDTH = 595;
    private static final int PAGE_HEIGHT = 842;
    private static final int MARGIN = 30;

    public int getWidth(int zoom) {
        return PAGE_WIDTH * zoom;
    }

    public int getHeight(int zoom) {
        return PAGE_HEIGHT * zoom;
    }

    public enum Align {
        TOP_LEFT(0, 0),
        CENTRE_LEFT(0, 1),
        BOTTOM_LEFT(0, 2),
        TOP_CENTRE(1, 0),
        CENTRE(1, 1),
        BOTTOM_CENTRE(1, 2),
        TOP_RIGHT(2, 0),
        CENTRE_RIGHT(2, 1),
        BOTTOM_RIGHT(2, 2);

        private final IntBinaryOperator xCalc;
        private final IntBinaryOperator yCalc;

        private Align(int xMove, int yMove) {
            this.xCalc = (x, w) -> x - w * xMove / 2;
            this.yCalc = (y, h) -> y - h * yMove / 2;
        }

        public int horz(int x, int width) {
            return xCalc.applyAsInt(x, width);
        }

        public int vert(int y, int height) {
            return yCalc.applyAsInt(y, height);
        }
    }

    public abstract class Component {

        private Optional<Container> parent = Optional.empty();

        public abstract void paint(Graphics2D g, int zoom);

        protected int x(int zoom) {
            return parent.get().x(zoom);
        }

        protected int y(int zoom) {
            return parent.get().y(zoom);
        }
    }

    public class Container extends Component {

        protected final int xp;
        protected final int yp;
        private final List<Component> children = new ArrayList<>();

        private Container(int xp, int yp) {
            this.xp = xp;
            this.yp = yp;
        }

        public Container with(Component... children) {
            for (Component child : children) {
                this.children.add(child);
                child.parent = Optional.of(this);
            }
            return this;
        }

        @Override
        protected int x(int zoom) {
            return super.x(zoom) + horz(xp, zoom);
        }

        @Override
        protected int y(int zoom) {
            return super.y(zoom) + vert(yp, zoom);
        }

        @Override
        public void paint(Graphics2D g, int zoom) {
            children.forEach(child -> child.paint(g, zoom));
        }
    }

    public Container blank(int xp, int yp) {
        return new Container(xp, yp);
    }

    public Container page() {
        return new Container(0, 0) {
            @Override
            public void paint(Graphics2D g, int zoom) {
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(zoom), getHeight(zoom));
                super.paint(g, zoom);
            }

            @Override
            protected int y(int zoom) {
                return MARGIN * zoom;
            }

            @Override
            protected int x(int zoom) {
                return MARGIN * zoom;
            }
        };
    }

    public Container borderedSection(int xp, int yp, int wp, int hp) {
        return new Container(xp, yp) {
            @Override
            public void paint(Graphics2D g, int z) {
                Shape inner = rect(4, 4, z);
                Shape outer = rect(2, 30, z);
                g.setColor(Color.WHITE);
                g.fill(inner);
                g.fill(outer);
                super.paint(g, z);
                g.setColor(Color.BLACK);
                g.setStroke(new BasicStroke(1.4f * z));
                g.draw(outer);
                g.setStroke(new BasicStroke(0.8f * z));
                g.draw(inner);
            }

            private Shape rect(float gap, float corner, int zoom) {
                return new RoundRectangle2D.Float(x(zoom) + gap * zoom, y(zoom) + gap * zoom,
                    horz(wp, zoom) - gap * zoom * 2, vert(hp, zoom) - gap * zoom * 2,
                    corner * zoom, corner * zoom);
            }
        };
    }

    public Container shadedSection(int xp, int yp, int wp, int hp) {
        return new Container(xp, yp) {
            @Override
            public void paint(Graphics2D g, int z) {
                g.setColor(new Color(200, 200, 200));
                g.fillRoundRect(x(z), y(z), horz(wp, z), vert(hp, z), 5 * z, 5 * z);
                super.paint(g, z);
            }
        };
    }

    public Component verticalLine(int xp, int hp) {
        return new Component() {
            @Override
            public void paint(Graphics2D g, int z) {
                g.setColor(Color.LIGHT_GRAY);
                g.setStroke(new BasicStroke(z));
                g.drawLine(x(z) + horz(xp, z), y(z), x(z) + horz(xp, z), y(z) + vert(hp, z));
            }
        };
    }

    private Component textComponent(String text, Function<Integer, Font> fontGenerator,
        int xp, int yp, int yOffset, Align align) {
        return new Component() {
            @Override
            public void paint(Graphics2D g, int zoom) {
                String[] lines = text.split("\\n");
                Font font = fontGenerator.apply(zoom);
                int textHeight = g.getFontMetrics(font).getHeight() * 15 / 24;
                for (int i = 0; i < lines.length; i++) {
                    int textWidth = g.getFontMetrics(font).stringWidth(lines[i]);
                    g.setColor(Color.BLACK);
                    g.setFont(font);
                    g.drawString(lines[i],
                        align.horz(x(zoom) + horz(xp, zoom), textWidth),
                        align.vert(y(zoom) + vert(yp, zoom) + textHeight * (i + 1),
                            textHeight * lines.length) + yOffset * zoom);
                }
            }
        };
    }

    public Component caption(String text, int xp, int yp, Align align) {
        return textComponent(text, z -> new Font("optima", Font.BOLD, 11 * z), xp, yp, 0, align);
    }

    public Component writing(String text, int xp, int yp, int wp, int hp) {
        return new Component() {
            @Override
            public void paint(Graphics2D g, int zoom) {
                BufferedImage image = new BufferedImage(horz(wp, zoom), vert(hp, zoom),
                    BufferedImage.TYPE_INT_ARGB);
                JLabel label = label(text, zoom);
                label.setSize(new Dimension(horz(wp, zoom), vert(hp, zoom)));
                label.validate();
                label.print(image.getGraphics());
                g.drawImage(image, x(zoom) + horz(xp, zoom), y(zoom) + vert(yp, zoom), null);
            }
        };
    }

    private JLabel label(String text, int zoom) {
        JLabel label = new JLabel();
        label.setVerticalAlignment(JLabel.TOP);
        label.setFont(new Font("optima", Font.PLAIN, 10 * zoom));
        label.setForeground(Color.BLACK);
        label.setText(text);
        return label;
    }

    public Component value(String text, int xp, int yp, Align align) {
        return textComponent(text, z -> new Font("optima", Font.BOLD, 18 * z), xp, yp, 0, align);
    }

    public Component value(int value, int xp, int yp, Align align) {
        return value(intToString(value), xp, yp, align);
    }

    private String intToString(int value) {
        return NumberFormat.getNumberInstance().format(value);
    }

    public Component field(String label, int value, int xp, int yp) {
        return field(label, intToString(value), xp, yp);
    }

    public Component field(String label, Object value, int xp, int yp) {
        return new Container(xp, yp)
            .with(textComponent(label.toUpperCase(),
                z -> new Font("helvetical", Font.PLAIN, 9 * z),
                xp - 1, yp, 1, Align.CENTRE_RIGHT))
            .with(textComponent(value.toString(),
                z -> new Font("optima", Font.BOLD, 11 * z),
                xp + 1, yp, 0, Align.CENTRE_LEFT));
    }

    public Component circle(int xp, int yp, int rp) {
        return new Component() {
            @Override
            public void paint(Graphics2D g, int z) {
                int radius = horz(rp, z);
                g.setColor(Color.WHITE);
                g.fill(circle(radius, z));
                g.setColor(Color.LIGHT_GRAY);
                g.setStroke(new BasicStroke(2f * z));
                g.draw(circle(radius - 2 * z, z));
                g.setColor(Color.BLACK);
                g.setStroke(new BasicStroke(1f * z));
                g.draw(circle(radius, z));
            }

            private Shape circle(int r, int z) {
                return new Ellipse2D.Float(x(z) + horz(xp, z) - r, y(z) + vert(yp, z) - r, 2 * r, 2 * r);
            }
        };
    }

    private int horz(int xp, int z) {
        return z * xp * (PAGE_WIDTH - 2 * MARGIN) / 100;
    }

    private int vert(int yp, int z) {
        return z * yp * (PAGE_HEIGHT - 2 * MARGIN) / 100;
    }
}
