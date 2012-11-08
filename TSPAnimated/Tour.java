import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import java.io.*;

public class Tour{

    private ListNode front, last;
    private int size;
   
    public Tour(){
        front=null;
        last=null;
        size=0;
    }
    
    public boolean add(Point p){
        ListNode toAdd = new ListNode(p, null);
        if(last != null) last.setNext(toAdd);
        last = toAdd;
        if(front==null) front = toAdd;
        size++;
        return true;
    }
    
    public boolean add(int index, Point p){
        if(!(index>=0||index<size))throw new RuntimeException();
        if(index==0){
            ListNode insert=new ListNode(p,front);
            front=insert;
            size++;
            return true;
        }
        ListNode temp=front;
        for(int i=0; i<index-1; i++){
            temp=temp.getNext();
        }
        ListNode insert=new ListNode(p,temp.getNext());
        temp.setNext(insert);
        size++;
        return true;
    }
    
    public Point get(int index){
        if(!(index>=0||index<size))throw new RuntimeException();
        if(index==0)return (Point)front.getValue();
        ListNode temp=front;
        for(int i=1; i<=index; i++){
            temp=temp.getNext();
        }
        return (Point)temp.getValue();
    }
    
    public ListNode getNode(int index){
        if(!(index>=0||index<size))throw new RuntimeException();
        if(index==0)return front;
        ListNode temp=front;
        for(int i=1; i<=index; i++){
            temp=temp.getNext();
        }
        return temp;
    }
    
    public Point set(int index, Point p){
        ListNode temp=front, old=null;
        if(index==0){
            Object result=front.getValue();
            front.setValue(p);
            return (Point)result;
        }
        for(int i=1; i<=index; i++){
            temp=temp.getNext();
        }
        Object result=temp.getValue();
        temp.setValue(p);
        return (Point)result;
    }
    
    public Point remove(int index){
        if(!(index>=0||index<size))throw new RuntimeException();
        ListNode temp=front;
        if(index==0){
            front=front.getNext();
            size--;
            return (Point)temp.getValue();
        }
        for(int i=1; i<index; i++){
            temp=temp.getNext();
        }
        ListNode result=temp.getNext();
        temp.setNext(result.getNext());
        size--;
        return (Point)result.getValue();
    }
    
    public int size(){
        return size;
    }
    public Iterator iterator(){
        return new TourIterator(front);
    }
    
    protected TourIterator tourIterator(){
        return new TourIterator(front);
    }
    
    
    
    //Tour Methods Below
    
    public void show(){
        Iterator I=iterator();
        int num=1;
        while(I.hasNext()){
            System.out.println("Point "+num+": "+(Point)I.next());
            num++;
        }
    }
    
    public double distance(){
        double result=0.0;
        if(size<2)return 0.0;
        TourIterator I=(TourIterator)iterator();
        for(int i=0; i<(size-1); i++){
            Point p1=I.next();
            Point p2=I.currentPoint();
            result+=p1.distance(p2);
        }
        result+=last.getValue().distance(front.getValue());
        return result;
    }
    
    public void insertNearest(Point p){
        //add p to the list according to the NearestNeighbor heuristic
        if(size==0){
            add(p);
            return;
        }
        TourIterator I=(TourIterator)iterator();
        int sIndex=0;
        double sDist=last.getValue().distance(p);
        for(int i=1; i<size; i++){
            double tDist=I.next().distance(p);
            if(tDist<sDist){
                sDist=tDist;
                sIndex=i;
            }
        }
        //System.out.println("Node "+size+" added at "+sIndex+", "+sDist); //for testing
        add(sIndex,p);
    }
        
    
    public void insertSmallestBad(Point p){ //bad version
        if(size<2){
            add(p);
            return;
        }
        int sIndex=0;
        add(0,p);
        double sDist=distance();
        remove(0);
        for(int i=1; i<size; i++){
            add(i,p);
            double tDist=distance();
            if(tDist<sDist){
                sDist=tDist;
                sIndex=i;
            }
            remove(i);
        }
        //System.out.println("Node "+size+" added at "+sIndex+", "+sDist); //for testing
        add(sIndex,p);
    }
    
