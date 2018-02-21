/* 
 * Lab 2, Cecelia Dahlinger
 *  Friday, November 10, 2017
 * This lab consists of four list types: (all consisting HNode)
 * -unsorted
 * -sorted(alphabetically)
 * -Front Self Adjusting
 * -Single Self Adjusting
 * Also a method within the HamletSorter Class to parse a text file
 * and create a queue (queue class and QNode)
 * 
 * 
 */

import java.util.*;
import java.io.*;

public class HamletSorter 
{

	public static void main(String[] args) 
	{
		//final allows to just change the string to different txt file names
		final String FILE_NAME = ("Random 1M.txt");
		// Parse and get initial time to go through the file
		// Then call test method with different params to test each list type
		try 
		{
			System.out.println(FILE_NAME);
			Queue textFile = fileReader(FILE_NAME);
			//test(textFile, "unsorted");
			//test(textFile, "sorted");
			//test(textFile, "new sorted");
			//test(textFile, "front self adjust");
			//test(textFile, "front self adjust with remove2");
			//test(textFile, "single self adjust");
			//test(textFile, "skip");
			//test(textFile, "hash 1");
			//test(textFile, "hash 2");
			//test(textFile, "hash 3");
			
			//getHashArrays(textFile);
			
			testBinaryTree(textFile);
		}
		catch (IOException e) 
		{
			System.out.println("oops");
		}
		
	}
	
	public static Queue fileReader (String FILE_NAME) throws IOException
	// Parses file and places each word without punctuation into an 
	// unsorted list(queue) with possible duplicates. Gets/eliminates preliminary time
	// to parse the list.
	{
		Scanner parser = new Scanner(new File(FILE_NAME));
		// create queue, first in first out, for the txt file(easier 
		// to manage each word as it's own object)
		Queue textFile = new Queue();
		String word;
		while (parser.hasNext())
		// While there are still word in the file, find beginning and 
		// end of the letters. Toss the word of the length becomes 0.
		{
			word = parser.next();
			//System.out.println(word);
			int firstLetter = 0; //use this to find the position of the first letter
			int lastLetter = word.length()-1; //use this to find the position of the last letter
			
			while(!"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(Character.toString(word.charAt(firstLetter))) )
			{
				// Account for "words" that do not contain any letters (ex:"--")
				// Doesn't let it go past the last char in the "word"
				firstLetter++;
				if (firstLetter>lastLetter)
					break;
			}
			
			while(!"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(Character.toString(word.charAt(lastLetter))))
			{
				// Doesn't let it go past the 0 index char in the "word"
				if(lastLetter>0)
					lastLetter--;
				else
					break;
			}
			
			// Only allows word with letters to be added to the queue
			if (firstLetter<=lastLetter)
			{
				word = word.substring(firstLetter, lastLetter+1);
				//System.out.println(word);
				textFile.enqueue(word.toLowerCase());
			}
			
		}
		//System.out.println("Done with Queue.");
		return textFile;
	}
	
	public static void test(Queue textFile, String type)
	{
		//Make sure the text File is at the beginning before adding
		textFile.restore();
		//To create a list with the properties of the list type chosen, a switch statement was implemented
		//List is an interface implemented by all list types
		List newList;
		switch (type)
		{
			case "unsorted": 	
			{
				System.out.println("Unsorted: ");
				newList = new UnsortedList();
				break;
			}
			case "sorted": 		
			{
				System.out.println("Sorted: ");
				newList = new SortedList();
				break;
			}
			case "new sorted": 	
			{
				System.out.println("NewSorted: ");
				newList = new NewSortedList();
				break;
			}
			case "front self adjust": 	
			{
				System.out.println("FrontSelfAdj: ");
				newList = new FrontSelfAdjList();
				break;
			}
			case "front self adjust with remove2":
			{
				System.out.println("FrontSelfAdj: ");
				newList = new FrontSelfAdjList(true);
				break;
			}
			case "single self adjust": 	
			{
				System.out.println("SingleSelfAdj: ");
				newList = new SingleSelfAdjList();
				break;
			}
			case "skip": 		
			{
				System.out.println("SkipList: ");
				newList = new SkipList();
				break;
			}
			case "hash 1":	 	
			{
				System.out.println("Hashed Method 1: ");
				newList = new HashList(1);
				break;
			}
			case "hash 2":	 	
			{
				System.out.println("Hashed Method 2: ");
				newList = new HashList(2);
				break;
			}
			case "hash 3":	 	
			{
				System.out.println("Hashed Method 3: ");
				newList = new HashList(3);
				break;
			}
			default:
			{
				System.out.println("Something went wrong! I have initialized newList to be an unsorted list");
				newList = new UnsortedList();
			}
		}
		
		//Get the start time for the add function
		double runtimeStart = System.currentTimeMillis();
		
		// call the .add() method of the list as long as there is something to add
		while(!textFile.isEmpty())
		{
			newList.add(textFile.dequeue());
		}

		//calculate elapsed time
		double runtime =(System.currentTimeMillis()-runtimeStart)/1000;
		
		System.out.print(newList);
		System.out.println(runtime + " secs");
		
		//Prep for removing by restoring the textfile and reseting the values of refchanges and comps
		textFile.restore();
		newList.reset();
		
		//Get the start time for the remove function
		runtimeStart = System.currentTimeMillis();
		
		//call the remove function as long as there are words in the text file
		while(!textFile.isEmpty())
		{
			newList.remove(textFile.dequeue());
		}
		
		//calculate elapsed time
		runtime =(System.currentTimeMillis()-runtimeStart)/1000;
		
		System.out.println("Remove:");
		
		System.out.print(newList);
		System.out.println(runtime + " secs \n");
	}
	
	public static void getHashArrays(Queue textFile)
	{
		textFile.restore();
		HashList newList = new HashList(1);
		
		// call the .add() method of the list as long as there is something to add
		while(!textFile.isEmpty())
		{
			newList.add(textFile.dequeue());
		}
		System.out.println("Hash Method 1: ");
		newList.getArrayVals();
		
		textFile.restore();
		HashList newList2 = new HashList(2);
		
		// call the .add() method of the list as long as there is something to add
		while(!textFile.isEmpty())
		{
			newList2.add(textFile.dequeue());
		}
		System.out.println("Hash Method 2: ");
		newList2.getArrayVals();
		
		textFile.restore();
		HashList newList3 = new HashList(3);
		
		// call the .add() method of the list as long as there is something to add
		while(!textFile.isEmpty())
		{
			newList3.add(textFile.dequeue());
		}
		System.out.println("Hash Method 3: ");
		newList3.getArrayVals();
		
		
	}
	
	public static void testBinaryTree(Queue textFile)
	{
		textFile.restore();
		BinaryTree newList = new BinaryTree();
		
		double runtimeStart = System.currentTimeMillis();
		while(!textFile.isEmpty())
		{
			newList.add(textFile.dequeue());
		}
		
		double runtime =(System.currentTimeMillis()-runtimeStart)/1000;
		
		System.out.println("Binary Tree:");
		System.out.println("UniqueWords: " + newList.totalWords());
		System.out.println("TotalWords: " + newList.totalNumWords());
		System.out.println("Height: "+newList.height());
		System.out.println("Runtime: " +runtime+" secs");
	}
}
