//package stringslicer;

import java.util.Scanner;
import java.util.ArrayList;

public class StringSlicer {
    
    public static ArrayList<Character> GetUserInput(){

        Scanner scnr = new Scanner(System.in);
        
        String userInput;
        
        ArrayList<Character> charInput = new ArrayList<>();
        
        System.out.print("Enter a string of characters: ");
        userInput = scnr.nextLine();
        
        for (int i = 0; i < userInput.length(); i++) {
            charInput.add(userInput.charAt(i));
        }

        scnr.close();

        return charInput;       
    }

    public static void DisplayUserInput(ArrayList<Character> userInput)
    {
        for (char input : userInput)
        {
            System.out.println(input);
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        DisplayUserInput(GetUserInput());

    }
}
