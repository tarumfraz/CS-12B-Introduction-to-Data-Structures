// ***********************************************************************//

// ***********************************************************************//
//  
// ** xref.java
// ** 
// ** This file takes in command line arguments and reads them appropiately
// ** If the command line contains a file name and/or the debugging opiton
// ** This file will go ahead and try to process the file.
// ** 
// ** For this file, I used my old notes from CS 11 to parse command line
// ** arguments
// **
//      
// ***********************************************************************//

// ***********************************************************************// 

import java.io.*;
import java.util.Scanner;
import static java.lang.System.*;
 
class xref {
       
   public static boolean willDebug = false; //**** Boolean flag
 
    static void processFile(String filename, boolean debug) throws IOException {
        Scanner scan = new Scanner (new File(filename));
        Tree tree = new Tree();
        for (int linenr = 1; scan.hasNextLine (); ++linenr) {
            for (String word: scan.nextLine().split ("\\W+")) {
             //   out.printf ("%s: %d: %s%n", filename, linenr, word);
                tree.insert(word, linenr);
            }
        }
        scan.close();
        if (debug) {
            tree.debug();
        } else {
            tree.output();
        }
    }

// =======================================================================// 
// Our main function, here we read in our command line arguments          //        
// =======================================================================//  
 
    public static void main(String[] args) {
        if (args[0].equals("-d")){   //**** If the command line contains -d
            willDebug = true;
            String filename = args[1]; // ***** Then the second argument is filename
            try {
               processFile(filename, willDebug); //**** Go ahead and process the file name
            } catch (IOException error) {
                    auxlib.warn (error.getMessage());
         }
         } else {                      
           String filename = args[0]; //**** If debugging option isn't included, than second argument is filename
           try {
              processFile(filename, willDebug); //**** Go gead and process it
           }catch (IOException error) {
              auxlib.warn (error.getMessage());
           }
        }
        auxlib.exit();
    }
 
}