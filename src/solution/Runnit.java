package solution;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Stack;

public class Runnit {

}
//'/this is a switch case problem... consider first reject.. then sort '


interface ScannerAndSorter {
	// Read and scan another item; return false when there are no more
	// items.
	boolean nextItem();

	// Percentage (0-100) of the current item that's red.
	double redPercentage();

	// Percentage (0-100) of the current item that's green.
	double greenPercentage();

	// Send the current item to the Red bin
	void sendToRed();

	// Send the current item to the Green bin
	void sendToGreen();

	// Send the current item to the Reject bin
	void reject();
}

class DecorationSorter {
	ScannerAndSorter sorter;

	public DecorationSorter(ScannerAndSorter sorter) {
		this.sorter = sorter;
	}

	// Sort the decorations that 'sorter' reads.
	void sort() {
	//	... fill this in ...
	//how do I get teh next item? where is the reader in the interface that returns the next item???  
	if (sorter==null || sorter.nextItem()==false) 
	return ;
	    while(sorter.nextItem())
	    {
	        /*Red, if the decoration is more than 35% red
Green, if the decoration is more than 30% green
Reject, if the decoration is both more than 35% red and 30% green - these are just too "loud". 
*/
	        //ScannerAndSorter item=sorter.read();
	        if (sorter.redPercentage()>35.00)
	            if (sorter.greenPercentage()>30)
	            sorter.reject();
	            else
	            sorter.sendToRed();
	       else if (sorter.greenPercentage()>30)
	            sorter.sendToGreen();
	    }
	    
	}
}
//this is a DFS problem
//i will use a stack to traverse.. at every point, I will make a new String buffer, and either when i get a wall
//or cheese, i will continue to visit all the "places"
//i  will simply do DFS.. and mark the nod i visited as boolean-> visited=t
//char to my strig
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
};

