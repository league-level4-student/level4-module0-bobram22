package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);

		// 4. select a random cell to start
		Random ram = new Random();
		Random ram2 = new Random();
		int ramnum = ram.nextInt(h);
		int ramnum2 = ram2.nextInt(w);
		Cell sCell = maze.getCell(ramnum2, ramnum);
		// 5. call selectNextPath method with the randomly selected cell
		selectNextPath(sCell);

		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited
		currentCell.setBeenVisited(true);
		// B. check for unvisited neighbors using the cell
		ArrayList<Cell> cCells = getUnvisitedNeighbors(currentCell);
		// C. if has unvisited neighbors,
		if (!cCells.isEmpty()) {
			// C1. select one at random.
				Random ran = new Random();
				int ranm=ran.nextInt(cCells.size());
				Cell ranc=cCells.get(ranm);
				// C2. push it to the stack
				uncheckedCells.push(ranc);
			// C3. remove the wall between the two cells
				removeWalls( ranc, currentCell);
			// C4. make the new cell the current cell and mark it as visited
				currentCell= ranc;
				currentCell.setBeenVisited(true);
			// C5. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
		}
		// D. if all neighbors are visited
		else if(cCells.isEmpty()) {
			
		
		// D1. if the stack is not empty
			if(!uncheckedCells.isEmpty()) {
				
			currentCell= uncheckedCells.pop();
		// D1a. pop a cell from the stack
			
		// D1b. make that the current cell
			
		// D1c. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
			}
		}
	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
			if(c1.getX()+1==c2.getX() && c1.getY()==c2.getY()) {
				c1.setEastWall(false);
				c2.setWestWall(false);
			}
			if(c1.getX()-1==c2.getX() && c1.getY()==c2.getY()) {
				c2.setEastWall(false);
				c1.setWestWall(false);
			}
			if(c1.getY()+1==c2.getY() && c1.getX()==c2.getX()) {
				c1.setSouthWall(false);
				c2.setNorthWall(false);
			}
			if(c1.getY()-1==c2.getY() && c1.getX()==c2.getX()) {
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			}
	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> unvisitedNeighbors = new ArrayList<Cell>();
		if(c.getX()<width-1&&!maze.getCell(c.getX()+1, c.getY()).hasBeenVisited()) {
			unvisitedNeighbors.add(maze.getCell(c.getX()+1, c.getY()));
		}
		if(c.getX()>=1&&!maze.getCell(c.getX()-1, c.getY()).hasBeenVisited()) {
			unvisitedNeighbors.add(maze.getCell(c.getX()-1, c.getY()));
		}
		if(c.getY()<height-1&&!maze.getCell(c.getX(), c.getY()+1).hasBeenVisited()) {
			unvisitedNeighbors.add(maze.getCell(c.getX(), c.getY()+1));
		}
		if(c.getY()>=1&&!maze.getCell(c.getX(), c.getY()-1).hasBeenVisited()) {
			unvisitedNeighbors.add(maze.getCell(c.getX(), c.getY()-1));
		}
		
		return unvisitedNeighbors;
	}
}
