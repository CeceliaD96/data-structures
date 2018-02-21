//sorted alphabetically
public class SortedList implements List
{
	protected HNode list; //points to the beginning of the list
	protected long compCount = 0;
	protected long refCount = 0;
	
	//constructor
	public SortedList()
	{
		list = null;
	}
	
	public void add(String element)
	// Adds element to the list, increment count if already exists
	{ 
		refCount++; // First time next gets set to the beginning of the list,add one to the counter
		HNode next = list; // Pointer
		HNode beforeNext= null; // Previous node pointer
		HNode newNode = new HNode(element);
		
		while (next != null)
		{
			compCount++; // Increases the counter of word comparisons
			int comp = next.getInfo().compareToIgnoreCase(element);
			if(comp==0)
			{
				// If found, then increase the number of times it has appeared and then exit method
				next.increaseNumTimes(); 
				return;
			}
			else if (comp>0) // The element compared is less than the current node
			{
				refCount++;
				newNode.setLink(next);
				if (beforeNext != null)
				{
					refCount++;
					beforeNext.setLink(newNode);
				}
				else
				{	
					refCount++;
				    newNode.setLink(list);
				    refCount++;
				    list = newNode;
				}
				return;
			}
			else
			{
				refCount++; // Increases the counter of reference changes, when next gets set to a new place in the list
				beforeNext = next; // Sets the previous node pointer to next
				refCount++;
				next = next.getLink(); // Try the next item in the list
			}
		}
		// If it didn't find the item already previously in the list, then it creates a new item at the end of the list
		if (beforeNext != null)
		{	
			refCount++;
			beforeNext.setLink(newNode);
		}
		else
		{
			refCount++;
		    newNode.setLink(list);
		    refCount++;
		    list = newNode;
		}
	} 
	public void reset()
	{
		refCount = 0;
		compCount = 0;
	}
	
	public void remove(String element)
	{
		HNode beforeNext = null;
		HNode next = list;
		
		while(next!=null)
		{
			compCount++;
			if(next.getInfo().equalsIgnoreCase(element))
			{
				next.decreaseNumTimes();
				if(next.getNumTimes()==0)
				{
					if(beforeNext!=null)
					{
						refCount++;
						beforeNext.setLink(next.getLink());
					}
					else
					{
						refCount++;
						list=next.getLink();
					}
				}
				return;
			}
			else
			{
				refCount++;
				beforeNext=next;
				refCount++;
				next=next.getLink();
			}
		}
	}
	
	public boolean isEmpty()
	// Returns true if this list is empty; otherwise, returns false.
	{              
		if (list == null) 
			return true;
		else
			return false;
	}
	
	public int totalNumWords()//total words
	{
		int total = 0;
		HNode next = list;
		// Adds all numTimes from each item in the list
		while (next != null)
		{
			total = total + next.getNumTimes();
			next = next.getLink();
		}
		return total;
	}
	
	public int totalWords()//total unique words
	{
		int total = 0;
		HNode next = list;
		// Counts the number of items in the list
		while (next != null)
		{
			total++;
			next = next.getLink();
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
