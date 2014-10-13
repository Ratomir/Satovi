
package main;

import clock.Frame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Marijana
 */
public class MainClass
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        try 
        {
            /*
            Uuljučivanje look and feel. Uključuje se nimubs.
            */
            
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            
            /*
            Program ide na izvršavanje klase frame.
            */
            new Frame();
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) 
        {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
