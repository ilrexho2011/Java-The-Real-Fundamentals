package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Elevator class
 * CREATED: IR ON T3500 WORKSTATION
 */
public class Elevator implements IElevator, Runnable {

    private enum directionChoice { UP, DOWN, STATIONARY };
    private enum currentStateChoice { IDLE, STOPPED, TRAVELING, OFF };
    private directionChoice direction;
    private currentStateChoice currentState;
    private int currentFloorNum;
    private ArrayList<Integer> pendingDestinations;
    private int defaultFloorNum;
    private int number;
    private int idleCounter;
    private boolean running;

    /**
     * @return Elevator object that sets the defaults of: directionChoice.UP and currentStateChoice.UP
	 * @param numberIn is the number of the elevator; its ID, so to speak.
	 * @throws IllegalArgumentException
     */
    public Elevator( int numberIn ) throws IllegalArgumentException {
        if ( numberIn <= 0 )
           throw new IllegalArgumentException();
        pendingDestinations = new ArrayList<Integer>();
        setDirection( directionChoice.STATIONARY );
        setCurrentState( currentStateChoice.IDLE );
        setDefaultFloorNum( 1 );
        setCurrentFloorNum( 1 );
        setNumber( numberIn );
        setIdleCounter( 0 );
        setRunning( true );
        System.out.println( "Spawning Elevator Car #" + getNumber() );
    }

    /**
     * sets the currentState of the elevator to the parameter currentStateChoiceIn.
	 * @return currentState the state of the elevator
     */
    private currentStateChoice getCurrentState() {
        return currentState;
    }
    
    /**
     * sets the currentState of the elevator to the parameter currentStateChoiceIn.
     * @param currentStateChoiceIn the state of the elevator that is an enum type currentStateChoice
     */
    private void setCurrentState( currentStateChoice currentStateIn ) {
        currentState = currentStateIn;
    }

    /**
     * set direction (UP or DOWN) to the parameter directionIn.
     * @param directionIn direction in which the elevator is now traveling in.
     */
    private void setDirection( directionChoice directionIn ) {
        direction = directionIn;
    }

    /**
     * runs the thread until running is set to false at which point the elevator status is set to OFF.
     */
    public void run() {
        while ( running ) {
            trySleep( 1000 );

            switch ( getCurrentState() ) {
                case IDLE:
					synchronized( this ) {
						boolean newDestinationAdded = false;
						if ( !hasPendingDestinations() ) {
							try {
								wait( 10000 );
                                System.out.println( "Elevator #" + getNumber() + " continues to waiting on floor #" + getCurrentFloorNum() + " for upcoming requests." );
							} catch ( IllegalMonitorStateException imse ) {
								// System.out.println( "IllegalMonitorStateException happened here!" );
							} catch ( InterruptedException ie ) {
                                System.out.println( "Elevator #" + getNumber() + " just got a message." );
								newDestinationAdded = true;
							}

							if ( newDestinationAdded == false && !( getCurrentFloorNum() == getDefaultFloorNum() ) ) {
                                addDestination( getDefaultFloorNum() );
                                setCurrentState( currentStateChoice.TRAVELING );
							}
						} else {
							System.out.println( "Elevator #" + getNumber() + " now has a purpose in life." );
							setCurrentState( currentStateChoice.TRAVELING );
						}
					} // End synchronized block #1
					break;
						
                case STOPPED:
                    System.out.println( "Elevator #" + getNumber() + " is now stopped at floor #" + getCurrentFloorNum() );
                    openCloseDoors();
                    removeOldDestination();
                    System.out.println( "Destination removed." );
                    if ( hasPendingDestinations() ) {
                        setCurrentState( currentStateChoice.TRAVELING );
					} else {
                        setCurrentState( currentStateChoice.IDLE );
                        setDirection( directionChoice.STATIONARY );
                    }
                    break;

                case TRAVELING:
                    if ( hasPendingDestinations() ) {
                        if ( getCurrentFloorNum() == getNextDestination() ) {
                            System.out.println( "Elevator #" + getNumber() + " has reached floor #" + getCurrentFloorNum() + "." );
                            setCurrentState( currentStateChoice.STOPPED );
                        } else if ( direction == directionChoice.UP ) {
                            trySleep(1000);
                            System.out.println( "Elevator #" + getNumber() + " has passed floor #" + getCurrentFloorNum() + "." );
                            setCurrentFloorNum( getCurrentFloorNum() + 1 );
                        } else if ( direction == directionChoice.DOWN ) {
                            trySleep(1000);
                            System.out.println( "Elevator #" + getNumber() + " has passed floor #" + getCurrentFloorNum() + "." );
                            setCurrentFloorNum( getCurrentFloorNum() - 1 );
                        } else { 
                            System.out.println( "Elevator #" + getNumber() + " does not know wtf it is doing." );
                        }
                    } else {
                        System.out.println( "Elevator #" + getNumber() + " became IDLE for some reason..." );
                        setCurrentState( currentStateChoice.IDLE );
                    }
                    break;

                case OFF:
                    System.out.println( "Shutting down Elevator #" + getNumber() );
                    setRunning( false );
                    break;

                default:
                    System.out.println( "Elevator #" + getNumber() + " is experiencing technical difficulties." );

            }
        }
    }

