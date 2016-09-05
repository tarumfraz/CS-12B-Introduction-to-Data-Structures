// ***********************************************************************//

// ***********************************************************************//
//  
// ** Tree.java
// ** 
// ** This file is the code for our BST wich contains the queues as leaves
// ** Debugs, and outputs our  tree as well
// 
// ** For this file, I used notes from the textbook, help from the TAs,
// ** my partner, and generic code help such as how to use the Iterator
//      
// ***********************************************************************//

// ***********************************************************************// 

import static java.lang.System.*;

 
class Tree {
 
    private class Node {
        String key;
        Queue <Integer> value;
        Node left;
        Node right;
 
        Node(String data){
           key = data;
           left = null;
           right = null;
        }
    }
    private Node root = null;

 // =======================================================================// 
// If "-d" is entered on the command line , this method prints it out      //        
// =======================================================================//  
 
    private void debugHelper(Node tree, int depth) {
        String spaces = "";
 
        if (tree == null) return;
        debugHelper(tree.left, depth + 1);
        for(int i = 0; i<depth; i++){
           spaces = spaces + "  ";
        }
        System.out.println(spaces + depth + " " + tree.key);
        debugHelper(tree.right, depth + 1);
       
    }
 
// =======================================================================// 
// This method helps us find a certain key within the BST                 //        
// =======================================================================//  

    private boolean contains(Node treeRoot, String searchKey) {
             if (treeRoot == null) return false;
             if (treeRoot.key.equals(searchKey)) return true;
             return contains(treeRoot.left, searchKey) || contains(treeRoot.right, searchKey);
    }

// =======================================================================// 
// This function aids us in the output of the queue leaves                //        
// =======================================================================//  
 
    private void outputHelper(Node tree) {
        if(tree == null)
         return;
        if(tree.left != null){
        outputHelper(tree.left);
        }
        System.out.print(tree.key + " " + ":" + " ");

        Queue<Integer>.Itor iterator = (Queue<Integer>.Itor)tree.value.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");

        }
        System.out.println();
        outputHelper(tree.right);



//        throw new UnsupportedOperationException();
    }
 
    public void insert(String key, Integer linenum) {
            root = insertHelper(root, key, linenum);
       
       
    }

// =======================================================================// 
// This is a funciton not included in the template, which allows us       //
// to inser into the BST efficiently and correctly                        //        
// =======================================================================//  

    public Node insertHelper(Node node, String key, Integer linenum ){


        if( node == null ){

            node = new Node(key);
            node.value = new Queue<Integer>();
            node.value.insert(linenum);
        }else{
            if( key.equals(node.key)){
                node.value.insert(linenum);
            }
            else if( key.compareToIgnoreCase(node.key) < 0 ) 
            {
               node.left =  insertHelper(node.left,key, linenum);
            }
            else{
                node.right = insertHelper(node.right, key, linenum);
            
            }       
}
        return(node); 
}



    public void debug() {
       
        debugHelper(root, 0);
    }

    
 
    public void output() {
        // Show sorted words with lines where each word appears
        outputHelper(root);
    }
 
 
}