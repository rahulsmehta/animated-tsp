import squint.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.geom.*;
public class DynamicGUI extends GUIManager
{
   
    private JFileChooser chooser = new JFileChooser( new File( System.getProperty( "user.dir" ) ));
    
    
    public DynamicGUI(String filename){
       
        //Open file for reading
        Scanner S = null;
        try{ S = new Scanner(new File(filename)); }
        catch(Exception e){ throw new RuntimeException(e); }
        
        int tourWidth = S.nextInt();//first two lines are width/height maxes
        int tourHeight = S.nextInt();

        
        // Make a new Tour
        Tour T = new Tour();
        TourPainter P=new TourPainter(tourWidth,tourHeight,T);
        createWindow(tourWidth+50, tourHeight+100, "Graphical Output");
        contentPane.add(P);
        JLabel size=new JLabel(),dist=new JLabel();
        contentPane.add(size);
        contentPane.add(dist);
        
        // add points to the tour from the file.
        while(S.hasNext()){
            Point p=new Point(S.nextDouble(), tourHeight-S.nextDouble());
            T.insertSmallestGood(p);
            size.setText("Points: "+Integer.toString(T.size()));
            dist.setText("Tour Distance: "+Double.toString(T.distance()));
            P.repaint();
        }
    }
}