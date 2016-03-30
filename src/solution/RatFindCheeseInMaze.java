package solution;
import java.io.Console;
import java.io.InputStream;
import java.util.*;


public class RatFindCheeseInMaze 
{
	public static final 
	public Place[][] maze;
	private static Scanner in;
	private static Scanner getScanner()
	{
		if(in==null)
			new Scanner(System.in);
		return in;
	}
	private static boolean mazeagain=true;
	private static Place cords;
	public static void main(String[] args)
	{
		RatFindCheeseInMaze mazgameobj;
		while(mazeagain)
	  {
		mazgameobj=new RatFindCheeseInMaze();	
		do{
			System.out.println("To Design the maze..\n Enter the size of maze: ");
			
		}while(!mazgameobj.setMazeCords(mazgameobj.getCords()));
		mazgameobj.designMaze();
		System.out.println("Maze looks like this..");
		mazgameobj.displayMaze();
		System.out.println("Enter the position of the mouse and cheese");
		do{
			System.out.println("mouse; x,y: ");
			
		}while(!mazgameobj.setMouse(mazgameobj.getCords()));
		do{
			System.out.println("cheese; x,y: ");
			
		}while(!mazgameobj.setCheese(mazgameobj.getCords()));
		findCheese(mazgameobj.getMouseCords());
		System.out.println("maze again ? true/false ");
		mazeagain=mazgameobj.getScanner().next().matches("[t|T|1|y].*")?true:false;
	  }	
	}

	private boolean setMazeCords(String cord) {
		try{
		this.X=Integer.parseInt(cord.split(",")[0]);
		this.Y=Integer.parseInt(cord.split(",")[1]));
		if (this.X>0 &&this.Y>0 && this.X)
		return true;
		else 
			return false;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}

	private static String getCords() {
		// TODO Auto-generated method stub
		Scanner in=getScanner();
		System.out.print("x: ");
		while(!in.hasNextInt())
		{
		in.next();
		}
		int x=in.nextInt();
		System.out.print("\ny: ");
		while(!in.hasNextInt())
		{
		in.next();
		}
		int y=in.nextInt();
		return x+","+y;
		
	}

	private static void displayMaze() {
		// TODO Auto-generated method stub
		for(int indx=0;indx<maze.length;indx++){
			for(int jndx=0;jndx<maze[indx].length;jndx++)
				System.out.print(maze[indx][jndx]);
			System.out.println();
		}
	}
	private boolean[][] maz;
	private void designMaze()
	{
			//i know dbl final is reptitive 
			//but i was testing something
		maz = new boolean[X + 1][Y + 1];

		for (int indx = 0; indx < X + 1; indx++) {
			maz[indx][Y + 1] = false;
			maz[indx][0] = false;
		}
		for (int indx = 0; indx < Y + 1; indx++) {
			maz[X + 1][indx] = false;
			maz[0][indx] = false;
		}
		

	}
	public Place[][] getMaze(){
		return maze[][];
	}

//this is traversal.. and also backtracking
//Basically the tricky part is to backtrack and keep track of visted nodes



private Stack<Place> visterstack;
java.util.HashSet<Place> visitedset=new HashSet<Place>();;
private StringBuilder soln= new StringBuilder("start-> ");
// Return a string of the letters NSEW which, if used to traverse the
// the maze from startingPoint, will reach a Place where isCheese() is
// true.  Return null if you can't find such a path.
public String findCheese(Place startingPoint) {
	if (startingPoint==null)
	return null;
	if (startingPoint.isCheese())
	{
	    //soln.append("->Cheese!");
	    return soln.toString();
	}
	
Stack<Place> visiterstack=new Stack<Place>();
Place curplace=null;
  
visiterstack.push(startingPoint);
  while(!visiterstack.isEmpty())// Traversing through all the elements 
  {
      curplace=getNextPlace(visiterstack.peek());// TODO
      if (curplace==null)
      {
          soln.deleteCharAt(soln.length()-1);
          visiterstack.pop();
          continue;
      }
      if(curplace.isCheese())
      {
          //soln.append("->Cheese!!");
          return soln.toString();
      }
      else{
          visiterstack.push(curplace);
      }
      
  }
  return null;
}
private Place getNextPlace(Place curplace)
{
    if (curplace==null)
    return null;

    if (!(curplace.goNorth()).isWall() && !visitedset.contains(curplace.goNorth()) )
      {
          soln.append("N");
          visitedset.add(curplace.goNorth());
      return curplace.goNorth();
      }
    if (!(curplace.goSouth()).isWall() && !visitedset.contains(curplace.goSouth()) )
      {
      soln.append("S");
      visitedset.add(curplace.goSouth());
      return curplace.goSouth();
      }
    if (!(curplace.goEast()).isWall() && !visitedset.contains(curplace.goEast()) )
      {
      soln.append("E");
      visitedset.add(curplace.goEast());
      return curplace.goEast();
      }
    if (!(curplace.goWest()).isWall() && !visitedset.contains(curplace.goWest()) )
      {
      soln.append("W");
      visitedset.add(curplace.goWest());
      return curplace.goWest();
      }

  return null;
}

}
interface Place {
	public final int XORD;
	public final int YORD;

	// Return the adjacent Place in the given direction
	public Place goNorth();
	public Place goSouth();
	public Place goEast();
	public Place goWest();

	// Returns true only for the special "Wall" place
	public boolean isWall();

	// Returns true only for the special "Cheese" place
	public boolean isCheese();
}

class Spot implements Place{
	
	public Place goNorth(){
		return maze[curx-1<=0?0:curx-1][cury];
	}
	public Place goSouth(){
		return maze[curx+1>=Place.XORD?Place.XORD:curx+1][cury];
	}
	public Place goEast(){
		return maze[curx][cury+1>=Place.YORD?Place.YORD:cury+1];
	}
	public Place goWest(){
		return maze[curx][cury-1<=0?0:cury-1];
	}

	// Returns true only for the special "Wall" place
	public boolean isWall(){
		return !this.maz[this.x][this.y];
	}

	// Returns true only for the special "Cheese" place
	public boolean isCheese(){
		return this.x==cheezex && this.y==cheezey;
	}
}