
public interface List 
{
	
	//add element to the list
	public void add(String element);
	//remove element from the list
	public void remove(String element);
	//check if the list is empty
	public boolean isEmpty();
	//get the total number of reference counts
	public long getRefCount();
	//get the total number of comparison counts
	public long getCompCount();
	//reset the comparison count and the reference count
	public void reset();
	//get the total unique words
	public int totalWords();
	//get the total words
	public int totalNumWords();
}
