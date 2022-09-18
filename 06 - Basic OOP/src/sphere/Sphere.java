/*
* Homework # 6
*/
package sphere;

public class Sphere {

    public double radius;
    public double diameter, circumference, volume, surface;

    public Sphere(int rad){
        this.radius = rad;
    }

    // Llogaritja e diametrit.
    public double getDiameter(int radius){
        diameter = 2 * radius;
        return diameter;
    }
    
    // Llogaritja e perimetrit.
    public double getCircumference(int radius){
        volume = 2 * Math.PI * radius;
        return volume;
    }

    // Calculate the surface area.
    public double getSurface(int radius){
        surface = Math.pow(radius,2)*Math.PI * 4.0;
        return surface;
    }
    
    // Llogaritet volumi.
    public double getVolume(int radius){
        volume = Math.pow(radius,3)* Math.PI *(4.0/3.0);
        return volume;
    }
    
    // Nxjerrja e rezultateve te metodave të thirrjes së një objekti konkret
    public static void main (String[] args)
    {
        Sphere sphere1 = new Sphere(4);
        System.out.println("Sphere diameter = " + sphere1.getDiameter(4) + " cm");
        System.out.println("Sphere circumference = " + sphere1.getCircumference(4) + " cm");
        System.out.println("Sphere surface Area = " + sphere1.getSurface(4) + " cm2");
        System.out.println("Sphere volume = " + sphere1.getVolume(4) + " cm3");
    }
}
