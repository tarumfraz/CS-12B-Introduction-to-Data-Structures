// dllist.java
// Template code for doubly-linked list of strings.
//received help from other students to complete certain facets of this code
//specifically for the setPosition method 
public class dllist {

   public enum position {FIRST, PREVIOUS, FOLLOWING, LAST};

   private class node {
      String item;
      node prev;
      node next;
   }

   private node first = null;
   private node current = null;
   private node last = null;
   private int currentPosition = 0;
   int counter;




      //Changes the current position to be one of the places specified by the enum argument. This
      //method is used to move the current position to the first, last, previous, or following string.
      //Attempts to move the current position before the first position or following the last position are
      //silently ignored.
   public void setPosition (position pos) {
/*      if( this.current == null){
     throw new java.util.NoSuchElementException();
      }*/
      if(pos == position.PREVIOUS && current == first)
      {
        throw new java.util.NoSuchElementException();
       
      }
      if(pos == position.FOLLOWING && current == last)
      {
        throw new java.util.NoSuchElementException();
      }
      if(pos == position.FIRST)
      {
              current = first;
              currentPosition = 0;
      }
      if(pos == position.PREVIOUS)
      {
              current = current.prev;
              currentPosition--;
      }
      if(pos == position.FOLLOWING)
      {
              current = current.next;
              currentPosition++;
      }
      if(pos == position.LAST)
      {
              current = last;
              currentPosition = counter;
      }
      }

      //returns true if the list is empty, false otherwise

   public boolean isEmpty () {
      if( first == null ){
        return true;
   }
      return false;
   }


      //returns the string at the current position without 
      //changing anything in the list. throws exception 
      //java.util.NoSuchElementException if there are no 
      //elements in the list
   public String getItem () {
      if(isEmpty()){
      throw new java.util.NoSuchElementException();
   }
   return current.item;
}


      //returns the relative numerical position of the
      //current element in the list. The first position
      //in the list is position 0. throws 
      //java.util.NoSuchElementException if there are no
      //elements in the list
   public int getPosition() {
      if(isEmpty()){
      throw new java.util.NoSuchElementException();
   }
      return currentPosition;
   }

      //deletes the string at the current position in the list
      //and makes the following string the current position.
      //if the last string in the list is deleted then the
      //current position becomes the new last string.
      //throws java.util.NoSuchElementException if there are
      //no elements in the list
   public void delete () {
      if(isEmpty()){
      throw new java.util.NoSuchElementException();
   }
      if( current != null){
         if(current == first && current == last){
            first = current.next;
            current.next.prev = null;
            current = first;
         }
         else if( current == last) {
            last = current.prev;
            current.prev = null;
            current.next = null;
            current = last;
            
         }else{
            current.prev.next = current.next;
            current.next.prev = current.prev;
            current = current.next;
         }

      }
   }


      //inserts new string into the list at the specified
      //position. the new item can be inserted as the first
      //element, last element, immediately before the current
      //position, or immediately after the current position. 
      //the element just inserted becomes the new current element.
      //throws IllegalArgumentException if the position argument
      //does not make sense for the current string
   public void insert (String item, position pos) {
      node newNode = new node();
      newNode.item = item;
      if(isEmpty() && (pos == position.FIRST || pos == position.LAST))
       {
         first = newNode;
         last = newNode;
         current = newNode;
         currentPosition++;
         counter++;
         return;
       }
       if(pos == position.FIRST)
       {
        newNode.next = first;
        newNode.prev = null;
        first.prev = newNode;
        current = newNode;
        first = newNode;
      }
      if(pos == position.PREVIOUS)
      {
        newNode.prev = current.prev;
          newNode.next = current;
          current.prev.next = newNode;
          current.prev = newNode;
          current = current.prev;
          currentPosition--;
          counter++;
      }
      if(pos == position.FOLLOWING)
      {
        if (current == last)
        {
          newNode.prev = last;
            newNode.next = null;
            last.next = newNode;
            current = newNode;
            last = newNode;
        }
        else
        {
        newNode.prev = current;
          newNode.next = current.next;
          current.next.prev = newNode;
          current.next = newNode;
          current = newNode;
        }
          currentPosition++;
          counter++;
      }
      if(pos == position.LAST)
      {
        newNode.prev = last;
        newNode.next = null;
        last.next = newNode;
        current = newNode;
        last = newNode;
      }
     
   }
 
}