	/**
	 * @return true if the elevator has been idle for ten seconds.
	 */
    private boolean idleTimeoutReturnToDefault () {
        return ( getIdleCounter() >= 10 && !isOnDefaultFloor() );
    }

	/**
	 * @return true if the elevator goes idle on any floor that isn't the default floor.
	 */	
    private boolean goesIdleOnRandomFloor () {
        return ( getIdleCounter() == 0 && !isOnDefaultFloor() );
    }
    
    /**
     * this method wraps the Thread-sleep method within a try/catch block to prevent the compilation error.
     * @param milliseconds the number of milliseconds you want to sleep.
	 */
    private void trySleep( int milliseconds ) {
        try {
            Thread.sleep( milliseconds );
        } catch ( InterruptedException ie ) {
            ie.printStackTrace();
        }
    }

    /**
     * @return a boolean that represents whether the elevator has any pending destinations.
     */
    private boolean hasPendingDestinations() {
       return ( getPendingDestinations().size() > 0 );
    }

	/**
     * @return the number of pending destinations that this elevator has.
     */
    public int getNumPendingDestinations() {
        return getPendingDestinations().size();
    }

    /**
     * adds a new destination to the elevator pendingDestinations ArrayList
     * checks whether the requested direction is valid, otherwise ignores
	 * the request; this method is synchronized.
     * @param requestedFloorNum the floor that is requested by an object.
     */
    public void addDestination( int requestedFloorNum ) {
		synchronized( pendingDestinations ) {
			boolean ignoreDownRequestWhileGoingUp = isRequestUp( requestedFloorNum ) && isGoingDown();
			boolean ignoreUpRequestWhileGoingDown = isRequestDown( requestedFloorNum )  && isGoingUp();

			if ( requestedFloorNum == currentFloorNum ) {
				openCloseDoors();
			} else if ( !ignoreUpRequestWhileGoingDown && !ignoreDownRequestWhileGoingUp ) {
				if ( isRequestUp( requestedFloorNum ) ) {
					System.out.println( "Elevator #" + getNumber() + " is now going up." );
					setDirection( directionChoice.UP );
				} else if ( isRequestDown( requestedFloorNum ) ) {
					System.out.println( "Elevator #" + getNumber() + " is now going down." );
					setDirection( directionChoice.DOWN );
				} else { 
					System.out.println( "Elevator #" + getNumber() + " doesn't know where it is going..." );
				}
				pendingDestinations.add( requestedFloorNum ); 
                sortPendingDestinations();
                try {
                    notifyAll();
                } catch ( IllegalMonitorStateException imse ) {
                    System.out.println( "Notify all threw IllegalMonitorStateException" );
                }
			} else {
				System.out.println( "The request direction is incorrect for Elevator #" + getNumber() + ", ignoring." );
			}
		} // end synchronized block 2
    }

    /**
     * removes the first destination from the list of pending destinations, 
     * this method should be used when an elevator has delivered people to a floor.
     */
    private void removeOldDestination() {
       getPendingDestinations().remove( 0 ); 
    }

    /**
     * @return the direction that the elevator is traveling in.
     */
    private directionChoice getDirection() {
        return direction;
    }

	/**
	 * @return true if the elevator is going down
	 */
    private boolean isGoingDown() {
        return getDirection() == directionChoice.DOWN;
    }

	/**
	 * @return true if the elevator is going up!
	 */
    private boolean isGoingUp() {
        return getDirection() == directionChoice.UP;
    }

