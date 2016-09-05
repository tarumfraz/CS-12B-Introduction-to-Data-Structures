// ***********************************************************************//

// ***********************************************************************//
//  
// ** Edfile.java
// **
// ** This file is part of asg 5, contains the main method, and reads 
// ** arguments from the command line.
// ** 
// ** This file also throws the appropiate arguments in a doubly linked
// ** which then aids our goal in creating a text editor
// ** 
//      
// ***********************************************************************//

// ***********************************************************************//   

import java.util.Scanner;
import static java.lang.System.*;
import java.io.*;
import java.util.ArrayList;


class edfile{
   
   public int var;
   public static dllist linkedList = new dllist();
   static int size;
   static int counter;

   public static void main (String[] args) throws IOException{
     System.out.println("Welcome");
     boolean want_echo = false;
     dllist lines = new dllist ();
    
// ***********************************************************************//  
//  Scan the command line arguments
// ***********************************************************************//  
      if( args.length > 2 )

          auxlib.usage(args); 
      
      else if( args.length == 1 && args[0].equals("-e"))

          want_echo = true;

      else if( args.length == 1 ){

          want_echo = false;
          fileReader( args[0],0 );
      }
      else if( args.length == 2 && args[0].equals("-e")){

          want_echo = true;
          fileReader( args[1],0 );
      }
      else if( args.length == 0 )

          want_echo = false;

      


// ***********************************************************************//  
//  Scan user input -- represents commands in our text editor
// ***********************************************************************//    

      Scanner stdin = new Scanner (in);
      
         for (;;) {
         if (! stdin.hasNextLine()) break;
        
         String inputline = stdin.nextLine();
        
         if (want_echo) out.printf ("%s%n", inputline);
         if (inputline.matches ("^\\s*$")) continue;
        
         char command = inputline.charAt(0);
      
// ***********************************************************************//  
//  Implement text editor, react based one what is entered on command line
// ***********************************************************************//  

         switch (command) {
            case '#': 
            break;
            case '$': 
            linkedList.setPosition(dllist.position.LAST); 
            System.out.println(linkedList.getItem()); 
            break;
            case '*':
            linkedList.setPosition(dllist.position.LAST);
            for( int i = 0; i < size; i++)
            {
                System.out.println(linkedList.getItem());
                if(!(i == size -1))
                  linkedList.setPosition(dllist.position.FOLLOWING);
              }
              linkedList.setPosition(dllist.position.LAST);
            break;
            case '.': 
            System.out.println(linkedList.getItem()); 
            break;
            case '0': 
            linkedList.setPosition(dllist.position.FIRST); 
            System.out.println(linkedList.getItem()); 
            break;
            case '<': 
            linkedList.setPosition(dllist.position.PREVIOUS); 
            System.out.println(linkedList.getItem()); 
            break;
            case '>': 
            linkedList.setPosition(dllist.position.FOLLOWING); 
            System.out.println(lines.getItem()); 
            break;
            case 'a': 
            String str1 = inputline.substring(2);
            linkedList.insert(str1,dllist.position.FOLLOWING);
            System.out.println(linkedList.getItem());
            size++;
            break;
            case 'd': 
            linkedList.delete(); 
            counter--;
            break;
            case 'i': 
            String str2 = inputline.substring(2);
            linkedList.insert(str2, dllist.position.PREVIOUS);
            System.out.println(linkedList.getItem());
            size++;
            break;
            case 'r': 
            String str3 = inputline.substring(2);
            fileReader(str3, 1);
            System.out.println(counter + "newly inserted lines");
            break;
            case 'w': 
            String str4 = inputline.substring(2);

            PrintWriter writer = new PrintWriter(str4, "UTF-8");

            if(str4.equals("") || linkedList.isEmpty())

                System.out.println("File cannot be created or written");

           else{
              
                linkedList.setPosition(dllist.position.FIRST);

                for (int i = 0; i < size; i++)
                {

                  writer.println(linkedList.getItem());
                  
                  if(!(i == size -1))

                    linkedList.setPosition(dllist.position.FOLLOWING);
                }
              }

              writer.close();
              System.out.println(size + " number of lines written to the file " + str4);
             
              if(str4.equals("") || linkedList.isEmpty())

                System.out.println("File cannot be created or written.");
              
              else
              {
                linkedList.setPosition(dllist.position.FIRST);
                for (int i = 0; i < size; i++)
                {
                  writer.println(linkedList.getItem());

                  if(!(i == size -1))

                    linkedList.setPosition(dllist.position.FOLLOWING);
                }
              }
            
              writer.close();
              System.out.println(size + " number of lines written to the file " + str4);
              break;
              default : 
              System.out.println("Print invalid command.");
              break;
         }
      }
      
      auxlib.die(args);

   }
// ***********************************************************************//  
//  Function to read the file and put it in a linked list
// ***********************************************************************//     
public static void fileReader( String inputFile, int x ) throws IOException{

      BufferedReader br = new BufferedReader( new FileReader(inputFile));
      String line;
      int z = x;
      counter = 0;
        
      while( (line = br.readLine() ) != null ) 
      {
      if( z == 0 )
      {
        linkedList.insert( line, dllist.position.FIRST );
         z++;
      }else{
        linkedList.insert( line, dllist.position.FOLLOWING );
      }
      size++;
      counter++ ;
      } 

      br.close();
   }

  

}