class Mouse {
private HashSet<Place> visited=new HashSet<Place>();
//private HashMap<Place,Drctn> nearby=new HashMap<Place,Drctn>();
public Mouse() {}
private StringBuilder soln=new StringBuilder("start->");
Drctn d;
// Return a string of the letters NSEW which, if used to traverse the
// the maze from startingPoint, will reach a Place where isCheese() is
// true.  Return null if you can't find such a path.
public String findCheese(Place startingPoint) {
if (startingPoint==null)
	return null;

visited.add(startingPoint);
Stack<Place> vstdstack=new Stack<Place>();
vstdstack.add(startingPoint);
//Stack<c> pather="start->";
while(!vstdstack.isEmpty())
{
	Place curpos=getNextPlace(vstdstack.peek());
    if (curpos==null)
    {
        vstdstack.pop();
        soln.deleteCharAt(soln.length()-1);
        continue;
    }
    if(curpos.isCheese())
        return soln.toString();
    else 
     vstdstack.push(curpos);
    
}
return null;
}
Place getNextPlace(Place next)
{
    
 
//for(Entry pl : nearby.entrySet())
   if (!visited.contains(next.goNorth()) && !(next.goNorth()).isWall()){
       soln.append("N");
       visited.add(next.goNorth());
       //nearby.clear();
       return next.goNorth();
    }
   else if (!visited.contains(next.goEast()) && !next.goEast().isWall()){
       soln.append("E");
       visited.add(next.goEast());
       //nearby.clear();
       return next.goEast();
    }
   else if (!visited.contains(next.goNorth()) && !next.goNorth().isWall()){
       soln.append("N");
       visited.add(next.goNorth());
       //nearby.clear();
       return next.goNorth();
    }
   else if (!visited.contains(next.goNorth()) && !next.goNorth().isWall()){
       soln.append("N");
       visited.add(next.goNorth());
       //nearby.clear();
       return next.goNorth();
    
    //nearby.clear();
   return null;
}  
}
}
/*
 * 
 * 
 * Interview Zen
Home
Create an Interview
Nitin Reddy
Total duration: 61:27

Question 1
You're asked to write a controller for a sorting machine for Christmas decorations.  
Decorations arrive at the scanner, and the percentages of red and green are reported.  

Your must write a program that sorts the decorations into one of three bins:  
Red, if the decoration is more than 35% red
Green, if the decoration is more than 30% green
Reject, if the decoration is both more than 35% red and 30% green - these are just too "loud". 

The interfaces you have are the following:


interface ScannerAndSorter {
	// Read and scan another item; return false when there are no more
	// items.
	bool nextItem();

	// Percentage (0-100) of the current item that's red.
	double redPercentage();

	// Percentage (0-100) of the current item that's green.
	double greenPercentage();

	// Send the current item to the Red bin
	void sendToRed();

	// Send the current item to the Green bin
	void sendToGreen();

	// Send the current item to the Reject bin
	void reject();
}

class DecorationSorter {
	ScannerAndSorter sorter;

	public DecorationSorter(ScannerAndSorter sorter) {
		this.sorter = sorter;
	}

	// Sort the decorations that 'sorter' reads.
	void sort() {
		... fill this in ...
	}
}


Implement the sort() function.
/'/this is a switch case problem... consider first reject.. then sort '


interface ScannerAndSorter {
        // Read and scan another item; return false when there are no more
        // items.
        bool nextItem();

        // Percentage (0-100) of the current item that's red.
        double redPercentage();

        // Percentage (0-100) of the current item that's green.
        double greenPercentage();

        // Send the current item to the Red bin
        void sendToRed();

        // Send the current item to the Green bin
        void sendToGreen();

        // Send the current item to the Reject bin
        void reject();
}

class DecorationSorter {
        ScannerAndSorter sorter;

        public DecorationSorter(ScannerAndSorter sorter) {
                this.sorter = sorter;
        }

        // Sort the decorations that 'sorter' reads.
        void sort() {
        //      ... fill this in ...
        //how do I get teh next item? where is the reader in the interface that returns the next item???  
        if (sorter==null || sorter.nextItem()==false) 
        return ;
        //Assuming that the nextItem(), when called automatically sets the focus on the next item
            while(sorter.nextItem())
            {
                //Red, if the decoration is more than 35% red
//Green, if the decoration is more than 30% green
//Reject, if the decoration is both more than 35% red and 30% green - these are just too "loud". 

                if (sorter.redPercentage()>35)
                    if (sorter.greenPercentage()>30)
                    sorter.reject();
                    else
                    sorter.sendToRed();
               else if (sorter.greenPercentage()>30)
                    sorter.sendToGreen();
            }
            
        }
}
0:00 / 17:43
 
play
1x
2x
5x
Question 2
A maze is a group of linked Places.  Each Place has a North, South, East, and West Place adjacent to it.  There are two special pre-defined Place's:  Place Wall represents a wall - the mouse can't go there.  Place Cheese is ... cheese!   The connections between Places are symmetrical - if you start from any Place and go North and then South, you return to where you were.  

To simplify things, the maze has no closed loops - that is, if you start in any Place and follow any path, you eventually either hit a wall or find cheese - you never come back to the Place you started unless you actually retrace your steps.

A mouse starts off in some Place and searches around for Cheese.  When it finds Cheese, it returns a set of directions - a string of the letters NSEW that lead from where it started to the Cheese.

The following framework defines the classes and functions.  Some other code not included here generates a maze by creating a bunch of Place's and linking them.  It then calls mouse(), passing some starting Place from the maze:

interface Place {

	// Return the adjacent Place in the given direction
	public Place goNorth();
	public Place goSouth();
	public Place goEast();
	public Place goWest();

	// Returns true only for the special "Wall" place
	public bool isWall();

	// Returns true only for the special "Cheese" place
	public bool isCheese();
};

class Mouse {
  public Mouse() {}

  // Return a string of the letters NSEW which, if used to traverse the
  // the maze from startingPoint, will reach a Place where isCheese() is
  // true.  Return null if you can't find such a path.
  public String findCheese(Place startingPoint) {
	... fill this in ...
  }
}

Implement findCheese().  You can add any fields or helper methods you need to Mouse.

Extra credit:  Eliminate the "no closed loops" restriction.  That is, change your code so that it works correctly even if there might be a path like SSNEEW that leads the mouse back to the Place it started from.
//this is a DFS problem
// i will use a stack to traverse.. at every point, I will make a new String buffer, and either when i get a wall
//or cheese, i will continue to visit all the "places"
//i  will simply do DFS.. and mark the nod i visited as boolean-> visited=t
// char to my strig
interface Place {

        // Return the adjacent Place in the given direction
        public Place goNorth();
        public Place goSouth();
        public Place goEast();
        public Place goWest();

        // Returns true only for the special "Wall" place
        public bool isWall();

        // Returns true only for the special "Cheese" place
        public bool isCheese();
};

class Mouse {
  private HashSet visited<Place>=new HashSet<Place>;
  private HashMap<Place,String> nearby=new HashMap<Place,String>(5,0.99);
  public Mouse() {}
private StringBuffer path=null;
  // Return a string of the letters NSEW which, if used to traverse the
  // the maze from startingPoint, will reach a Place where isCheese() is
  // true.  Return null if you can't find such a path.
  public String findCheese(Place startingPoint) {
   visited.add(startingPoint);
   
   Stack visitstack<Place>=new Stack<Place>();
   visitstack.add(startingplace);
   while(!visitstack.isEmpty())
   {
       Place curpos=getNextPlace(visitstack.peek());
       if (curpos==null)
       {
           visitstack.pop();
       }
       if(curpos.isCheese()){
           return path;
        else 
        visitstack.push(curpos);
       }
   }
   return null;
  }
   Place getNextPlace(Place next)
   {
       
    nearby.add(next.goNorth(),"N");
    nearby.add(next.goEast(),"E");
    nearby.add(next.goWest(),"W");
    nearby.add(next.goSouth(),"S");
   
   for(Entry pl : nearby.EntrySet())
      if (!visited.contains(pl.getKey()) && !(pl.getKey()).isWall()){
          path.append(pl.getValue());
          visited.add(pl.getKey());
          nearby.clear();
          return pl;
       }
       nearby.clear();
      return null;
   }  
  }
}
43:17 / 43:17
 
play
1x
2x
5x
© 2016 Interview Zensupport@interviewzen.com

 */