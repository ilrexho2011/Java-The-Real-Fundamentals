/*
 Class Vehicle - Homework #7 Package
 */
package vehicle;

/*
 * Written BY IR ON T3500 WORKSTATION Tuesday, 29 September 2020
 */
public class Vehicle {
    protected String make;
    protected String model;
    protected int year;
    protected double weight;
    
    protected Boolean needsMaintenance = false;
    protected int tripsSinceMaintenance = 0;
    
    public Vehicle(String make, String model, int year, double weight){
        this.make = make;
        this.model = model;
        this.year = year;
        this.weight = weight;
    }
    
    public void Print(){
        System.out.println("Name: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Weight: " + weight);
        
        System.out.println("Need Maintenance Status: " + needsMaintenance);
        System.out.println("Current trip record: " + tripsSinceMaintenance);
    }
}
