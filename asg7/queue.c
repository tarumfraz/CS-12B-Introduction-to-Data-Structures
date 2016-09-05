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

queue *queue_new(void) {
   queue *this = malloc(sizeof( struct queue));
   this->front = this->rear = NULL;
   return this;
}

void queue_free(queue *this) {
   assert(queue_isempty(this));
   free(this);
}





void queue_insert(queue *this, queue_item_t item) {
   queue_node *p = malloc(sizeof( struct queue_node ));
   if( p == NULL ){
      fprintf(stderr, "ERROR!\n");
      exit(1);  
   }

   p->item = item;
   p->link = NULL;

   if( this->front == NULL ){ //queue is empty
      this->front = this->rear = p;
   }else{
      this->rear->link = p;
      this->rear = p;
   }
}

queue_item_t queue_remove(queue *this) {
   assert(!queue_isempty(this));
   this->front = front;
   if(this->front->link != NULL ){
   this->front = this->front->link;
   free(front);
   front = this->front;
}
 else{
   free(front);
   front = NULL;
   rear = NULL;
 }

 return NULL;
}

bool queue_isempty(queue *this) {
   return this->front == NULL;
   free(this);
}
