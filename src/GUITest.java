import squint.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.geom.*;
public class GUITest extends GUIManager
{
   
    private Canvas img;
    
    public GUITest(){
       
        //Open file for reading
        Scanner S = null;
        String fileName="tsp1000";
        try{ S = new Scanner(new File(fileName+".txt")); }
        catch(Exception e){ throw new RuntimeException(e); }
        
        int tourWidth = S.nextInt();//first two lines are width/height maxes
        int tourHeight = S.nextInt();

        
        // Make a new Tour
        Tour T = new Tour();
        
        // add points to the tour from the file.
        while(S.hasNext()){
            Point p=new Point(S.nextDouble(), tourHeight-S.nextDouble());
            //T.add(p);
            
            // Comment/Uncomment 
            //T.insertNearest(p);
            //T.insertSmallestBad(p); //left this in as a comparison to the below (this one being O(n^3) while below is O(n^2)).
            T.insertSmallestGood(p);
        }
        

        //Set up a Canvas
        img = new Canvas(tourWidth, tourHeight);
        img.setPointSize(4);
        img.setLineWeight(1);
        
        //Draw the Tour on the canvas by iterating through points
        Iterator i = T.iterator();
        
        Point prev = (Point)i.next();  //have to get first point before looping so we have a prev to draw back to
        img.drawPoint(prev);
        
        while(i.hasNext()){
            Point p=(Point)i.next();
            img.drawPoint(p);
            img.drawLine(prev,p); //connect previous point and this point.
            prev=p;
        }  
        img.drawLine(prev, T.get(0)); //connect last point to first.
 
        
        //Squint stuff.  Make a window, put canvas on contentPane etc.
        createWindow(tourWidth+100, tourHeight+100, "Graphical Output");

        contentPane.add(new JLabel("Tour with "+T.size()+" points"));
        contentPane.add(new JLabel("Tour distance: "+T.distance()));
        //put image in a scroll pane in case it's too big to show.
        JScrollPane scroll = new JScrollPane(img);
        scroll.setPreferredSize(new Dimension(getWidth()-50, getHeight()-50));
        contentPane.add(scroll);
        T.show();
        //T.write(tourWidth, tourHeight, fileName); //left the write method commented, as it overwrites the current file with the order achieved after a shorter tour is found
    }
}