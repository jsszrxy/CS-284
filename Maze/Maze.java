package Maze;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Stack;

//David Horowitz
//I pledge my honor that I have abided by the Stevens honor system.

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.x,y
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    /**
     * Helper method to check if the coordinates are in the board.
     * @param x coordinate 
     * @param y coordinate
     * @return true if the coordinate is in the board and false otherwise.
     */
    private boolean inBoard(int x, int y) {
    	return x >= 0 && x < maze.getNCols() && y >= 0 && y < maze.getNRows();
    }
    
    /**
     * Helper method to check if the coordinates point to the exit.
     * @param x coordinate 
     * @param y coordinate
     * @return true if the current coordinates point to the exit and false otherwise.
     */
    private boolean isExit(int x, int y) {
    	return x == maze.getNCols() - 1 && y == maze.getNRows() -1;
    }
    
    /**
     * Solves the maze and recolors the path it used to find its solution, and then returns true. If there is not path, it will return false.
     * @param x coordinate 
     * @param y coordinate
     * @return true if a path through the maze is found and false otherwise.
     * 
     */
    public boolean findMazePath(int x, int y) {
        if(!inBoard(x,y)) {
        	return false;
        }
        else if(maze.getColor(x,y) != NON_BACKGROUND) {
        	return false;
        }
        else if(isExit(x, y)) {
        	maze.recolor(x, y, PATH);
        	return true;
        } else {
        	maze.recolor(x,y,PATH);
        	if (findMazePath(x+1, y) || findMazePath(x, y+1) || findMazePath(x, y-1) || findMazePath(x-1, y)) {
        		return true;
        	} else {
        		maze.recolor(x,y, TEMPORARY);
        		return false;
        	}
        }
	}
    /**
     * Helper function for findAllMazePaths.
     * Stores the coordinate pairs (PairInts) from the stack into result so that the main function can return it.
     * @param x coordinate 
     * @param y coordinate
     * @param result - the arraylist of arraylists that holds the lists of coordinates (PairInts).
     * @param trace - stack used to hold coordinate pairs (PairInts).
     */
	public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
    	if(!inBoard(x,y)) {
        	return;
        } else if(maze.getColor(x,y) != NON_BACKGROUND) {
        	return;
        }
        else if(isExit(x, y)) {
        	trace.push(new PairInt(x,y));
    		ArrayList<PairInt> temp = new ArrayList<PairInt>();
    		temp.addAll(trace);
    		result.add(temp);
    		trace.clear();
    	} else {
        	trace.push(new PairInt(x,y));
        	maze.recolor(x, y,PATH);
    		findMazePathStackBased(x+1, y, result, (Stack<PairInt>)trace.clone());
    		findMazePathStackBased(x, y+1, result, (Stack<PairInt>)trace.clone());
    		findMazePathStackBased(x-1, y, result, (Stack<PairInt>)trace.clone());
    		findMazePathStackBased(x, y-1, result, (Stack<PairInt>)trace.clone());
    		maze.recolor(x, y, NON_BACKGROUND);
    	}
    	
    }
    /**
     * Finds each possible path through the maze and returns it in a list of lists with pairs of coordinates.
     * @param x coordinate 
     * @param y coordinate
     * @return an arraylist of arraylists that holds the lists of coordinates (PairInts). If there are no paths, it returns an empty arraylist inside an arraylist.
     */
    public ArrayList <ArrayList <PairInt>> findAllMazePaths (int x, int y) {
    	 ArrayList <ArrayList <PairInt>> result = new ArrayList<>();
    	 Stack <PairInt> trace = new Stack<>();
    	 findMazePathStackBased(0, 0, result, trace);
    	 if(result.size() == 0) {
    		 ArrayList<PairInt> temp = new ArrayList<PairInt>();
    		 result.add(temp);
    	 }
    	 return result;
    }

    /**
     * Finds the shortest path through the maze and returns a list of coordinates.
     * @param x coordinate 
     * @param y coordinate
     * @return an arraylist of coordinates (PairInts) that leads to the shortest path. If there are no paths, it returns an empty arraylist inside an arraylist.
     */
    public ArrayList<PairInt> findMazePathMin(int x, int y) {
    	ArrayList <ArrayList <PairInt>> result = findAllMazePaths(x, y);
    	int currLength = result.get(0).size();
    	int index = 0;
    	for(int i = 1; i<result.size(); i++) {
    		if(currLength > result.get(i).size()) {
    			currLength = result.get(i).size();
    			index = i;
    		}
    	}
    	if(result.get(index).size() == 0) {
    		throw new NoSuchElementException("There are no avaliable paths through the maze!");
    	}
    	return result.get(index);
    }

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
