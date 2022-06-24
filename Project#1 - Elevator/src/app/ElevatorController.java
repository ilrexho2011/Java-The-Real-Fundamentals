package app;

import java.util.ArrayList;

/**
 * ElevatorController class, it uses Singleton pattern
 * CREATED BY IR ON T3500 WORKSTATION
 */
public class ElevatorController {

    private int numFloors;
    private ArrayList<Elevator> elevators;
    private int desiredElevatorNumber;

	/**
     * @return ElevatorConcroller object that sets up the ArrayList of
	 * Elevators and number of floors in the building.
	 * @param numElevatorsIn is the number of elevators you wish to spawn within the building.
	 * @param numFloorIn is the number of floors that the building has.
	 * @throws IllegalArgumentException
     */
    public ElevatorController( int numElevatorsIn, int numFloorsIn ) throws IllegalArgumentException {
        if ( numFloorsIn <= 0 || numElevatorsIn <= 0 )
            throw new IllegalArgumentException();
        elevators = new ArrayList<Elevator>();
        int currentElevatorNumber = 1;
        for ( int i = currentElevatorNumber; i <= numElevatorsIn; i++ ) {
            Elevator elevator = new Elevator( i ); 
            elevators.add( elevator );
            new Thread( elevator ).start();
        }
        setNumFloors( numFloorsIn );
    }

	/**
     * @return the number of elevators in the Elevator array
     */
    public int getNumElevators() {
        return getElevators().size();
    }

    /**
     * @return number of floors available in the building.
     */
    public int getNumFloors() {
    	return numFloors;
    }

    /**
     * sets numFloors to the parameter numFloorsIn.
     */
    private void setNumFloors( int numFloorsIn ) {
    	numFloors = numFloorsIn;
    }
    
    /**
     * Accepts a request from a person (for now App.java) and stores it in pendingRequests.
     * @param requestedFloorNum is the floor that is requested for the elevator to travel to.
	 * @param elevatorNumber is the number of the elevator car.
	 */
    public void addFloorRequest( int requestedFloorNum, int elevatorNumber ) {
        if ( ( ( requestedFloorNum > 0 ) && ( requestedFloorNum <= numFloors ) ) && 
                ( ( elevatorNumber > 0 ) && ( elevatorNumber <= getNumElevators() ) ) ) {
            sendRequestToElevator( elevatorNumber, requestedFloorNum );
        } else {
            System.out.println( "We're are sorry, but you have requested an invalid floor or an invalid elevator." );
        }
    }

    /**
     * sends a request to the specified elevator(elevatorIndex) to the specified floor(requestedFloorNum).
     * @param elevatorIndex The index of the requested elevator within the elevators ArrayList.
     * @param requestedFloorNum The number of the destination floor.
     */
    private void sendRequestToElevator( int elevatorNumber, int requestedFloorNum ) {
        // TODO take me out please.
        System.out.println( "getNumElevators(): " + getNumElevators() );
        System.out.println( "The elevator number is " + elevatorNumber );
        getElevator( elevatorNumber ).addDestination( requestedFloorNum );
    }

    /**
     * checks to see whether there are any pending destinations that were added to an elevator.
     * @return the number of destinations that this elevator has
	 */
    public int getNumRequests() {
        ArrayList<Elevator> allElevators = getElevators();
        int totalDestinationsCount = 0;
        for( Elevator elevator : allElevators ) {
            totalDestinationsCount += elevator.getNumPendingDestinations();
        }
        return totalDestinationsCount;
    }

    /**
     * @return the list of elevators that pertain to this ElevatorController.
     */
    private ArrayList<Elevator> getElevators() {
        return elevators;
    }

    /**
     * @return Elevator object by number, using the index from the elevators ArrayList.
     * @param elevatorNum is the number of the elevator car that you wish to obtain.
	 */ 
    private Elevator getElevator( int elevatorNum ) {
        return getElevators().get( getElevatorIndexByNumber( elevatorNum ) );
    }

    /**
	 * Is used to get the Elevator's car number when you give it the index of the ArrayList. 
     * @return the elevator's car number; index + 1.
     * @param elevatorIndex is the index of the elevator in the ArrayList. 
	 */
    private int getElevatorNumberByIndex( int elevatorIndex ) {
        return elevatorIndex + 1;
    }

    /**
	 * Is used to get the index of the ArrayList when you give it the Elevator's car number. 
     * @return elevatorIndex is the index of the elevator in the ArrayList; number - 1.
     * @param the elevator's car number
     */
    private int getElevatorIndexByNumber( int elevatorNumber ) {
        return elevatorNumber - 1;
    }
}
