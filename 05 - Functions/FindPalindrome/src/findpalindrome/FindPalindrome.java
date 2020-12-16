/*
 * Homework #5 part 2
 */
package findpalindrome;

import java.util.Scanner;

public class FindPalindrome {

    public static void main(String[] args) {
        boolean flag;
	Scanner reader = new Scanner(System.in);
	System.out.print("Enter your string : ");
	String  str = reader.nextLine();
        flag = isPalindrome(str);
        if(flag)
        {
            System.out.println(str + " is palindrome !");
        }
        else
        {
            System.out.println(str + " is not palindrome !");
        }
    }
    
    public static boolean isPalindrome(String s)
   {
      String strReverse = new StringBuffer(s).reverse().toString();
       // checking the equality with the reversed word
      if(s.equals(strReverse))
      {
         return true;
      }
      else
      {
         return false;
      }
   }
    
}
