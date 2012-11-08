import java.awt.geom.*;

/**
 * A simple facade for the class Point2D.Double - a class that represents
 * a point in 2D space with Double precision.
 * 
 * Using this class just saves you some typing - see the documentation for Point2D.Double to see the methods it has.
 * 
 * Of particular interest to you will be the distance(Point2D) method which gives the pythagorean distance
 * between this Point and some other Point. Because this Point class extends Point2D you can use any
 * of the methods by polymorphism.
 * EXAMPLE:
 *   Point p = new Point(2,3);
 *   Point p2 = new Point(102, 57);
 *   double dist = p.distance(p2);  
 */

public class Point extends Point2D.Double
{

    public Point(double x, double y){
        super(x,y);
    }
    
    public String toString(){
        return "Point["+getX()+", "+getY()+"]";
    }

}