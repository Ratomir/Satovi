package clock;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JPanel;

/**
 *
 * @author Ratomir
 */


public class BoardDigital extends JPanel implements Runnable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Thread thread = null;
    SimpleDateFormat formatter = new SimpleDateFormat("s", Locale.getDefault());
    Date currentDate;
    
    Font digitalFont;
    
    public BoardDigital()
    {
        try 
        {
            //create the font to use. Specify the size!
            digitalFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/Content/DS-DIGII.TTF")).deriveFont(50f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/Content/DS-DIGII.TTF")));
        } 
        catch (IOException | FontFormatException e)
        {
        }
    }
    
    @Override
    public void run() 
    {
         while (thread != null) 
        {
            try 
            {
                Thread.sleep(1000);
            } 
            catch (InterruptedException e) 
            {
            }
            
            repaint();
        }
        thread = null;
    }
    
    public void start() 
    {
        if (thread == null) 
        {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stop() 
    {
        thread = null;
    }
    
    @Override
    public void paint(Graphics g)
    {
        int second, minute, hour;
        
        super.paint(g);
        
        currentDate = new Date();
        formatter.applyPattern("s");

        second = Integer.parseInt(formatter.format(currentDate));
        formatter.applyPattern("m");

        minute = Integer.parseInt(formatter.format(currentDate));
        formatter.applyPattern("h");
        hour = Integer.parseInt(formatter.format(currentDate));
        
        //Font kojim se ispisuje text na ekranu           
        g.setFont(digitalFont);
        
        Dimension d = getSize();
        FontMetrics fm = g.getFontMetrics();
        String time = hour + ":" + minute + ":" + second;
        
        g.setColor(new Color(0, 165, 255));
        g.drawString(time, d.width/2 - fm.stringWidth(time) / 2, d.height/2 + fm.getDescent());

        // Sinhronizovanje sa grafičkom kartom
        Toolkit.getDefaultToolkit().sync();

        // Optimizacija upotrebe RAM-a, 
        g.dispose();
    }
    
}
