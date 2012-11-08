import java.util.*;

public class TourIterator implements Iterator
{
    private ListNode current;
    public TourIterator(ListNode n){
        current=n;
    }
    
    public boolean hasNext(){
        if(current==null)return false;
        return true;
    }
    
    public Point next(){
        Point result=current.getValue();
        current=current.getNext();
        return (Point)result;
    }
    
    /*public Point getPoint(){
        return current.getValue();
    }*/
    
    public Point currentPoint(){
        return current.getValue();
    }
    
    public void remove(){
        throw new UnsupportedOperationException();
    }
    
}