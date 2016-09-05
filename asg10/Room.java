// ***********************************************************************//
//  
// ** Room.java
// This file is a room class for cyoa
//      
// ***********************************************************************//

class Room {

//instances of class Room

  public Room next;
  public Room prev;
  public String tag;
  public Option lastOption;
  public Option firstOption;
  public int optionsCreated = 0;
  public String description = "";
  public String[] optionTags = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l"};


// =======================================================================// 
// This function creates objects of type option
// controls options within our game       
// =======================================================================// 
  public void createOption(String details) {

    Option tempOption = new Option();
    tempOption.details = details;

    tempOption.choiceTag = optionTags[optionsCreated];

    if (this.firstOption == null) {
      this.firstOption = tempOption;
    }

    if (this.lastOption != null) {
      this.lastOption.nextOption = tempOption;
    }

    this.lastOption = tempOption;
    optionsCreated++;

  }

// =======================================================================// 
// This function iterates though options
// =======================================================================// 
  public String choiceMade(String choice) {

    Option tempOption = this.firstOption;

    while(tempOption != null) {

      if(tempOption.choiceTag.equals(choice)) {
        System.out.println("\033[35m[\033[0m" + "\033[35m" + tempOption.details + "\033[0m" + "\033[35m]\033[0m");
        return tempOption.targetRoom;
      }

    tempOption = tempOption.nextOption;
    }

    return null;
  }

// =======================================================================// 
// This function updates the option tag within our game      
// =======================================================================// 
  public void updateLastOptionTag(String tag) {
    this.lastOption.targetRoom = tag;
  }

 


// =======================================================================// 
// Function to display information about tags in game
// =======================================================================// 
  public void information() {
    String target = "";
    Option tempOption = this.firstOption;

    while(tempOption != null) {
      if(target.length() != 0) {
        target = target + " ";
      }

      target = target + tempOption.targetRoom;
      tempOption = tempOption.nextOption;
    }

    System.out.println( "\033[33m" + this.tag + "\033[0m" + "\033[33m: \033[0m"
    + "\033[33m" + target + "\033[0m");
  }
// =======================================================================// 
// function that displays desctiptions
// =======================================================================// 
  public void display() {
    Option tempOption = this.firstOption;
    System.out.println("\n" + this.description + "\n");

    while(tempOption != null) {
      System.out.println(tempOption.choiceTag + " - " + tempOption.details);
      tempOption = tempOption.nextOption;
    }

    System.out.println();
  }
}