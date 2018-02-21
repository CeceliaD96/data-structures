// SkipList implementation
// Creates multiple sentinal, probabalistic linked lists to ease searching and sorting
// Search, Add, and Remove are required methods
// uses SLNodes, which have 4 links, as opposed to 1
import java.util.*;

public class SkipList implements List
{
	private SLNode head; // Leftmost node
	private SLNode tail; // Rightmost node
	private int height; //number of parallel linked lists (# of lanes)
	private int num; // number of items in the list (in the slow lane)
	private Random rand; //need a random number generator to determine how many lanes to add
	private int refCount;
	private int compCount;
	
	//constructor creates empty SkipList with head and tail
	public SkipList()
	{
		head = new SLNode(SLNode.NEG_INF); //Left
		tail = new SLNode(SLNode.POS_INF); //Right
		head.right = tail; //pointing head to tail
		tail.left = head; // pointing tail to head
		rand = new Random();
		num = 0; // empty list, so 0 items
		height = 1; // empty lists have 1 lane
	}
	
	//If element is not already in the list,
	//Search method returns the preceding slow lane node to the one to be inserted, insert between next and next.right
	//Else, if element matches a node already in the list, 
	//Existing slow lane SLNode is returned
	public SLNode search(String element)
	{

		SLNode next = head;
		//System.out.println("Directly to the right of head is: "+ next);
		//System.out.print(element +" goes after ");
		while(true)
		{
			compCount++;
			while(next.right.getInfo()!=SLNode.POS_INF && next.right.getInfo().compareToIgnoreCase(element)<=0)
			{
				compCount++;
				refCount++;
				next = next.right; // Move to the right one item in the list
			}
			if (next.down == null) // Check for a level beneath current level, if none proceed
			{
				//System.out.println(next);
				return next; //return the item that precedes the location element should be inserted or the node to be increased
			}
			refCount++;
			next = next.down; // If there is a level beneath current level, go deeper
		}
	}
	
	//Add method calls the search method and inserts the given string element into the list
	//This method also does a coin toss to determine if and how many lanes should be added
	//Connects existing items in the lanes to element, and created new lane if needed
	public void add(String element)
	{
		SLNode newNode = new SLNode(element);
		//System.out.println(newNode);
		SLNode result = search(element); // get the preceding location (or null if it doesn't need to be inserted)
		// use the result location to insert newNode, then proceed to flip a coin to determine how many levels it should span
		// if result has the same info as element, do nothing here
		compCount++;
		if(result.getInfo().equalsIgnoreCase(element))
		{
			result.increaseNumTimes();
		}
		else 
		{
			refCount = refCount+4;
			newNode.right = result.right; //points the newnode right arrow to the item originally to the right of result (newNode<->result.right)
			newNode.left = result; // result<-newNode
			result.right.left = newNode; //points the item originally to the right of the result's left arrow to the newNode (newNode<-result.right)
			result.right = newNode; // result<->newNode
			
			SLNode lowerNode = newNode; //initial level node when implementing new node in upper level
			while(rand.nextBoolean()) //gives a true or a false, this while determines how many levels each node will be contained within
			{
				SLNode current = lowerNode; //pointer to current node being checked
				//System.out.println("line 72: "+current +" "+current.left);
				SLNode upNode = new SLNode(newNode.getInfo()); //current upper level node to be placed in the right spot
				while(current.up==null) //checking for level above directly above current
				{
					refCount++;
					current=current.left; //keep moving through list towards the beginning to check for upper level
					//System.out.println("line 76: " +current);
					if(current.getInfo()==SLNode.NEG_INF) //if at beginning of list with no upper level, create one
					{
						//System.out.println("new level was made");
						// TODO:implement new level
						SLNode newHead = new SLNode(SLNode.NEG_INF); //new Left
						SLNode newTail = new SLNode(SLNode.POS_INF); //new Right
						refCount= refCount+8;
						newHead.right = newTail; //pointing head to tail of new level
						newTail.left = newHead; // pointing tail to head of new level
						newHead.down = head; // next few lines set head and tail to new head and tail and connect levels
						newTail.down = tail;
						head.up = newHead;
						tail.up = newTail;
						head = newHead;
						tail = newTail;
					}
				
				}
				// once it finds a node with a valid .up, move to that level and insert
				// or if no valid .up, created one and set current to pos_Inf node of upper level
				refCount = refCount+8;
				current = current.up; //move pointer to the upper level
				current.right.left = upNode; //points the item originally to the right of the result's left arrow to the upNode (upNode<-current.right)
				upNode.right = current.right; //points the upnode right arrow to the item originally to the right of result (upNode<->current.right)
				upNode.left = current; // current<-upNode
				current.right = upNode; // current<->upNode
				upNode.down = lowerNode; //connects the upper level node to the lower level node
				lowerNode.up = upNode; // ""
				
				lowerNode = upNode; // set lowerNode to the previous upNode in prep for a possible next level
			}
			
		}
	}
	
	public void reset()
	{
		refCount = 0;
		compCount = 0;
	}
	
	public void remove(String element)
	{
		SLNode current = search(element);
		current.decreaseNumTimes(); //removes a single iteration of the element
		if (current.getNumTimes()==0) //when numTimes becomes 0, remove the node completely
		{
			do //as long as there is another level, it removes the node from the level, and removes the level if it's the last node in it
			{
				if(current.right==tail && current.left==head) //checks if it is the last node in a level and removes it...
				{
					refCount = refCount+2;
					head = head.down;
					tail = tail.down;
				}
				else
				{
					refCount = refCount+2;
					current.left.right = current.right; // points the nodes on either side of the current node to each other
					current.right.left = current.left; // ""
				}
				refCount++;
				current = current.up; // moves up to next level if there is one to remove the node there as well
			}while(current!=null); //checks to see if there is another level, null if there is no other level
		}
	}
	
	public boolean isEmpty()
	// Returns true if this list is empty; otherwise, returns false.
	{              
		if (head.right == tail) 
			return true;
		else
			return false;
	}
	
	public int totalNumWords()
	{
		int total = 0;
		SLNode start = head;
		while (start.down != null) //gets down to the bottom level
		{
			start = start.down;
		}
		start = start.right; // start on an actual string
		while (start.getInfo()!=SLNode.POS_INF) //don't include neg_inf string
		{
			total = total + start.getNumTimes(); //add total number of strings
			start = start.right;//move right to the next string
		}
		return total;
	}
	
	public int totalWords()
	{
		int total = 0;
		SLNode start = head;
		while (start.down != null) //gets down to the bottom level
		{
			start = start.down;
		}
		start = start.right; // start on an actual string
		while (start.getInfo()!=SLNode.POS_INF) //don't include neg_inf string
		{
			total++; //increases total as it moves right
			start = start.right;//move right to the next string
		}
		return total;
	}
	public String toString()
	{
		/*Display results in this Format( or close enough to put into excel sheet easier)
		 * 
		 * Words: Unique Words: Reference Changes: Comparisons: Runtime:
		 * 27 188 3145 1037964 567.00 secs
		 *
		 *
		 */
		return "Words: UniqueWords: ReferenceChanges: Comparisons: Runtime: \n"+totalNumWords()+" "+totalWords()+" "+getRefCount()+" "+getCompCount()+" ";
		
	}
	public long getRefCount()
	{
		return refCount;
	}
	public long getCompCount()
	{
		return compCount;
	}
	
}
