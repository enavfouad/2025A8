import java.util.ArrayList;
/**
  @author enabf 
  The SharedData class is used to store shared data that is accessed by multiple threads.
  It contains the array of integers, the target sum, and a flag to indicate whether 
  a solution has been found. It also tracks the elements selected in the subset 
  that sums up to the target value.
 */
public class SharedData 
{
	private  ArrayList<Integer> array;
	private boolean [] winArray;
	private boolean flag;
	private final int b;

    /**
     * Constructor to initialise the SharedData object with the array and target sum.
     
      @param array The ArrayList of integers to check for a subset sum
      @param b The target sum
     */
	public SharedData(ArrayList<Integer> array, int b) {
		
		this.array = array;
		this.b = b;
	}

	public boolean[] getWinArray() 
	{
		return winArray;
	}

	public void setWinArray(boolean [] winArray) 
	{
		this.winArray = winArray;
	}
	
    /**
     * Gets the array of integers to check for a subset sum.
     
     @return The ArrayList of integers
     */

	public ArrayList<Integer> getArray() 
	{
		return array;
	}

    /**
     * Gets the target sum to find in the array.
      
     * @return The target sum `b`
     */

	public int getB() 
	{
		return b;
	}
	 /**
     * Gets the flag that indicates if a solution has been found.
      
     * @return True if a solution has been found, false otherwise
     */
	public boolean getFlag() 
	{
		return flag;
	}
    /**
     * Sets the flag that indicates if a solution has been found.
     
     * @param flag True if a solution has been found, false otherwise
     */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
