package solution;
import java.io.Console;
import java.io.InputStream;
import java.util.*;

public class RatFindCheeseInMaze 
{
	
	
	private Place[][] maze;
	private static Scanner in;
	private int X=-1,Y=-1;
	private Place mousespot;
	private int mouseX=-1;
	private int mouseY=-1;
	private Place cheezespot;
	private int cheezeX=-1;
	private int cheezeY=-1;
	private static Random rndm;
	RatFindCheeseInMaze(){
		
	}
	public static Scanner getScanner()
	{
		if(in==null)
			return (in=new Scanner(System.in));
		return in;
	}
	public static Random getRandomGenerator()
	{
		if(rndm==null)
			return (rndm=new Random());
		return rndm;
	}
	
	private static boolean mazeagain=true;
	private static Place cords;
	
	class Spot implements Place{
		private int curx,cury;
		private final boolean isWall;
		public final Place INVALID_SPOT=new Spot(-1,-1);		
		public Spot(int  x, int y) {
			isWall=(x>=0&&x<X)&& (y>=0&&y<Y)?getRandomGenerator().nextBoolean():true;
			this.curx=x;
			this.cury=y;
		}
		public Place goWest(){
			return curx-1<0?INVALID_SPOT:maze[curx-1][cury];
		}
		public Place goEast(){
			return curx+1>X-1?INVALID_SPOT:maze[curx+1][cury];
		}
		public Place goSouth(){
			return cury+1>Y-1?INVALID_SPOT:maze[curx][cury+1];
		}
		public Place goNorth(){
			return cury-1<0?INVALID_SPOT:maze[curx][cury-1];
		}
		

		// Returns true only for the special "Wall" place
		public boolean isWall(){
			return isWall;
		}

		// Returns true only for the special "Cheese" place
		public boolean isCheese(){
			return curx==cheezeX && cury==cheezeY;
		}
	}
	public static void main(String[] args)
	{
		RatFindCheeseInMaze mazgameobj;
		while(mazeagain)
	  {
		mazgameobj=new RatFindCheeseInMaze();	
		do{
			System.out.println("To Design the maze..\n Enter a valid size of "
					+ "maze: ");
			
		}while(!mazgameobj.setMazeCords());
		mazgameobj.designMaze();
		System.out.println("Maze looks like this..");
		mazgameobj.displayMaze();
		do{
			System.out.println("cheese; x,y: ");
			
		}while(!mazgameobj.setCheese());
		
		System.out.println("Enter the position of the mouse ");
		do{
			System.out.println("mouse; x,y: ");
			
		}while(!mazgameobj.setMouse());
		mazgameobj.displayMaze();
		System.out.println(mazgameobj.findCheese(mazgameobj.getMouseCords()));
		System.out.println("\n====\n maze again ? ");
		mazeagain=mazgameobj.getScanner().next().matches("[t|T|1|y].*")?true:false;
	  }	
	}
	private Place getMouseCords() {
		// TODO Auto-generated method stub
		return this.mousespot;
	}
	private boolean setCheese() {
		// TODO Auto-generated method stub
		try{
			final String cord=getCords();
		int chX=Integer.parseInt(cord.split(",")[0])-1; // because human counting start from 1 not 0;
		int chY=Integer.parseInt(cord.split(",")[1])-1;
		
		if (chX>=0 && chY>=0 && 
				chX<this.X && chY<this.Y &&
					this.getSpot(chX,chY)!=null && 
					!this.getSpot(chX,chY).isWall())
		{
			this.cheezeX=chX;// because human counting start from 1 not 0
			this.cheezeY=chY;// so first position is 1,1 not 0,0
			cheezespot=this.getSpot(this.cheezeX,this.cheezeY);
		return true;
		}else 
			return false;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
	public Place getSpot(int x2, int y2) {
		// TODO Auto-generated method stub
		return (x2>=0&&x2<this.X) &&
				(y2>=0&&y2<this.Y)? this.maze[x2][y2]:null;
	}
	private boolean setMouse() {
		try{
			String cord=getCords();
		int mouseX=Integer.parseInt(cord.split(",")[0])-1; // because human counting start from 1 not 0;
		int mouseY=Integer.parseInt(cord.split(",")[1])-1;
		
		if (mouseX>=0 && mouseY>=0 && 
				mouseX<X && mouseY<Y &&
				this.getSpot(mouseX,mouseY)!=null && !this.getSpot(mouseX,mouseY).isWall()){
			this.mouseX=mouseX;
			this.mouseY=mouseY;
			mousespot=this.getSpot(this.mouseX,this.mouseY);
		return true;
		}
		else 
			return false;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}

	private boolean setMazeCords() {
		try{
			String cord=getCords();
		this.X=Integer.parseInt(cord.split(",")[0]);
		this.Y=Integer.parseInt(cord.split(",")[1]);
		if (this.X>0 && this.Y>0 && this.X<1000 && this.Y<1000 )
		return true;
		else 
			return false;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}

	private String getCords() {
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

	private void displayMaze() {
		// TODO Auto-generated method stub
		System.out.println("\tN↑");
		System.out.println("←W\t✣\tE→");
		System.out.println("\tS↓");
		System.out.println("--------------------------");
		for(int indx=0;indx<maze.length;indx++){
			for(int jndx=0;jndx<maze[indx].length;jndx++)
				System.out.print(
					"|"+(maze[indx][jndx].isWall()?
							"█":
					   maze[indx][jndx].isCheese()?
							   "░":
								   (indx==this.mouseX&&jndx==this.mouseY)?
										   "⊗":" ")+"|");//" all of a sudden, a bee 🐝!"
			System.out.println();
		}
		System.out.println("--------------------------");
	}
	private boolean[][] maz;
	private void designMaze()
	{
			//i know dbl final is reptitive 
			//but i was testing something
		/*//maz = new boolean[X + 1][Y + 1];

		for (int indx = 0; indx < X + 1; indx++) {
			maz[indx][Y + 1] = false;
			maz[indx][0] = false;
		}
		for (int indx = 0; indx < Y + 1; indx++) {
			maz[X + 1][indx] = false;
			maz[0][indx] = false;
		}
		
*/
		maze=new Place[X][Y];
		for (int indx = 0; indx < X; indx++) 
			for (int jndx = 0; jndx < Y; jndx++) 
				maze[indx][jndx]=new Spot(indx,jndx);	
		
	}
	public Place[][] getMaze(){
		return maze;
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

