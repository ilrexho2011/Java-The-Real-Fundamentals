/*
    Homework 4
*/
package maps;

import java.util.*;

public class Maps {
    public static void main(String[] args) {
        String[] myFavSong =  
            //<editor-fold desc="raw names data">
            {"album", "Welcome back",
            "genre", "Rock",
            "producer", "DJ69",
            "author", "Sea boys",
            "year", "1990"};
            //</editor-fold>
        String[] Properties = new String[myFavSong.length/2];
        String[] Values = new String[myFavSong.length/2];
        for(int i = 0; i < myFavSong.length; i++)
        {
            /*
            The case of an EVEN NUMBERED iteration 
            */
            if(i % 2 == 0) 
            {
            // The case of an even number iteration - looking properties/values
                Properties[i/2] = myFavSong[i];
            }
            else
            {
            // The case of an odd number iteration - looking at values of properties chosen/keys
                Values[i/2] = myFavSong[i];
            }
        }
        
        Map<String,String> favoritSong = new HashMap<>();
        for(int i = 0; i < Values.length; i++)
        {
            favoritSong.put(Values[i],Properties[i]);
        }
        // use of map entry (key-value pair) iterating with entrySet()
        for (Map.Entry<String, String> entry : favoritSong.entrySet()) 
        {
            // use of getKey() and getValue() to print the key and value in the same row
            System.out.println(entry.getKey()  + '[' + entry.getValue() + ']');
        }
        
        
    }
}
