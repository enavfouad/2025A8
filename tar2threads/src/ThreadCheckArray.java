import java.util.ArrayList;
/**
 @author enabf
 * This class implements the Runnable interface to check if a subset of an array
 * adds up to a target value `b`. It uses recursion and multiple threads to 
 * perform the subset sum check.
 */
 
public class ThreadCheckArray implements Runnable 
{
	private boolean flag;
	private boolean [] winArray;// Array to track the selected elements for the subset
	SharedData sd;// Shared data object that holds the array, target value `b`, and other shared state
	ArrayList<Integer> array;
	int b;
    /**
     * Constructor for the ThreadCheckArray class.
     * 
     * @param sd SharedData object that contains the array, target value, and shared state
     */
	
	public ThreadCheckArray(SharedData sd) 
	{
		this.sd = sd;	
		synchronized (sd) 
		{
			array = sd.getArray();
			b = sd.getB();
		}		
		winArray = new boolean[array.size()];
	}
	 /**
     * Recursive function to check for a subset sum that equals `b`.
     * This function works by considering two possibilities at each step:
     * - Including the current element in the subset
     * - Not including the current element in the subset
     * 
     * @param n The index of the current element being considered
     * @param b The remaining target sum we need to achieve
     */
	void rec(int n, int b)
	{
		synchronized (sd) 
		{
			if (sd.getFlag())
				return;
		}	
		if (n == 1)// Base case: Only one element left to check
		{
			// If the target sum is 0 or equal to the current element, a solution is found
			if(b == 0 || b == array.get(n-1))
			{
				flag = true;
				synchronized (sd) 
				{
					sd.setFlag(true);
				}			
			}
			if (b ==array.get(n-1) )
				winArray[n-1] = true;
			return;
		}

        // Recursive calls: One where we include the current element and one where we don't
		rec(n-1, b - array.get(n-1));
		if (flag)
			winArray[n-1] = true;
		synchronized (sd) 
		{
			if (sd.getFlag())
				return;
		}	
		rec(n-1, b);
	}
	  /**
     * The `run` method is the entry point for the thread.
     * It starts the recursive checking process either by including the last element or not.
     */
	public void run() {
		if (array.size() != 1)
			if (Thread.currentThread().getName().equals("thread1"))
				rec(array.size()-1, b - array.get(array.size() - 1));
			else 
				rec(array.size()-1, b);
		if (array.size() == 1)
			
			if (b == array.get(0) && !flag)
			{
				winArray[0] = true;
				flag = true;
				synchronized (sd) 
				{
					sd.setFlag(true);
				}
			}
		if (flag)
		{
			if (Thread.currentThread().getName().equals("thread1"))
				winArray[array.size() - 1] = true;
			synchronized (sd) 
			{
				sd.setWinArray(winArray);
			}	
		}
	}
}
