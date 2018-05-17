/* CMPT 435
 * Project 2 -- Word Melt Solver
 * Filename: Driver_prj2.java
 * Student name: Ariel Camilo
 *
 * Program will accept a list of words
 * in the form of valid, start, and end words.
 * Program will solve the word melt puzzle by determining
 * steps to get from start to end, and it will
 * determine the shortest path found first.
 */
import java.util.Scanner;
import java.util.Stack;
import java.util.Map;
import java.util.HashMap;

 public class Driver_prj2{
   public static void main(String[] args){
     Scanner input = new Scanner(System.in);

     Maze myMaze = new Maze();
     myMaze.streamIn(input);

     Map <Location, Location> solnPaths = new HashMap<Location, Location>();
     Stack<Location> finalSolution = new Stack<Location>();

     ArrayQueue aqPath = new ArrayQueue();
     aqPath.add(myMaze.getStartLocation());

     solnPaths.put(aqPath.getFront(), aqPath.getFront());
     boolean isFound = false;


     while((isFound == false) && (aqPath.getLength() > 0)){
       Location currentWord = aqPath.getFront();
       aqPath.remove();
       currentWord.start();
       while(!(currentWord.isDone())){
         Location neighborWord = currentWord.nextNeighbor();
         neighborWord.streamOut();
         if(myMaze.isValidLocation(neighborWord)){
           if( !(solnPaths.containsKey(neighborWord)) ){
             solnPaths.put(neighborWord, currentWord);
             aqPath.add(neighborWord);
           }
           if(myMaze.isEndLocation(neighborWord)){
             isFound = true;
           }
         }
       }
     }

     finalSolution.push(myMaze.getEndLocation());
     //To iterate through Map, gotta create reference to end Word
     Location iteratorWord = myMaze.getEndLocation();


     for (Map.Entry<Location, Location> entry : solnPaths.entrySet()) {
       System.out.print("Key:");
       entry.getKey().streamOut();
       System.out.print("Value:");
       entry.getValue().streamOut();
       System.out.println("------------------------");
     }




   }
 }
