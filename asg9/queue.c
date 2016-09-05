// *********************************************************************************//

// *********************************************************************************//

//  
// ** queue.c 
// ** 
// ** This file representsthe Abstract Data Type for our queue implementatiom
// ** I used the help of https://www.cs.bu.edu/teaching/c/queue/linked-list/funcs.html
// ** Which explained in detail in how to implement a queue using ADT/CDT
//      
// *********************************************************************************//


// *********************************************************************************//




#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "queue.h"

#define STUBPRINTF(...) fprintf(stderr, __VA_ARGS__);

/* Internal implementation definitions */
struct queue_node {
   queue_item_t item;
   struct queue_node *link;
};

typedef struct queue_node queue_node;

struct queue {
   queue_node *front;
   queue_node *rear;
};

/* Functions */


// =======================================================================// 
// Function for creating a new queue                                      //        
// =======================================================================//  

queue *queue_new(void) {
   queue *this = malloc(sizeof( struct queue));
   assert(this != NULL);
   this->front = this->rear = NULL;
   return this;
}

void queue_free(queue *this) {
   assert(queue_isempty(this));
   free(this);
}


// =======================================================================// 
// Function for creating inserting into queue                             //        
// =======================================================================//  


void queue_insert(queue *this, queue_item_t item) {
   queue_node *p = malloc(sizeof( queue_node ));
   assert( p != NULL );
   p->item = item;
   p->link = NULL;
   if(this->front == NULL) 
      this->front = p;
   else this->rear->link = p;
      this->rear = p;
     
   
}

// =======================================================================// 
// Function for removing from  queue                                      //        
// =======================================================================//  



queue_item_t queue_remove(queue *this) {


   assert(!queue_isempty(this));

   queue_node *t = NULL;
   t = this->front;
   this->front = this->front->link;
   queue_item_t temp = t->item;
   free(t);
   return temp;
}

bool queue_isempty(queue *this) {
   return this->front == NULL;
   free(this);
}
