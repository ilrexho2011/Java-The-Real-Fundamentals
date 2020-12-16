/*
 * Homework #5 part 3
 */
package generaterandomnr;

import java.util.Random;
import java.util.Scanner;

public class GenerateRandomNr {

    public static void main(String[] args) 
    {
	Scanner reader = new Scanner(System.in);
	System.out.print("Enter min : ");
	int min = reader.nextInt();
        System.out.print("Enter max : ");
	int max = reader.nextInt();
        System.out.println(randBetween(min, max));
    }
    private static int randBetween(int min, int max) 
    {
        if (min >= max) 
        {
            System.out.println("Be careful - max must be greater than min");
	}
        Random nr = new Random();
	return nr.nextInt((max - min) + 1) + min;
    }
}
