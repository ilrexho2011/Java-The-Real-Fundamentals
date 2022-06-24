/*
 * Homework #7
 */
package vehicle;
/*
 * Written BY IR ON T3500 WORKSTATION, Monday, 28 September 2020
 */
public class Main {
    
    public static void main(String[] args) {
        Cars a = new Cars(
                "Skoda",
                "Fabia",
                2017,
                1435.0);
        Cars b = new Cars(
                "Honda",
                "Civic",
                2007,
                1136.0);
        Cars c = new Cars(
                "WV",
                "Golf",
                2004,
                1540.0);
        
        Planes d = new Planes(
                "Air Bus",
                "380-500",
                2015,
                280120.0);
        
        
        for (int i=0; i<102; i++){
            a.Driving();
            b.Driving();
            c.Driving();
            d.Flying();
            
            a.Stoping();
            b.Stoping();
            c.Stoping();
            d.Landing();
        }
        
        a.Repair();
        c.Repair();
        System.out.println("========================");
        a.Print();
        System.out.println("========================");
        b.Print();
        System.out.println("========================");
        c.Print();
        System.out.println("========================");
        d.Print();
    }
}
