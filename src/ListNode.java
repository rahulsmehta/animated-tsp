/**
 * ListNode from the AP Java Subset
 */
public class ListNode
{
	private Point value;
	private ListNode next;
	
	public ListNode(Point v, ListNode n)
	{    value = v;   next = n;	}	
	public ListNode getNext(){ return next; }
	public Point getValue(){ return value; }
	public void setNext(ListNode n){ next = n; }
	public void setValue(Point v){ value = v; }
}