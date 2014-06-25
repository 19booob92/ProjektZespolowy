package com.pwr.MainView;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePreview extends JPanel{

    private BufferedImage image;

    public  ImagePreview(String src) {
    	super();
       try {                
          image = ImageIO.read(new File(src));
       } catch (IOException ex) {
       }
      setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
    }

}

