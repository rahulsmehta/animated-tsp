import java.awt.*;
import javax.swing.*;
import java.util.*;
import squint.*;

public class TourPainter extends JPanel{
    private int width,height;
    private Tour currentTour;
    public TourPainter(int width,int height, Tour t){
        super();
        setPreferredSize(new Dimension(width,height));
        this.width=width;
        this.height=height;
        currentTour=t;
    }
    
    public TourPainter(int width,int height){
        this(width,height,null);
    }
    
    public void setTour(Tour t){
        currentTour=t;
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        setBackground(Color.WHITE);
        if(currentTour==null)return;
        currentTour.paint((Graphics2D)g.create());
    }
    
    
}