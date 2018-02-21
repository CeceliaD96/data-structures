
public class QueueUnderflowException extends RuntimeException 
{
	//exception in case a pop or a top is attempted on an empty list
	  public QueueUnderflowException()
	  {
	    super();
	  }
	  //allows a custom message
	  public QueueUnderflowException(String message)
	  {
	    super(message);
	  }
}

