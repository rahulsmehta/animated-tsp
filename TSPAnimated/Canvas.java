import squint.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.awt.geom.Line2D.Double;
public class Canvas extends JLabel //BufferedImage
{
    Graphics2D g;
    BufferedImage bi;
    private double pointSize;
    public Canvas(int w, int h){
        super();        
        bi = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        g = bi.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,w,h);
        g.setColor(Color.BLACK);
        setIcon(new ImageIcon(bi));
        setPointSize(1.5);
        g.setStroke(new BasicStroke(1));
    }
    
    
    /**
     * Set the thickness of the line in pixels. The line weight does not need to be set
     * each time a line is drawn.  Once it's set all subsequent lines will be draw with 
     * this thickness until a different lineWeight has been set.
     */
    public void setLineWeight(int weight){
        g.setStroke(new BasicStroke(weight));
    }
    
    /**
     * Set the size of the points that will be drawn on the Canvas, where size=diameter.
     * Once pointSize has been set, all subsequent points will be drawn with this size
     * until setPointSize is called with a different size.
     */
    public void setPointSize(double size){
        pointSize=size;
    }
    
    /**
     * Draw a point on the canvas at the given Point. Default is for a 2px
     * diameter point to be drawn centered on the x,y in the given point.
     */
    public void drawPoint(Point2D p){
        
      //  this.drawPoint(p, pointSize);
         double off = (double)pointSize/2.0;
         g.fill(new Ellipse2D.Double(p.getX()-off, p.getY()-off, pointSize, pointSize));
         repaint();
    }
    
    /**
     * Same description as drawPoint(Point2D p), but will use given x, y.
     * NOTE: method just constructs a 
     */
    public void drawPoint(double x, double y){
       // this.drawPoint(new Point(x,y));
        double off = (double)pointSize/2.0;
       g.fill(new Ellipse2D.Double(x-off, y-off, pointSize, pointSize));
       repaint();
    }
    
    /**
     * Draw a line on the canvas between the two given points. Default line thickness
     * is 1px.  You can change the thickness by calling lineWeight(int) before drawing
     * the line.
     */
    public void drawLine(Point2D p, Point2D p2){    
        g.draw(new Line2D.Double(p,p2));
        repaint();
    }
    
    /**
     * Same description as drawLine(Point2D, Point2D) but using given point components.
     */
    public void drawLine(double x0, double y0, double x1, double y1) {
        g.draw(new Line2D.Double((x0), (y0), (x1), (y1)));
        repaint();
    }
    
    public int getWidth(){ return bi.getWidth(); }
    public int getHeight(){ return bi.getHeight(); }

    
    /**
     * Set the color of the "paintbrush".  Once the color has been set, all subsquent
     * points and lines will be draw in this color until setColor is called with a 
     * different color.
     */
    public void setColor(Color c){
        g.setColor(c);
    }
 
} 