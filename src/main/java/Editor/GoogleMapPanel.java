package Editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import static java.awt.event.MouseEvent.BUTTON1;
import static java.awt.event.MouseEvent.BUTTON2;
import static java.awt.event.MouseEvent.BUTTON3;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.abs;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GoogleMapPanel extends JPanel implements ActionListener,MouseMotionListener, MouseListener, KeyListener{
	private int mouseX=0;
    private int mouseY=0;
    private int mouseStartX=0;
    private int mouseStartY=0;
    private int imageX=0;
    private int imageY=0;
    private int imageVX=0;
    private int imageVY=0;
    private int imageWidth=0;
    private int imageHeight=0;
    
    private boolean mDragged=false;
    
    private MarkerRect markerArray = null;
    
    private String[] viewMode = new String[4];
    private int viewModeCounter = 0;
    private JButton buttonViewMode = null;
    
    GoogleMapPanel(int width, int height)
    {
        imageWidth=width;
        imageHeight=height;
        MapGetter.setImageSizeH(imageHeight);
        MapGetter.setImageSizeW(imageWidth);
        viewMode[0]="roadmap";
        viewMode[1]="satellite";
        viewMode[2]="hybrid";
        viewMode[3]="terrain";
        buttonViewMode = new JButton(viewMode[0].toUpperCase());
        buttonViewMode.addActionListener(this);
        buttonViewMode.setAlignmentX(RIGHT_ALIGNMENT);
        
        add(buttonViewMode);
        MapGetter.getMapImage(MapGetter.createUrl(0, 0));
        repaint();
    }
    
    private BufferedImage createImage()
    {
        BufferedImage img = null;
            try {
                img = ImageIO.read(new File("image.jpg"));
                } catch (IOException e) {
                }
            
        BufferedImage image = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_RGB); 
        Graphics g = image.getGraphics();
        
        g.drawImage(img, imageX-imageVX, imageY-imageVY, null);
           /* try {  
                ImageIO.write(image, "jpg", new File("CustomImage.jpg")); 
                } catch (IOException e) {  
                e.printStackTrace(); 
                }*/
        return image;
    }
    
    private void doDrawing(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;

        BufferedImage img = null;
        img = createImage();
        /*    try {
                img = ImageIO.read(new File("image.jpg"));
                } catch (IOException e) {
                }*/
        img.flush();
        //POLOZENIE CZARNEGO TLA
        g2d.drawImage(img, 0, 0, this);
        
        if(markerArray!=null){
            if(!mDragged){
        markerArray.doDrawing(g);}}
    }
    
    
    @Override
   public void paintComponent(Graphics g) {
   super.paintComponent(g);
   doDrawing(g);
   
   }

    public void mouseDragged(MouseEvent e) {
        
        if(e.isControlDown())
        {
            markerArray.setWidth(-(mouseStartX-e.getX()));
            markerArray.setheight(-(mouseStartY-e.getY()));
            repaint();
        }
        else
        {
        mDragged=true;
        mouseX=e.getX();
        mouseY=e.getY();
        imageVX=mouseStartX-mouseX;
        imageVY=mouseStartY-mouseY;
        repaint();
        System.out.println("ruch");
        }
        
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void mouseMoved(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==BUTTON3){
        mouseX=e.getX();
        mouseY=e.getY();
        System.out.println("X:"+Integer.toString(mouseX));
        System.out.println("Y:"+Integer.toString(mouseY));
        System.out.println("Koordynaty: "+MapGetter.addMarker(mouseX,mouseY));
        MapGetter.getMapImage(MapGetter.createUrl(0, 0));
        double [] coords = MapGetter.getMarker(mouseX, mouseY);
        MainView.createPoint(coords[0], coords[1]);
        //mouse_x=0;
        //mouse_y=0;
        
        }
        if(markerArray!=null)
        {
            if(markerArray.isSelected())
            {
                markerArray.selectMarker(e.getX(), e.getY());
                if(markerArray.isSelected()){
                markerArray.setLabel(JOptionPane.showInputDialog("Dodaj nazwę"));}
            }
            markerArray.selectMarker(e.getX(), e.getY());
            markerArray.cleanUp();
        }
        
        repaint();
    }

    public void mousePressed(MouseEvent e) {
        
        if((e.isControlDown())&&(e.getButton()==BUTTON1))
        {
            markerArray = new MarkerRect(MapGetter.getLatitude(e.getY()),MapGetter.getLongtitude(e.getX()));
            //double [] coords = MapGetter.getMarker(mouseX, mouseY);
            // MainView.createPoint(coords[0], coords[1]);
        }
        mouseStartX=e.getX();
        mouseStartY=e.getY();
        
        //mouse_x=e.getX();
        //mouse_y=e.getY();
        //System.out.println(mouse_x);
        repaint();

// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void mouseReleased(MouseEvent e) {
        //mouse_x=0;
        //mouse_y=0;
        mDragged=false;
        if(!e.isControlDown()){
        imageVX=0;
        imageVY=0;
        mouseX=e.getX();
        mouseY=e.getY();
        MapGetter.getMapImage(MapGetter.createUrl(mouseStartX-mouseX,mouseY-mouseStartY));
        System.out.println("Roznica X:"+Integer.toString(mouseStartX-mouseX));
        System.out.println("Roznica Y:"+Integer.toString(mouseY-mouseStartY));
        //mouse_x=0;
        //mouse_y=0;
        }
        if(markerArray!=null){
        markerArray.cleanUp();}
        repaint();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void keyPressed(KeyEvent e) {
        System.out.println("Usuwam znacznik");
     switch (e.getKeyCode())
     {
         case KeyEvent.VK_DELETE: 
             MapGetter.deleteMarker();
             MapGetter.getMapImage(MapGetter.createUrl(0, 0));
             repaint();
             break;
     } 
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void actionPerformed(ActionEvent e) {
        
        Object src = e.getSource();
        
        if(src==buttonViewMode)
        {
            System.out.println("klik");
            viewModeCounter++;
            if(viewModeCounter==4){viewModeCounter=0;}
            MapGetter.setMapType(viewMode[viewModeCounter]);
            MapGetter.getMapImage(MapGetter.createUrl(0, 0));
            buttonViewMode.setText(viewMode[viewModeCounter].toUpperCase());
            repaint();
        }
        
    }
}
