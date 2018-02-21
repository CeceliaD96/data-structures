// Queue class to help parse the txt file
// Holds individual words with no outer punctuation
public class Queue 
{
	// pointers for the front and the rear of the queue
	private QNode front;
	private QNode rear;
	private QNode beginning;
	
	//constructor to assign front and rear to null
	public Queue()
	{
		beginning = null;
		front = null;
		rear = null;
	}
	
	public void enqueue(String element)
	{
		//creates new node with given string
		QNode newNode = new QNode(element);
		//checks if list is empty
		if (rear == null)
		{
			front = newNode;
			beginning = newNode;
		}
		else
		{
			rear.setLink(newNode);
		}
		rear = newNode;
	}
	public void restore() //sets queue back to original queue without re-parsing text file
	{
		front = beginning;
	}
	
	public String dequeue()
	{
		//checks for an empty queue
		if (isEmpty())
		{
			throw new QueueUnderflowException("Dequeue attempted on empty queue.");
		}
		else 
		{
			// returns the first thing in the queue and sets front to the next item in line
			String element;
			element = front.getInfo();
			front = front.getLink();
			if(front == null)
				rear = null;
			return element;
		}
	}
	// checks if the queue is empty
	public boolean isEmpty()
	{
		if (front == null) 
			return true;
		else
			return false;
	}
	
}
