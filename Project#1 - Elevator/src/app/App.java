/*
 * The Fundamentals of Java  -  Project #1

* Details:
 
* You've been hired by a construction firm to help build the "brain" for a set of elevators in a new building. Your task is to write the code that will control the elevators, and tell each elevator which floor to travel to next.
* Building DescriptionThe building is 10 stories tall and the floors are numbered 0 - 10 inclusive. The lobby is floor 0, and the penthouse is floor 10. The building contains one basement (floor -1).
* The building contains 2 elevators: A and B.
* Elevator A: Goes to all floors except the penthouse (floor 10).
* Elevator B: Goes all the way up (including 10) but does not go to the basement (-1).
* AUTHOR : IR ON T3500 WORKSTATION
 */
package app;

/**
 * This class is going to instantiate the ElevatorController
 */
public class App {
    public static void main( String[] args ) { 
        /* Request 1: Create a building with 15 floors and 6 elevators. */
        Building building = new Building( 10, 2 );

        /* Request 2: Make elevator 1 go to floor 9. */
        building.addFloorRequest( 9, 1 );

        // Request 3: Make elevator 2 go to floor 10.
        building.addFloorRequest( 10, 2 );

        // Request 4
        building.addFloorRequest( 7, 2 );
        building.addFloorRequest( 3, 2 );

        // Request 5
        try {
            Thread.sleep( 30000 );
        } catch ( InterruptedException ie ) {
            ie.printStackTrace();
        }

        // Request 6
        building.addFloorRequest( 10, 1 );
        building.addFloorRequest( 1, 1 );

        // Request 7
        try {
            Thread.sleep( 10000 );
        } catch ( InterruptedException ie ) {
            ie.printStackTrace();
        }
        building.addFloorRequest( 2, 1 );

        // Request 8
        building.addFloorRequest( 5, 1 );
        building.addFloorRequest( 3, 1 );
    }
}

