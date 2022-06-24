/*
 Class Car - Homework #7 Package
 */
package vehicle;
/*
 * Written BY IR ON T3500 WORKSTATION, Tuesday, 29 September 2020
 */
public class Cars extends Vehicle{
    public Boolean isDriving = false;
    
    public Cars(String make, String model, int year, double weight) {
            super(make, model, year, weight);
    }
    
    public void Driving(){
        if (isDriving == false){
            isDriving = true;
            /*System.out.println("Have a safe trip, sir!");
        */} else {
            System.out.println("You already driving, sir.");
        }
    }
    
    public void Stoping(){
        if (isDriving == true){
            isDriving = false;
            this.tripsSinceMaintenance += 1;
            if (this.tripsSinceMaintenance == 100) {
                this.needsMaintenance = true;
            }
            /*System.out.println("The car is stopping.");*/
        } else {
            System.out.println("The car already stop.");
        }
    }
    
    public void Repair(){
        this.tripsSinceMaintenance = 0;
        this.needsMaintenance = false;
    }
}
