// Calc.java
// Define a class for doing RPN.
// this is a basic array implementarion for stacks
// We will be using this file along with our junit test file to see if our codes are correct


import java.lang.Math;

public class Calc {

    // Variables

    private double[] stackArray; // Create a stack
    private int maxArraySize = 100; // max size of the stack
    private int top; // top of the stack
    private int counter; // to keep track of elements in our stack

    

    // Constructor
    public Calc() {

        stackArray = new double[maxArraySize];
        top = -1;
        counter = 0;


        
    }
    
    // Push a number
    public void push(double x) {

        if( top == stackArray.length){

            throw new RuntimeException("Cannot add to a stack that is full");
      
        }

        stackArray[++top] = x;
        counter = counter + 1; // increment counter if element gets pushed into stack
    }
    
    // Pop top number (removes)
    public double pop() {

        if( top == -1 ){

            throw new RuntimeException("Cannot pop from an empty stack");
       
        }

        counter = counter - 1; // decrement counter if element gets popped from stack
        return stackArray[top--];

    }
    
    // Peek at top number (does not remove)
    public double peek() {

        if (top == -1 ){

            throw new RuntimeException("Cannot peek into a stack that is empty");
        
        }

        return stackArray[top];
    }
    
    // Add top two numbers
    public void add() {

       if ( top == -1 || top == 0 ){

             throw new RuntimeException("Cannot add two numbers if there are not two numbers in the stack");
      
       }

       double temp = 0;
       temp = pop();
       temp = temp + pop();
       push( temp );
        

    }
    
    // Subtract top two numbers (top on right side)
    public void subtract() {

         if ( top == -1 || top == 0 ){

             throw new RuntimeException("Cannot subtract two numbers if there are not two numbers in the stack");
       
       }

       double temp = 0;
       temp = pop();
       temp = pop() - temp;
       push(temp);
        

    }

    // Multiply top two numbers
    public void multiply() {

         if ( top == -1 || top == 0 ){

             throw new RuntimeException("Cannot multiply two numbers if there are not two numbers in the stack");
       
       }

        double temp = 1;
        temp = pop();
        temp = temp * pop();
        push (temp);
        
    }
    
    // Divide top two numbers (top on bottom)
    public void divide() {

        if ( top == -1 || top == 0 ){

             throw new RuntimeException("Invalid Operation");

       }
              
        double temp = 1;
        double tempPop = pop();
        temp =  temp / tempPop;

        if( tempPop == 0){

            throw new RuntimeException("Invalid Operation");

        }

        temp = temp * pop();
        push( temp );

    
}
    
    // Return how many numbers are in the stack
    public int depth() {

        return counter;
        
        
        
}

    // Return the log2 value of the top element in the stack
    public void log2() {

        int base = 2;
        double temp = pop();
        double temp2 = Math.log(temp)/Math.log(base);
        push(temp2);


    }

}
