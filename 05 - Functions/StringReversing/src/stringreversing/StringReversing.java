/*
 * Homework #5 part 1
 */
package stringreversing;

import java.util.Scanner;

public class StringReversing {

    public static void main(String[] arg)
	{
	StringReversing rev=new StringReversing();
	Scanner reader = new Scanner(System.in);
	System.out.print("Enter your string : ");
	String  str = reader.nextLine();	
	System.out.println("Reverse of given String  is : " + rev.reverseString(str));
	}
static String reverseString(String s)
	{
	String rev= "";
        // copying characters starting from last to first one
	for(int j = s.length(); j>0; --j)
	{
	rev = rev + (s.charAt(j-1)); 
	}
	return rev;
	}
    
}
