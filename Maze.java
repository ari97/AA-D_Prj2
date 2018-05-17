import java.util.Set;
import java.util.TreeSet;
import java.util.Scanner;

/* Changes from Project 1:
 *
 * Data member validLocations was changed from a Location array to a
 * Set<Location>, but it still represents the group of locations that may be
 * visited in the maze.
 *
 * Data member validLocationCount was eliminated (because validLocations is no
 * longer an array).
 *
 * We let the compiler deal with the assignment operator, copy constructor, and
 * for this version of the Maze, you should use the default constructor for the
 * _usual_ and to initally allocate memory for the Set of validLocations using
 * the TreeSet implementation of a Set.
 */

class Maze {
  private Set<Location> validLocations;

  private Location startLocation;
  private Location endLocation;

  Maze() {
    // -
    validLocations = new TreeSet<Location>();
  }

  Location getStartLocation() {
    // -
    return this.startLocation;
  }
  Location getEndLocation(){
    return this.endLocation;
  }
  boolean isValidLocation(Location loc) {
    // -
    return (validLocations.contains(loc)) ;

  }
  boolean isEndLocation(Location loc) {
    // -
    return (loc.isEqual(this.endLocation));

  }

  void streamIn(Scanner input) {
    // -
    int counter = Integer.parseInt(input.nextLine());
    for(int x = 0; x < counter; x++){
      Location validLoc = new Location();

      validLoc.streamIn(input);
      this.validLocations.add(validLoc);
    }
    String theEmptyLine = input.nextLine(); //get last empty line
    //Last 2 lines are start and end
    Location startLoc = new Location();
    startLoc.streamIn(input);
    this.startLocation = startLoc;


    Location endLoc = new Location();
    endLoc.streamIn(input);

    this.endLocation = endLoc;
  }

}
