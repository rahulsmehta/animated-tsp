import squint.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.geom.*;
public class TerminalTest {
   
    public static void main(String[] args){
       
                //Open file for reading
        Scanner S = null;
        try{ S = new Scanner(new File("../Data/tsp8.txt")); }
        catch(Exception e){ throw new RuntimeException(e); }
        
        int w = S.nextInt(); //first two lines are width/height maxes
        int h = S.nextInt();

        
        // Make a new Tour
        Tour T = new Tour();
        
        // add points to the tour from the file.
        while(S.hasNext()){
            //T.add(new Point(S.nextDouble(), S.nextDouble()));
            
            // Comment/Uncomment 
            //T.insertNearest(S.nextDouble(), S.nextDouble()));
            T.insertSmallestGood(new Point(S.nextDouble(), S.nextDouble()));
        }
        
        
        //Print stuff out about it:
        System.out.println("Created Tour with "+T.size()+" points.");
        System.out.println("Tour has distance "+T.distance());
        System.out.println("Here is the tour in order of points visited");
        T.show();
    }
}
