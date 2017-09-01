package characterbuilder.ui.character;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.Picture;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXParseException;

public class PicturePanel extends CharacterSubPanel {

    private static final int IMAGE_WIDTH = 100;
    private static final int IMAGE_HEIGHT = 150;
    private final JLabel imageLabel = new JLabel();
    private final JButton chooseButton;
    private final JButton pasteButton;

    public static class ImagePicture extends Picture {

        private String name;
        private final BufferedImage image;

        public ImagePicture(String name, BufferedImage image) {
            this.image = image;
            setName(name);
        }

        public final void setName(String name) {
            this.name = name.toLowerCase().replaceAll("\\s+", "_").replaceAll("\\W", "");
        }

        public Image getImage() {
            return image;
        }

        @Override
        public Element save(Document document) {
            try {
                ImageIO.write(image, "png", file(name));
                Element imageElement = getType().save(document);
                imageElement.setTextContent(name);
                return imageElement;
            } catch (IOException ex) {
                throw new IllegalStateException();
            }
        }

        public static File file(String name) {
            return new File("characters/" + name + "_portrait.png");
        }
    }

    static {
        Picture.setLoader(PicturePanel::loadImagePicture);
    }

    public PicturePanel(CharacterUpdater updater) {
        super("Picture", new BorderLayout(), updater);
        JToolBar tools = new JToolBar();
        add(tools, BorderLayout.NORTH);
        chooseButton = tools.add(choose());
        pasteButton = tools.add(paste());
        setMaximumSize(new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT));
        add(imageLabel, BorderLayout.CENTER);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        imageLabel.setBorder(BorderFactory.createEtchedBorder());
        chooseButton.setEnabled(false);
        pasteButton.setEnabled(false);
    }

    private Action choose() {
        return new AbstractAction("Choose") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "png"));
                if (fileChooser.showOpenDialog(PicturePanel.this) == JFileChooser.APPROVE_OPTION) {
                    try {
                        showImage(ImageIO.read(fileChooser.getSelectedFile()));
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(PicturePanel.this, "Could not open file",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        };
    }

    private Action paste() {
        return new AbstractAction("Paste") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                try {
                    showImage((Image) clipboard.getContents(null)
                        .getTransferData(DataFlavor.imageFlavor));
                } catch (UnsupportedFlavorException | IOException ex) {
                    JOptionPane.showMessageDialog(PicturePanel.this, "No clipboard image",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
    }

    private void showImage(Image image) {
        BufferedImage scaledImage = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, TYPE_INT_ARGB);
        int width = image.getWidth(this);
        int height = image.getHeight(this);
        if ((float) width / height > (float) IMAGE_WIDTH / IMAGE_HEIGHT) {
            height = height * IMAGE_WIDTH / width;
            width = IMAGE_WIDTH;
        } else {
            width = width * IMAGE_HEIGHT / height;
            height = IMAGE_HEIGHT;
        }
        scaledImage.createGraphics().drawImage(image, 0, 0, width, height, null);
        imageLabel.setIcon(new ImageIcon(scaledImage));
        getCharacter().removeAttributesOfType(AttributeType.PICTURE);
        getCharacter().addAttribute(
            new ImagePicture(getCharacter().getStringAttribute(AttributeType.NAME), scaledImage));
        triggerChange();
    }

    @Override
    protected void updateCharacter(Character character) {
        super.updateCharacter(character);
        chooseButton.setEnabled(character != null);
        pasteButton.setEnabled(character != null);
        if (character.hasAttribute(AttributeType.PICTURE)) {
            ImagePicture picture = character.getAttribute(AttributeType.PICTURE);
            imageLabel.setIcon(new ImageIcon(picture.image));
        } else {
            imageLabel.setIcon(null);
        }
    }

    private static ImagePicture loadImagePicture(Element element) throws SAXParseException {
        try {
            String name = element.getTextContent();
            BufferedImage image = ImageIO.read(ImagePicture.file(name));
            return new ImagePicture(name, image);
        } catch (IOException ex) {
            throw new SAXParseException("Cannot load portrait image", null);
        }
    }

}
