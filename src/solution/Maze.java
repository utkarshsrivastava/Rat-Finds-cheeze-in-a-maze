package solution;
import java.util.Stack;
import java.util.*;
public class Maze {

}
//this is traversal.. and also backtracking
//Basically the tricky part is to backtrack and keep track of visted nodes




class Mouse {
public Mouse() {}
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
	    return soln;
	}
	
	visterstack=new Stack<Place>();
  
  
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
      return nextplace.goSouth();
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