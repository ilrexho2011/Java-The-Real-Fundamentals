/* 
    FizzBuzz Program with Prime detection include
    Number 1 is excluded
*/
package fizzbuzz;

public class FizzBuzz {

    public static void main(String[] args) {
        int i = 1, j = 1, nrmul = 0;
            while(i <= 100)
            {                             
                while(j <= 100)
                    {                               
                      if(j < i)
                      {
                       if(i % j == 0 )
                       nrmul++;
                      }                          
                      j++;
                    }                              

              if(nrmul == 1)
              {
                  System.out.println("Prime");
              }
              else if((i % 3 == 0) && (i % 5 == 0))
              {
                 System.out.println("FizzBuzz"); 
              }
              else if(i % 3 == 0)
              {
                 System.out.println("Fizz"); 
              }
              else if(i % 5 == 0)
              {
                 System.out.println("Buzz"); 
              }
              else
              {
                 System.out.println(i); 
              }
              i++;
              j=1;  nrmul = 0;
            }      
    }
    
}
