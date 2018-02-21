// each time a duplicate word appears, the node moves to the beginning of the list
public class FrontSelfAdjList implements List
{
	protected HNode list;
	protected long compCount = 0;
	protected long refCount = 0;
	boolean useRemove2 = false;
	
	public FrontSelfAdjList()
	{
		list = null;
	}
	
	public FrontSelfAdjList(boolean x)
	{
		list = null;
		useRemove2 = x;
	}
	
	public void add(String element)
	// Adds element to the list, increment count if already exists
	{ 
		HNode next = list; // Pointer
		HNode beforeNext= null; // Previous node pointer
		HNode newNode = new HNode(element);
		refCount++; // First time next gets set to the beginning of the list,add one to the counter
		while (next != null)
		{
			compCount++; // Increases the counter of word comparisons
			if(next.getInfo().equalsIgnoreCase(element))
			{
				// If found, then increase the number of times it has appeared and then move to front of list
				next.increaseNumTimes();
				if(beforeNext != null)
				{
					refCount++;
					beforeNext.setLink(next.getLink());
					refCount++;
					next.setLink(list);
					refCount++;
					list = next;
				}
				return;
			}
			refCount++; // Increases the counter of reference changes, when next gets set to a new place in the list
			beforeNext = next; // Sets the previous node pointer to next
			refCount++;
			next = next.getLink(); // Try the next item in the list
		}
		// If it didn't find the item already previously in the list, then it creates a new item at the beginning of the list
		refCount++;
		newNode.setLink(list);
		refCount++;
		list = newNode;
	}  
	
	public void remove(String element)
	{
		//check to see if remove2 should be used instead
		if(useRemove2)
		{
			remove2(element);
			return;
		}
		//proceed as normal if useRemove2 is false
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
	
	public void remove2(String element)
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
				else
				{
					if (beforeNext!=null)
					{
						refCount++;
						beforeNext.setLink(next.getLink());
					}
					refCount++;
					next.setLink(list.getLink());
					list=next;
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
	
	public int totalNumWords()
	{
		int total = 0;
		HNode next = list;
		while (next != null)
		{
			total = total + next.getNumTimes();
			next = next.getLink();
		}
		return total;
	}
	
	public int totalWords()
	{
		int total = 0;
		HNode next = list;
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
	
	public void reset()
	{
		refCount = 0;
		compCount = 0;
	}
}
