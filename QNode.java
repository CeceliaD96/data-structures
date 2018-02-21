// Used by the queue class
public class QNode 
{
	// Node contains string and a link to the next node
	private String info;
	private QNode link;
	
	//constructor
	public QNode(String element)
	{
		this.info = element;
		link = null;
	}
	
	public void setInfo(String element)
	{
		this.info = element;
	}
	
	public String getInfo()
	{
		return info;
	}
	
	public void setLink(QNode link)
	{
		this.link = link;
	}
	
	public QNode getLink()
	{
		return link;
	}
}
