//Uses 3 different methods to add an element to a hash table

public class HashList implements List
{
	FrontSelfAdjList[] table;
	int method;
	
	public HashList(int num)
	{
		method = num;
		table = new FrontSelfAdjList[256];
	}
	
	public void add(String element)
	{
		int hashVal = -1;
		switch(method)
		{
		case 1: hashVal = hashMethod1(element);
		break;
		case 2: hashVal = hashMethod2(element);
		break;
		case 3: hashVal = hashMethod3(element);
		break;
		}
		if(table[hashVal]==null)
			table[hashVal] = new FrontSelfAdjList();
		table[hashVal].add(element);
	}
	
	public void remove(String element)
	{
		int hashVal = -1;
		switch(method)
		{
		case 1: hashVal = hashMethod1(element);
		break;
		case 2: hashVal = hashMethod2(element);
		break;
		case 3: hashVal = hashMethod3(element);
		break;
		}
		table[hashVal].remove(element);
	}
	
	public int hashMethod1(String element)
	{
		//sum of each character and mod 255
		int sum = 0;
		for(int i = 0; i < element.length(); i++)
		{
			sum = sum + (int)element.charAt(i);
		}
		return sum % 256;
	}
	
	public int hashMethod2(String element)
	{
		//val of first char
		return (int) element.charAt(0);
	}
	
	public int hashMethod3(String element)
	{
		//given java hash function
		int hash = 0;
		for(int i = 0;i<element.length();i++)
			hash = (hash *31) + element.charAt(i);
		
		return hash & 255;
	}
	
	public int totalNumWords()
	{
		int total = 0;
		for (int i = 0;i<256;i++)
		{
			if(table[i]!= null)
				total = total + table[i].totalNumWords();
		}
		return total;
	}
	
	public int totalWords()
	{
		int total = 0;
		for (int i = 0;i<256;i++)
		{
			if(table[i]!= null)
				total = total + table[i].totalWords();
		}
		return total;
	}
	
	public long getRefCount()
	{
		long total = 0;
		for (int i = 0;i<256;i++)
		{
			if(table[i]!= null)
				total = total + table[i].getRefCount();
		}
		return total;
	}
	public long getCompCount()
	{
		long total = 0;
		for (int i = 0;i<256;i++)
		{
			if(table[i]!= null)
				total = total + table[i].getCompCount();
		}
		return total;
	}
	
	public void reset()
	{
		for(int i = 0; i<256; i++)
		{
			if(table[i]!=null)
				table[i].reset();
		}
	}
	
	public boolean isEmpty()
	{
		for (int i = 0;i<256;i++)
		{
			if(table[i]!=null)
				if(!table[i].isEmpty())
					return false;
		}
		return true;
	}
	
	public int getMax()
	{
		int max = 0;
		for (int i = 0;i<256;i++)
		{
			if(table[i]!=null)
				if(table[i].totalWords()>max)
					max = table[i].totalWords();
		}
		return max;
	}
	
	public int getMin()
	{
		int min;
		//checks for a list in the first position of the array, if none the min is 0
		if(table[0]!=null)
			min = table[0].totalWords();
		else 
			min = 0;
		for (int i = 0;i<256;i++)
		{
			if(table[i]!=null)
			{
				if(table[i].totalWords()<min)
					min = table[i].totalWords();
			}
			else 
				min = 0;
		}
		return min;
	}
	
	public double getAvg()
	{
		//returns total words in the hash table
		double sum = totalWords();
		//to get the average divide
		return sum/256;
	}
	
	public double stdDev()
	{
		double sum = 0;
		for (int i = 0; i<256;i++)
		{
			if(table[i]!=null)
				sum = sum + (Math.pow((double)table[i].totalWords()-getAvg(),2.));
		}
		return sum/256;
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
		return "Words: UniqueWords: ReferenceChanges: Comparisons: \n"+totalNumWords()+" "+totalWords()+" "+getRefCount()+" "+getCompCount()+" \nAverage: "+ getAvg() + " Minimum: "+ getMin() + " Maximum: " + getMax()+ " Std Dev: " + stdDev() +" Runtime: ";
	}
	
	public void getArrayVals()
	{
		for(int i = 0;i<256;i++)
		{
			System.out.print(i+":\t");
			if(table[i]!=null)
				System.out.println(table[i].totalNumWords());
			else
				System.out.println(0);
		}
	}
	
}
