//Creates a binary tree data structure with given data

public class BinaryTree 
{
	public BNode root;
	
	public BinaryTree()
	{
		root=null;
	}
	
	public void add(String element)
	{
		BNode newNode = new BNode(element);
		BNode current = root;
		BNode previous = null;
		//determine of the newNode should go left or right of the current node
		//when loop ends, newNode was either found, increased, and method returned or previous is the parent node of the newNode
		int goLeftorRight = 0;
		while(current != null)
		{
			//if newNode is less than current, go left
			if(newNode.getInfo().compareToIgnoreCase(current.getInfo())<0) 
			{
				previous = current;
				current = current.getLeft();
				goLeftorRight = -1;
				
			}
			//if newNode is more than current, go right
			else if(newNode.getInfo().compareToIgnoreCase(current.getInfo())>0) 
			{
				previous = current;
				current = current.getRight();
				goLeftorRight = 1;
			}
			//if newNode is equal to the current node, increase numtimes and return
			else
			{
				current.increaseNumTimes();
				return;
			}
		}
		//newnode goes to the left child of prev
		if(goLeftorRight == -1)
		{
			previous.setLeft(newNode);
		}
		 //newNode goes to the right of prev
		else if (goLeftorRight == 1)
		{
			previous.setRight(newNode);
		}
		//list is empty
		else 
		{
			root = newNode;
		}
		
	}
	
	//find total words in the binary tree recursively, treat each node as individual tree
	public int totalWords()
	{
		int total = 0;
		//reached end of branch, return
		if(this.root==null) 
			return total;
		//left node is new binary tree, get total words of it
		BinaryTree leftTree = new BinaryTree();
		leftTree.root = this.root.getLeft();
		total = total + leftTree.totalWords();
		//right node is new binary tree, get total words of it
		BinaryTree rightTree = new BinaryTree();
		rightTree.root = this.root.getRight();
		total = total + rightTree.totalWords();

		//account for the root by adding 1
		return total+1;
	}
	
	//for total numWords in the binary tree recursively, same as totalWords
	public int totalNumWords()
	{
		int total = 0;
		//reached end of branch, return
		if(this.root==null) 
			return total;
		//left node is new binary tree, get totalnumwords
		BinaryTree leftTree = new BinaryTree();
		leftTree.root = this.root.getLeft();
		total = total + leftTree.totalNumWords();
		//right node is new binary tree, get totalnumwords
		BinaryTree rightTree = new BinaryTree();
		rightTree.root = this.root.getRight();
		total = total + rightTree.totalNumWords();

		//account for the root's numtimes
		return total+this.root.getNumTimes();
	}
	
	//Find the height of the tree recursively
	public int height()
	{
		int height = 0;
		//reached the end of the branch, return
		if(this.root==null) 
			return height;
		//left node is now a binary tree, find it's height
		BinaryTree leftTree = new BinaryTree();
		leftTree.root = this.root.getLeft();
		int leftHeight = leftTree.height();
		//right node is now a binary tree, find it's height
		BinaryTree rightTree = new BinaryTree();
		rightTree.root = this.root.getRight();
		int rightHeight = rightTree.height();
		
		//determine if one node is longer than the other and return that height and account for the root by adding 1
		if(rightHeight>leftHeight)
			height = rightHeight +1;
		else
			height = leftHeight +1;
		return height;
	}
}
