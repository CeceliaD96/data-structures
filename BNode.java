//Nodes used by the Binary Tree class, similar to the other nodes used in this program

public class BNode 
{
	// BNode contains string, int, and left and right child nodes
		private String info;
		private BNode right;
		private BNode left;
		private int numTimes;
		
		//constructor
		public BNode(String info)
		{
		  this.info = info;
		  left = null;
		  right = null;
		  numTimes = 1;
		}
		// Sets info string	 
		public void setInfo(String info)
		{
			this.info = info;
		}

		public String getInfo()
		// Returns info string
		{
		return info;
		}
		 
		public void setLeft(BNode link)
		// Sets sets left child
		{
		    this.left = link;
		}
		  
		public void setRight(BNode link)
		// Sets right child
		{
		    this.right = link;
		}
		  
		  
		public BNode getLeft()
		// Returns left child
		{
		    return left;
		}
		  
		public BNode getRight()
		// Returns right child
		{
		    return right;
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
