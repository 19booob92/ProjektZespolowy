package com.pwr.Map;

import static java.awt.event.MouseEvent.BUTTON1;
import static java.awt.event.MouseEvent.BUTTON3;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;



public class GoogleMapPanel extends JPanel implements ActionListener,MouseMotionListener, MouseListener, MouseWheelListener, KeyListener{
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
    
    private MarkerRect markerRect = new MarkerRect(0,0);
    
    private String[] viewMode = new String[4];
    private int viewModeCounter = 0;
    private JButton buttonViewMode = null;
    private JButton buttonZoomIn = null;
    private JButton buttonZoomOut = null;
    private JButton buttonHelp = null;
    
    public GoogleMapPanel(int width, int height)
    {
    	setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
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
        
        buttonZoomIn = new JButton("Zoom +");
        buttonZoomIn.addActionListener(this);
        add(buttonZoomIn);
        
        buttonZoomOut = new JButton("Zoom -");
        buttonZoomOut.addActionListener(this);
        add(buttonZoomOut);
        
        buttonHelp = new JButton("Pomoc");
        buttonHelp.addActionListener(this);
        add(buttonHelp);
        
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
        
        if(markerRect!=null){
            if(!mDragged){
        markerRect.doDrawing(g);}}
    }
    
    
    @Override
   public void paintComponent(Graphics g) {
   super.paintComponent(g);
   doDrawing(g);
   
   }

    public void mouseDragged(MouseEvent e) {
    	
        if(e.isControlDown())
        {
        	markerRect.draw();
            markerRect.setWidth(-(mouseStartX-e.getX()));
            markerRect.setHeight(-(mouseStartY-e.getY()));
            repaint();
        }
       /* else if(markerRect.isSelectedDrag())
        {
            markerRect.setX(-(mouseStartX-e.getX()));
            markerRect.setY(-(mouseStartY-e.getY()));
            repaint();
        }*/
      /*  else if((markerRect.isSelectedWidth())||(markerRect.isSelectedHeight()))
        {
            if(markerRect.isSelectedWidth()){
            markerRect.changeWidth(-(mouseStartX-e.getX()));}
            if(markerRect.isSelectedHeight())
            {
                markerRect.changeHeight(-(mouseStartY-e.getY()));
            }
            repaint();
        }*/
//        else if(markerArray.isSelecetedHeigh())
//        {
//            markerArray.changeHeight(-(mouseStartY-e.getY()));
//            repaint();
//        }
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
        repaint();
        
        
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    	
        
        if(e.getButton()==BUTTON3){
        mouseX=e.getX();
        mouseY=e.getY();
        System.out.println("X:"+Integer.toString(mouseX));
        System.out.println("Y:"+Integer.toString(mouseY));
        System.out.println("Koordynaty: "+MapGetter.addMarker(mouseX,mouseY));
        MapGetter.getMapImage(MapGetter.createUrl(0, 0));
        //mouse_x=0;
        //mouse_y=0;
        
        }
        
        if(markerRect!=null)
        {
   /*         if(markerRect.isSelected())
            {
                markerRect.selectMarker(e.getX(), e.getY());
                if(markerRect.isSelected()){
                markerRect.setLabel(JOptionPane.showInputDialog("Dodaj nazwę"));
                }
            }
            markerRect.selectMarker(e.getX(), e.getY());
            markerRect.cleanUp();*/
        }
        
        repaint();
    }

