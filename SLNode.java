// Modified node class (copied and pasted from HNode) to include up, down, left, and right and bound variables
public class SLNode 
{
	// HNode contains string, int, and links
		private String info;
		public SLNode up; // By making public, no need for setters and getters
		public SLNode down;
		public SLNode right;
		public SLNode left;
		private int numTimes;
		
		public static final String NEG_INF = "negInf"; //Lower string bound
		public static final String POS_INF = "posInf"; //Upper string bound
		
		//constructor
		public SLNode(String info)
		{
		  this.info = info;
		  up = null;
		  down = null;
		  left = null;
		  right = null;
		  numTimes = 1;
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
