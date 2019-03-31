package characterbuilder;

import characterbuilder.ui.MainWindow;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;

public class CharacterBuilder {

    public static void main(String[] args) {
        try {
            MainWindow mainWindow = new MainWindow();
            mainWindow.show();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CharacterBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
