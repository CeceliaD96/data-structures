//used by all 4 list classes
public class HNode 
{
	// HNode contains string, int, and link
	private String info;
	private FrontSelfAdjList list;
	private HNode link;
	private int numTimes;
	
	//constructor
	public HNode(String info)
	{
	  this.info = info;
	  link = null;
	  numTimes = 1;
	}
	
	public HNode(FrontSelfAdjList nodeList)
	{
		this.list = nodeList;
		link = null;
	}
	 
	  public void setInfo(String info)
	  // Sets info string
	  {
	    this.info = info;
	  }

	  public String getInfo()
	  // Returns info string
	  {
	    return info;
	  }
	  
	  public FrontSelfAdjList getList()
	  {
		  return list;
	  }
	 
	  public void setLink(HNode link)
	  // Sets link
	  {
	    this.link = link;
	  }
	  
	  public HNode getLink()
	  // Returns link
	  {
	    return link;
	  }
	  
	  public void increaseNumTimes()
	  {
		  numTimes++;
	  }
	  
	  public void decreaseNumTimes()
	  {
		  numTimes--;
	  }
	  
	  public int getNumTimes()
	  {
		  return numTimes;
	  }
	  
	  public String toString()
	  {
		  String newString = getInfo()+ " "+ getNumTimes() + " Times";
		  return newString;
	  }
}
