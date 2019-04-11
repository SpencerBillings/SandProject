package sand.pack;
import java.awt.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class SandLab
{
  //Step 4,6
  //add constants for particle types here
  public static final int EMPTY = 0;
  public static final int METAL = 1;
  public static final int SAND = 2;
  public static final int WATER = 3;
  public static final int ACID = 4;
  public static final int LAVA = 5;
  public static final int ROCK = 6;
  public static final int OBSIDIAN = 7;
  public static final int STEAM = 8;
  public static final int DYNOMITE = 9;
  
  //do not add any more fields below
  private int[][] grid;
  private SandDisplay display;
  
  
  /**
   * Constructor for SandLab
   * @param numRows The number of rows to start with
   * @param numCols The number of columns to start with;
   */
  public SandLab(int numRows, int numCols)
  {
    String[] names;
    // Change this value to add more buttons
    //Step 4,6
    names = new String[10];
    // Each value needs a name for the button
    names[EMPTY] = "Empty";
    names[METAL] = "Metal";
    names[SAND] = "Sand";
    names[WATER] = "Water";
    names[ACID] = "Acid";
    names[LAVA] = "Lava";
    names[ROCK] = "Rock";
    names[OBSIDIAN] = "Obsidian";
    names[STEAM] = "Steam";
    names[DYNOMITE] = "Dynomite";
    
    //1. Add code to initialize the data member grid with same dimensions
    grid = new int[numRows][numCols];
    
    display = new SandDisplay("Falling Sand", numRows, numCols, names);
  }
  
  //called when the user clicks on a location using the given tool
  private void locationClicked(int row, int col, int tool)
  {
    //2. Assign the values associated with the parameters to the grid
	  grid[row][col] = tool;
  }

  //copies each element of grid into the display
  public void updateDisplay()
  {
      //Step 3
   //Hint - use a nested for loop
    for (int col = 0; col < grid[0].length; col++)
    {
    	for (int row = 0; row < grid.length; row++)
    	{
    		if (grid[row][col] == 0)
    		{
    			display.setColor(row, col, Color.BLACK);
    		}
    		else if (grid[row][col] == 1)
    		{
    			display.setColor(row, col, Color.GRAY);
    		}
    		else if (grid[row][col] == 2)
    		{
    			display.setColor(row, col, Color.YELLOW);
    		}
    		else if (grid[row][col] == 3)
    		{
    			display.setColor(row, col, Color.BLUE);
    		}
    		else if (grid[row][col] == 4)
    		{
    			display.setColor(row,  col,  Color.GREEN);
    		}
    		else if (grid[row][col] == 5)
    		{
    			display.setColor(row,  col,  Color.ORANGE);
    		}
    		else if (grid[row][col] == 6)
    		{
    			display.setColor(row,  col,  Color.LIGHT_GRAY);
    		}
    		else if (grid[row][col] == 7)
    		{
    			display.setColor(row,  col,  Color.DARK_GRAY);
    		}
    		else if (grid[row][col] == 8)
    		{
    			display.setColor(row,  col,  Color.WHITE);
    		}
    		else if (grid[row][col] == 9)
    		{
    			display.setColor(row, col, Color.RED);
    		}
    	}
    }
  }

  //Step 5,7
  //called repeatedly.
  //causes one random particle in grid to maybe do something.
  public void step()
  {
    //Remember, you need to access both row and column to specify a spot in the array
    //The scalar refers to how big the value could be
    int randomRow = (int) (Math.random() * grid.length - 1);
    int randomCol = (int) (Math.random() * grid[0].length);
    //remember that you need to watch for the edges of the array
    
    // SAND
    if (grid[randomRow][randomCol] == 2 && grid[randomRow + 1][randomCol] == 0)
    {
    	grid[randomRow][randomCol] = 0;
    	grid[randomRow + 1][randomCol] = 2;
    }
    else if (grid[randomRow][randomCol] == 2 && grid[randomRow + 1][randomCol] == 3)
    {
    	grid[randomRow][randomCol] = 3;
    	grid[randomRow + 1][randomCol] = 2;
    }
    
    //WATER
    if (grid[randomRow][randomCol] == 3 && grid[randomRow + 1][randomCol] == 5)
    {
    	grid[randomRow][randomCol] = 8;
    	grid[randomRow + 1][randomCol] = 6;
    }
    else if (randomCol != grid[0].length - 1 && grid[randomRow][randomCol] == 3 && grid[randomRow][randomCol + 1] == 5)
    {
    	grid[randomRow][randomCol] = 8;
    	grid[randomRow][randomCol + 1] = 6;
    }
    else if (randomCol != 0 && grid[randomRow][randomCol] == 3 && grid[randomRow][randomCol - 1] == 5)
    {
    	grid[randomRow][randomCol] = 8;
    	grid[randomRow][randomCol - 1] = 6;
    }
    else if (grid[randomRow][randomCol] == 3 && grid[randomRow + 1][randomCol] == 0)
    {
    	grid[randomRow][randomCol] = 0;
    	grid[randomRow + 1][randomCol] = 3;
    }
    else if (randomCol != grid[0].length - 1 && grid[randomRow][randomCol] == 3 && grid[randomRow][randomCol + 1] == 0)
    {
    	grid[randomRow][randomCol] = 0;
    	grid[randomRow][randomCol + 1] = 3;
    }
    else if (randomCol != 0 && grid[randomRow][randomCol] == 3 && grid[randomRow][randomCol - 1] == 0)
    {
    	grid[randomRow][randomCol] = 0;
    	grid[randomRow][randomCol - 1] = 3;
    }
    
    //ACID
    if (grid[randomRow][randomCol] == 4 && grid[randomRow + 1][randomCol] == 0)
    {
    	grid[randomRow][randomCol] = 0;
    	grid[randomRow + 1][randomCol] = 4;
    }
    else if (grid[randomRow][randomCol] == 4 && (grid[randomRow + 1][randomCol] == 1 || grid[randomRow + 1][randomCol] == 2 || grid[randomRow + 1][randomCol] == 3 || grid[randomRow + 1][randomCol] == 6 || grid[randomRow + 1][randomCol] == 7))
    {
    	grid[randomRow][randomCol] = 0;
    	grid[randomRow + 1][randomCol] = 4;
    }
    else if (randomCol != grid[0].length - 1 && grid[randomRow][randomCol] == 4 && grid[randomRow][randomCol + 1] == 0)
    {
    	grid[randomRow][randomCol] = 0;
    	grid[randomRow][randomCol + 1] = 4;
    }
    else if (randomCol != 0 && grid[randomRow][randomCol] == 4 && grid[randomRow][randomCol - 1] == 0)
    {
    	grid[randomRow][randomCol] = 0;
    	grid[randomRow][randomCol - 1] = 4;
    }
    
    //LAVA
    if (grid[randomRow][randomCol] == 5 && grid[randomRow + 1][randomCol] == 3)
    {
    	grid[randomRow][randomCol] = 5;
    	grid[randomRow + 1][randomCol] = 7;
    }
    else if (randomCol != grid[0].length - 1 && grid[randomRow][randomCol] == 5 && grid[randomRow][randomCol + 1] == 3)
    {
    	grid[randomRow][randomCol] = 5;
    	grid[randomRow][randomCol + 1] = 7;
    }
    else if (randomCol != 0 && grid[randomRow][randomCol] == 5 && grid[randomRow][randomCol - 1] == 3)
    {
    	grid[randomRow][randomCol] = 5;
    	grid[randomRow][randomCol - 1] = 7;
    }
    else if (grid[randomRow][randomCol] == 5 && grid[randomRow + 1][randomCol] == 0)
    {
    	grid[randomRow][randomCol] = 0;
    	grid[randomRow + 1][randomCol] = 5;
    }
    else if (grid[randomRow][randomCol] != 7 && grid[randomRow][randomCol] == 5 && (grid[randomRow + 1][randomCol] == 1 || grid[randomRow + 1][randomCol] == 2 || grid[randomRow + 1][randomCol] == 3 || grid[randomRow + 1][randomCol] == 4 || grid[randomRow + 1][randomCol] == 6))
    {
    	grid[randomRow][randomCol] = 0;
    	grid[randomRow + 1][randomCol] = 5;
    }
    else if (grid[randomRow][randomCol] != 7 && randomCol != grid[0].length - 1 && grid[randomRow][randomCol] == 5 && grid[randomRow][randomCol + 1] == 0)
    {
    	grid[randomRow][randomCol] = 0;
    	grid[randomRow][randomCol + 1] = 5;
    }
    else if (grid[randomRow][randomCol] != 7 && randomCol != 0 && grid[randomRow][randomCol] == 5 && grid[randomRow][randomCol - 1] == 0)
    {
    	grid[randomRow][randomCol] = 0;
    	grid[randomRow][randomCol - 1] = 5;
    }
    
    //STEAM
    if (randomRow != 0 && grid[randomRow][randomCol] == 8 && grid[randomRow - 1][randomCol] == 0)
    {
    	grid[randomRow][randomCol] = 0;
    	grid[randomRow - 1][randomCol] = 8;
    }
    else if (randomRow != 0 && randomCol != grid[0].length - 1 && grid[randomRow][randomCol] == 8 && grid[randomRow][randomCol + 1] == 0)
    {
    	grid[randomRow][randomCol] = 0;
    	grid[randomRow][randomCol + 1] = 8;
    }
    else if (randomRow != 0 && randomCol != 0 && grid[randomRow][randomCol] == 8 && grid[randomRow][randomCol - 1] == 0)
    {
    	grid[randomRow][randomCol] = 0;
    	grid[randomRow][randomCol - 1] = 8;
    }
    
    //DYNOMITE
    if (grid[randomRow][randomCol] == 9)
    {
    	try
    	{
    		System.out.println("3");
    		Thread.sleep(1000);
    		System.out.println("2");
    		Thread.sleep(1000);
    		System.out.println("1");
    		Thread.sleep(1000);
    		System.out.println("BOOM!");
    		grid[randomRow][randomCol] = 0;
    		grid[randomRow - 1][randomCol] = 0;
    		grid[randomRow][randomCol - 1] = 0;
    		grid[randomRow - 1][randomCol - 1] = 0;
    		grid[randomRow + 1][randomCol] = 0;
    		grid[randomRow][randomCol + 1] = 0;
    		grid[randomRow + 1][randomCol + 1] = 0;
    		grid[randomRow - 1][randomCol + 1] = 0;
    		grid[randomRow + 1][randomCol - 1] = 0;
    		grid[randomRow - 2][randomCol + 1] = 0;
    		grid[randomRow - 2][randomCol - 1] = 0;
    		grid[randomRow - 2][randomCol] = 0;
    		grid[randomRow + 2][randomCol + 1] = 0;
    		grid[randomRow + 2][randomCol - 1] = 0;
    		grid[randomRow + 2][randomCol] = 0;
    		grid[randomRow][randomCol + 2] = 0;
    		grid[randomRow][randomCol - 2] = 0;
    		grid[randomRow + 1][randomCol + 2] = 0;
    		grid[randomRow - 1][randomCol + 2] = 0;
    		grid[randomRow + 1][randomCol - 2] = 0;
    		grid[randomRow - 1][randomCol - 2] = 0;
    	} catch (InterruptedException ie)
    	{
    		ie.printStackTrace();
    	}
    }
  }
  
  //do not modify this method!
  public void run()
  {
    while (true) // infinite loop
    {
      for (int i = 0; i < display.getSpeed(); i++)
      {
        step();
      }
      updateDisplay();
      display.repaint();
      display.pause(1);  //wait for redrawing and for mouse
      int[] mouseLoc = display.getMouseLocation();
      if (mouseLoc != null)  //test if mouse clicked
      {
        locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
      }
    }
  }
}
