/*
 * Homework #7
 */
package vehicle;

/**
 * written 30.09.2020
 * @author IR ON T3500 WORKSTATION
 */
public class Planes extends Vehicle{
    public Boolean isFlying = false;
    
    public Planes(String make, String model, int year, double weight) {
            super(make, model, year, weight);
    }
    
    public void Flying(){
        if (isFlying == false){
            if (this.needsMaintenance == true){
                isFlying = false;
                System.out.println("It can't fly until repaired, sir.");
            } else {
                isFlying = true;
                /*System.out.println("Have a safe trip, sir!");*/
            }
        } else {
            System.out.println("It's already flying, sir.");
        }
    }
    
    public void Landing(){
        if (isFlying == true){
            isFlying = false;
            this.tripsSinceMaintenance += 1;
            if (this.tripsSinceMaintenance == 100) {
                this.needsMaintenance = true;
            }
            /*System.out.println("Good job, sir. How is your trip?");
        */} else {
            System.out.println("The plane already landed.");
        }
    }
    
    public void Repair(){
        this.tripsSinceMaintenance = 0;
        this.needsMaintenance = false;
    }
}
