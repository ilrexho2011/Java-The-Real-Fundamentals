  
/**
 * Java Fundamentals - TimeAdder
 * 
 */
package timeadder;

import java.io.*;

public class TimeAdder
{
    private int a[];
    public TimeAdder(){
        a = new int[2];
    }
    public void readTime()throws IOException{
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);
        System.out.print("hours = ");
        a[0] = Integer.parseInt(br.readLine());
        System.out.print("minutes = ");
        a[1] = Integer.parseInt(br.readLine());
    }
    public void addTime(TimeAdder X, TimeAdder Y){
        this.a[1] = X.a[1] + Y.a[1];
        this.a[0] = X.a[0] + Y.a[0] + this.a[1] / 60;
        this.a[1] %= 60;
    }
    public void dispTime(){
        System.out.println("hours = " + this.a[0]);
        System.out.println("minutes = " + this.a[1]);
    }
    public static void main(String args[])
    throws IOException{
        TimeAdder x = new TimeAdder();
        x.readTime();
        TimeAdder y = new TimeAdder();
        y.readTime();
        TimeAdder z = new TimeAdder();
        z.addTime(x, y);
        z.dispTime();
    }
}