    public void mousePressed(MouseEvent e) {
    	
    	
        if((e.isControlDown())&&(e.getButton()==BUTTON1))
        {
            markerRect=new MarkerRect(MapGetter.getLatitude(e.getY()),MapGetter.getLongtitude(e.getX()));
            //MainView.createPoint(MapGetter.getLongtitude(e.getX()), MapGetter.getLatitude(e.getY()));
        }
        markerRect.selectSize(e.getX(), e.getY());
        mouseStartX=e.getX();
        mouseStartY=e.getY();
        
        //mouse_x=e.getX();
        //mouse_y=e.getY();
        System.out.println("X:"+Integer.toString(e.getX()));
        System.out.println("Y:"+Integer.toString(e.getY()));
        repaint();

// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void mouseReleased(MouseEvent e) {
        //mouse_x=0;
        //mouse_y=0;
        mDragged=false;
        markerRect.stopDraw();
        if(markerRect!=null){
        markerRect.cleanUp();}
        
        if(e.isControlDown()){
        	MapGetter.setGoogleMarkers(markerRect);
        	 MapGetter.getMapImage(MapGetter.createUrl(0, 0));
        }
      /*  else if(((markerRect.isSelectedWidth())||markerRect.isSelectedHeight())||(markerRect.isSelectedDrag()))
        {
            //int index = markerRect.getSelectedIndex();
            //if(index!=-1){
            //MainView.setPoint(index,markerArray.getLongtitude(),markerArray.getLatitude());}
            //}
        }*/
        else
        {
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
         case KeyEvent.VK_PLUS:
            zoomIn();
            break;
         case KeyEvent.VK_MINUS:
             zoomOut();
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
            changeViewMode();
        }
        if(src==buttonZoomIn)
        {
            zoomIn();
        }
        
        if(src==buttonZoomOut)
        {
            zoomOut();
        }
        if(src==buttonHelp)
        {
        	JOptionPane.showMessageDialog(null, "Aby zaznaczyć teren przytrzymaj przycisk \"Ctrl\" i przeciągnij myszką.\n"
        			+"Przemieszczanie po mapie wykonuje się poprzez kliknięcie i przeciągnięcie myszy.\n"
        			+"Do przybliżania i oddalania można użyć rolki.");
        }
        
    }
    
    public void centerViewOnPoint(int index)
    {
        //MapGetter.getMapImage(MapGetter.createUrl(markerRect.getLongtitude(index), markerRect.getLatitude(index)));
        //repaint();
    }
    public void selectMarker(int index)
    {
        //markerRect.select(index);
    }
    
    public void deleteMarker(int index)
    {
        //markerRect.deleteMarker(index);
        //repaint();
    }
    
    private void zoomIn()
    {
            markerRect.cleanUp();
            MapGetter.zoomIn();
            MapGetter.getMapImage(MapGetter.createUrl(0, 0));
            markerRect.changeZoom();
            repaint();
    }
    
    private void zoomOut()
    {
            markerRect.cleanUp();
            MapGetter.zoomOut();
            MapGetter.getMapImage(MapGetter.createUrl(0, 0));
            markerRect.changeZoom();
            repaint();
    }
    
    private void changeViewMode()
    {
        viewModeCounter++;
            if(viewModeCounter==4){viewModeCounter=0;}
            MapGetter.setMapType(viewMode[viewModeCounter]);
            MapGetter.getMapImage(MapGetter.createUrl(0, 0));
            buttonViewMode.setText(viewMode[viewModeCounter].toUpperCase());
            repaint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(e.getWheelRotation()<0)
        {
            zoomIn();
        }
        else if(e.getWheelRotation()>0)
        {
            zoomOut();
        }
    }
    
    public double getMarkerLatitude()
    {
        return markerRect.getLatitude();
    }
    
    public double getMarkerLongtitude()
    {
        return markerRect.getLongtitude();
    }
    
    public double getMarkerWidth()
    {
        return markerRect.getWidthCoordinates();
    }
    
    public double getMarkerHeight()
    {
        return markerRect.getHeightCoordinates();
    }
    
	public void setMapPoint(double latitude, double longtitude, double width, double height)
	{
		markerRect = new MarkerRect(longtitude,latitude,width,height);
		MapGetter.setGoogleMarkers(markerRect);
		MapGetter.getMapImage(MapGetter.createUrl(0, 0));
		repaint();
	}
}
