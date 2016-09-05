// ***********************************************************************//
//  
// ** Adventure.java
// This file is contains the Adventure class
// Guides the user through the different rooms
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
import java.util.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import static java.lang.System.*;
import java.io.InputStreamReader;


public class Adventure {

// =======================================================================// 
// The different instances of Adventure
// =======================================================================// 

  private Room firstRoom;
  private Room lastRoom;
  private Room currRoom;


// =======================================================================// 
// This function checks to see if the room exists
// =======================================================================// 
  public Room existRoom (String tag) {

    Room tempRoom = this.firstRoom;

    while(tempRoom != null) {

      if(tempRoom.tag.equals(tag)) {
        return tempRoom;
      }

    tempRoom = tempRoom.next;
    }

    return null;

  }

  
// =======================================================================// 
// This function creates a room
// =======================================================================// 
  public Room createRoom (String tag) {

    Room tempRoom = new Room();
    tempRoom.tag =  tag;

    if (this.firstRoom == null) {
      this.firstRoom = tempRoom;
    }

    if (this.lastRoom != null) {
      this.lastRoom.next = tempRoom;
    }

    this.lastRoom = tempRoom;
    return tempRoom;
  }

// =======================================================================// 
// This function creates options and assigns to current room
// =======================================================================// 
  public void createOption (String details) {
    this.currRoom.createOption (details);
  }
  
// =======================================================================// 
// Function to display the current room
// =======================================================================// 
  public void displayRoom () {
    this.currRoom.display();
  }



// =======================================================================// 
// This function finds the current room
// =======================================================================// 
  public void currRoom (String tag, boolean undo) {
    Room tempRoom = this.existRoom (tag);

    if (tempRoom == null) {
      tempRoom = this.createRoom (tag);
    }

    if (undo) {
      tempRoom.prev = this.currRoom;
    }

    this.currRoom = tempRoom;
  }

// =======================================================================// 
// This function sets the current room description
// =======================================================================// 
  public void descriptions (String description) {
    this.currRoom.description += "\n" + description;
  }

// =======================================================================// 
// Function to assign information to rooms
// =======================================================================// 
  public void information () {

    Room tempRoom = firstRoom;

    while(tempRoom != null) {
      tempRoom.information();
      tempRoom = tempRoom.next;
    }
  }



// =======================================================================// 
// This function updates the last option tag
// =======================================================================// 
  public void updateLastOptionTag (String tag) {
    this.currRoom.updateLastOptionTag (tag);
  }

 
// =======================================================================// 
// Reccurring function that changes current room
// =======================================================================// 
  public void choiceMade(String choice) {

    String nextRoom = this.currRoom.choiceMade(choice);

    if(nextRoom == null) {
      System.out.println("\033[31mERROR: Try again!\033[0m");
    } else {
      this.currRoom(nextRoom,true);
    }
  }


 
// =======================================================================// 
// Function that allows player to go to the previous room
// =======================================================================// 
  public void undo() {
    if(this.currRoom != null && this.currRoom.prev != null) {
      this.currRoom = this.currRoom.prev;
    } else {
      System.out.println("\033[31mThere is nothing to undo.\033[0m");
    }
  }


}