	/**
	 * @param requestedFloorNumIn the floor number that is requested for this elevator to travel to.
	 * @return true if the requested floor is below the elevator
	 */
    private boolean isRequestDown( int requestedFloorNumIn ) {
        return ( requestedFloorNumIn < getCurrentFloorNum() );
    }

	/**
	 * @param requestedFloorNumIn the floor number that is requested for this elevator to travel to.
	 * @return true if the requested floor is above the elevator
	 */
    private boolean isRequestUp( int requestedFloorNumIn ) {
        return ( requestedFloorNumIn > getCurrentFloorNum() );
    }
 
    /**
     * sets the current floor to the currentFloorNumIn
     * @param currentFloorNumIn current floor.
     */
    private void setCurrentFloorNum( int currentFloorNumIn ) {
        currentFloorNum = currentFloorNumIn;
    }

    /**
     * retrieves the current floor number
     * @return the current floor the elevator is on.
     */
    private int getCurrentFloorNum() {
        return currentFloorNum;
    }

    /**
     * sets the default floor for the elevator to be on initially.
     * @param defaultFloorNumIn the default floor.
     */
    private void setDefaultFloorNum( int defaultFloorNumIn ) {
        defaultFloorNum = defaultFloorNumIn;
    }

    /**
     * this function is used to obtain the default floor number.
     * @return default floor number.
     */
    private int getDefaultFloorNum() {
        return defaultFloorNum;
    }

    /**
     * opens and closes doors with pauses in between each action, this simulates loading and unloading passengers.
     */
    private void openCloseDoors() {
        System.out.println( "Elevator #" + getNumber() + " is opening doors..." );    
        trySleep( 2000 );
        System.out.println( "Elevator #" + getNumber() + "'s doors are now open." );    

        System.out.println( "Elevator #" + getNumber() + " is now loading/unloading passengers..." );    
        trySleep( 5000 );
        
        System.out.println( "Elevator #" + getNumber() + " is closing its doors..." );    
        trySleep( 2000 );
        System.out.println( "Elevator #" + getNumber() + "'s doors are now closed" );    
    }

    /**
     * returns the number of the elevator.
     * @return number the number of the elevator is used to identify it throughout the application.
     */
    private int getNumber() {
        return number;
    }
    
    /**
     * sets the number of the elevator to the specified parameter numberIn.
     * @param numberIn used to set number of the elevator.
     */
    private void setNumber( int numberIn ) {
        number = numberIn;
    }

    /**
     * @return a boolean that determines whether the elevator is currently on the default floor.
     */
    private boolean isOnDefaultFloor() {
        return ( getCurrentFloorNum() == getDefaultFloorNum() );
    }

    /**
     * sorts the pendingDestinations ArrayList and sets it to the sorted value.
     */
    private void sortPendingDestinations() {
        ArrayList<Integer> sortedPendingDestinations = getPendingDestinations();
        Collections.sort( sortedPendingDestinations );
        if ( direction == directionChoice.DOWN )
            Collections.reverse( sortedPendingDestinations );
        setPendingDestinations( sortedPendingDestinations );
    }

    /**
     * @return first destination from the list of pendingDestinations.
	 * @throws IllegalArgumentException
     */
    private int getNextDestination() throws IllegalArgumentException {
        if ( !hasPendingDestinations() )
            throw new IllegalArgumentException( "You messed up! There are no pending destinations..." );
        return ( int ) getPendingDestinations().get( 0 );
    }

    /**
     * @return pending destinations for the elevator.
     */
    private ArrayList<Integer> getPendingDestinations() {
        return pendingDestinations; 
    }

	/**
	 * sets the list of destinations for the elevator to travel to.
	 * @param pendingDestinationsIn is the arraylist of floor numbers that this elevator shall travel to.
	 */
    private void setPendingDestinations( ArrayList<Integer> pendingDestinationsIn ) {
        pendingDestinations = pendingDestinationsIn;
    }

    /**
     * @return the idleCounter data member.
     */
    private int getIdleCounter() {
        return idleCounter;
    }

    /**
     * sets the idleCounter data member to idleCounterIn param.
     * @param idleCounterIn used in setting the idleCounter data member.
     */
    private void setIdleCounter( int idleCounterIn ) {
        idleCounter = idleCounterIn;
    }

    /**
     * @return true of the thing is running.
     */
    private boolean isRunning() {
        return running;
    }

    /**
     * sets the running data member to the runningIn parameter.
     * @param runningIn used to set the running booooooolean.
     */
    private void setRunning( boolean runningIn ) {
        running = runningIn;
    }
}