    public void insertSmallestGood(Point p){
        if(size<2){
            add(p);
            return;
        }
        double dInit=distance();
        TourIterator I=(TourIterator)iterator();
        int sIndex=0;
        double sDist=simDistance(0,dInit,p,I.next(),I.currentPoint());
        for(int i=1; i<size; i++){
            double tDist=0.0;
            if(i==(size-1)){
                tDist=simDistance(i,dInit,p,new Point(0.0,0.0), new Point(0.0,0.0));
            }
            else tDist=simDistance(i,dInit,p,I.next(),I.currentPoint());
            if(tDist<sDist){
                sDist=tDist;
                sIndex=i+1; //the smallest index is always offset by one using the iterator here, so 1 is added to fix that
            }
        }
        //System.out.println("Node "+size+" added at "+sIndex+", "+sDist); //for testing
        add(sIndex,p);
    }
    
    private double simDistance(int index, double dInit, Point p,Point p1, Point p2){ //this method allows smallestIncrease to have a running time of O(n^2)
        if(!(index>=0||index<size))throw new RuntimeException();
        double distance=dInit;
        if(index==0){
            distance+=last.getValue().distance(p);
            distance+=p.distance(front.getValue());
            distance-=last.getValue().distance(front.getValue());
            return distance;
        }
        if(index==(size-1)){
            distance+=last.getValue().distance(p);
            distance+=p.distance(front.getValue());
            distance-=last.getValue().distance(front.getValue());
            return distance;
        }
        distance+=p1.distance(p);
        distance+=p.distance(p2);
        distance-=p1.distance(p2);
        return distance;
    }
    
    //start drawing methods
    
    public void paint(Graphics2D g){
        if(front==null)return;
        TourIterator T=tourIterator();
        g.setColor(Color.BLACK);
        Point prev=T.next();
        drawPoint(prev,g);
        while(T.hasNext()){
            Point p=T.next();
            drawPoint(p,g);
            drawLine(prev,p,g);
            prev=p;
        }
        drawLine(prev,get(0),g);
    }
    
    public void setLineWeight(int weight,Graphics2D g){
    	g.setStroke(new BasicStroke(weight));
    }
    
    public void drawPoint(Point2D p,Graphics2D g){
    	
         double pointSize=4.0;
    	 double off = (double)pointSize/2.0;
    	 g.fill(new Ellipse2D.Double(p.getX()-off, p.getY()-off, pointSize, pointSize));
    }
    
    
    public void drawPoint(double x, double y,Graphics2D g){
       // this.drawPoint(new Point(x,y));
       double pointSize=4.0;
       double off = (double)pointSize/2.0;
       g.fill(new Ellipse2D.Double(x-off, y-off, pointSize, pointSize));
    }
    
    
    public void drawLine(Point2D p, Point2D p2,Graphics2D g){    
    	g.draw(new Line2D.Double(p,p2));
    }
    
    
    public void drawLine(double x0, double y0, double x1, double y1,Graphics2D g) {
    	g.draw(new Line2D.Double((x0), (y0), (x1), (y1)));
    }
    
    //end drawing methods
    
    public void write(int width, int height, String fileName){ //writes the current list of points to a text file, so it can be run through the algorithm again
        TourIterator I=(TourIterator)iterator();
        FileWriter f=null;
        BufferedWriter out=null;
        try{
            f=new FileWriter(fileName+".txt");
            out=new BufferedWriter(f);
            out.write(Integer.toString(width)+" "+Integer.toString(height)+" ");
            while(I.hasNext()){
                Point p=I.next();
                out.write(Double.toString(p.getX()));
                out.write(" ");
                out.write(Double.toString(p.getY()));
                out.write(" ");
            }
            out.close();
        } catch(Exception e){
            throw new RuntimeException();
        }
        
    }

}