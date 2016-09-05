// ***********************************************************************//
//  
// ** cyoa.java
// Contains function main
// Reads command line, parses/starts game appropiatey
//
// =======================================================================// 
// For this program, I used the help of my partner,the book, my notes from 
// CS11, my friend who has taken the class before, lecture notes, and
// help from google for simple questions such as when is the appropiate
// to use a while loop
// =======================================================================// 
//      
// ***********************************************************************//
import java.io.*;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import static java.lang.System.*;
import java.io.InputStreamReader;


public class cyoa {

 public static void main(String[] args) throws IOException {
        if(args.length == 0) {
      System.out.println("Usage: adventure <filename>");
      System.exit(1);
        }

        adventureStart(args[0]);
        System.exit(0);
  }

  
// =======================================================================// 
// This function reads command line files
// =======================================================================// 
        private static void readFile(String filename, Adventure adventure) throws IOException {

                BufferedReader in = new BufferedReader(new FileReader(filename));

                String line;
                String letter;
                String details;
                String firstRoom = null;

                Scanner sc = new Scanner( in );
                while (sc.hasNextLine()) { //lines = in.readLine()) != null){
                        line = sc.nextLine();
                        if (line.length() == 0) {
                                continue;
                        }
                        letter = line.substring(0, 1);
                        details = line.substring(2, line.length());

                        switch (letter) {
                                case "r":
                                        adventure.currRoom(details,false);

                                        if(firstRoom == null) {
                                        firstRoom = details;
                                        }

                                        break;

                                case "d":
                                        adventure.descriptions(details);
                                        break;

                                case "o":
                                  adventure.createOption(details);
                                        break;

                                case "t":
                                        adventure.updateLastOptionTag(details);
                                        break;


                                case "#":
                                        break;

                                default:
                                  System.out.println("\033[31mInvalid Command; r, d, o, or t are needed\033[0m");
                                  System.exit(1);
                        }
                }

                in.close();
                adventure.currRoom(firstRoom, false);
        }

// =======================================================================// 
// This function gets user input and then parses appropiately    
// =======================================================================// 
        public static void userInput(String filename, Adventure adventure) {
    Scanner stdin = new Scanner(System.in); //grabs user input
    adventure.displayRoom();

    for (;;) {
                        if (!stdin.hasNextLine()) break;

                        String inputline = stdin.nextLine();

                        if (inputline.matches("^\\s*$")) continue;

                        String command = inputline.substring(0,1);

                        String text = "";

                        if (inputline.length() > 1) {
                                text = inputline.substring(1);
                        } //ends if

                        switch (command) {
                                case "q":
                                  System.out.println("\033[31m[quit]\033[0m");
                                        System.exit(0); //quits
                                        return;

                                case "r":
                                        System.out.println("\033[31m[restart]\033[0m");
          adventureStart(filename);
          return; //kills the method

                                case "y":
                                  System.out.println("\033[33m[information]\033[0m");
                                  System.out.println();
                                        adventure.information();
                                        break;

                                case "z":
                                  System.out.println("\033[31m[undo]\033[0m");
                                        adventure.undo();
                                        break;

                                case "a":
                                case "b":
                                case "c":
                                case "d":
                                case "e":
                                case "f":
                                case "g":
                                case "h":
                                case "i":
                                case "j":
                                case "k":
                                case "l":
                                  adventure.choiceMade(command);
                                        break;

                                default:
                                        System.out.println("\033[31mchoose a-l, q, r, y, or z\033[0m");
                        }

                        adventure.displayRoom();
                }
        }

// =======================================================================// 
// prints a
// error message if not ran correctly
// =======================================================================// 
        public static void adventureStart (String filename) {
        Adventure adventure = new Adventure();
        try {
      readFile(filename, adventure);
        }
        catch (IOException error) {
        System.out.println(error.getMessage());
        System.exit(1);
        }

        userInput(filename, adventure);
        }




       
}