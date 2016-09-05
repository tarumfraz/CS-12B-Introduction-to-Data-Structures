// ***********************************************************************//

// ***********************************************************************//
//  
// ** Queue.java
// ** 
// ** This file is the code for a generic queue w/ iteration
// 
// ** For this file, I used notes from the textbook, help from the TAs,
// ** my partner, generic code help such as how to use the Iterator,
// ** and my previous codes as well
//      
// ***********************************************************************//

// ***********************************************************************// 

import java.util.Iterator;
import java.util.NoSuchElementException;
 
class Queue <Item> implements Iterable <Item> {
 
   private class Node {
      Item item;
      Node next;
 
      Node(Item newItem){ //**** A call to this will fill up our node
             item = newItem; 
             next = null;
      }
   }


   private Node head = null;
   private Node tail = null;

 
// ========================================================================// 
//  Boolean method to check if our queue is empty or not                   //        
// ========================================================================//  
   public boolean isempty() {
           if (head == null){
            return true;
           }
         
        else
         return false;
        }

 // =======================================================================// 
//  Method to insert items into our code                                   //        
// ========================================================================//   
   public void insert(Item newitem) {
           Node focusTail = tail;

           tail = new Node(newitem);
         
         if( isempty()){
            head = tail;
      }
         else         
          focusTail.next = tail ;
           
        
     }
           
    
 
   public Iterator<Item> iterator() {
      return new Itor ();
   }
 
   class Itor implements Iterator <Item> {
      Node current = head;
      public boolean hasNext() {
         return current != null;
      }
      public Item next() {
         if (! hasNext ()) throw new NoSuchElementException();
         Item result = current.item;
         current = current.next;
         return result;
      }
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
 
}
