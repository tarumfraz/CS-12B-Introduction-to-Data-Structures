/****
* queue.c 
* This C program is an array implementation of a circular queue of maximum size 100
* There are unit tests to see if this program works the way we want it too
*
****/

#include <stdio.h>
#include <stdlib.h>
#include "minunit.h"

#define MAXSIZE 100
static int front; 
static int rear;
static int counter;
int *queueArray;

int tests_run = 0; // keep track of number of unit tests run

typedef enum q_status {
    /* Enumerated status codes for queue operations */
    q_success = 0,
    q_failure
} q_status;

/**** Private variables for queue ****/


/**** Functions on queues ****/

q_status q_init(void) {
    /* Initialize the queue */

    queueArray = (int *)malloc(MAXSIZE*sizeof(int));  
    front = 0;
    rear = -1;
    counter = 0;
    return q_success;

    if( queueArray == NULL)
    return q_failure;
}

q_status q_insert(int value) {
    /* Insert an item into back of queue
       Returns q_success on success.
    */

    if(counter == MAXSIZE){
        return q_failure;
    }
    else if(rear == MAXSIZE - 1) // wraparound
    rear = -1; 
    queueArray[++rear] = value; //increment the end and insert value
    counter++; // add one to our counter
    return q_success;

}

q_status q_remove(int *value) {
    /* Remove item from front of queue

       Stores item at front of queue into pointer
       given as argument. Removes item from queue.

       Returns qSuccess on success.
    */

    if(counter == 0 || value == NULL ){
        return q_failure;
    }
    
    *value = queueArray[front++]; // get the value that is in the front of the queue
    if( front == MAXSIZE ) // wraparound
        front = 0;
        counter--; //decrement our counter
        return q_success;
}

q_status q_peek(int *value) {
    /* Peek at item at front of queue

       Stores item at front of queue into pointer
       given as argument. Queue is not altered.

       Returns qSuccess on success.
    */
      if(counter == 0 || value == NULL ){
        return q_failure;
    }
    
    *value = queueArray[front];
    return q_success;
}


q_status q_destroy(void) {
    /* Destroy the queue */
    if(queueArray != NULL){
        free(queueArray);
        return q_success;
   }
    return q_failure;
}

/**** FUNCTION TO CHECK IF QUEUE IS EMPTY OR NOT ****/
q_status q_is_empty(void){
    if( counter == 0 )
        return q_success;
    else
        return q_failure;
}

/**** Unit tests ****/

char * test_lifecycle(void) {
    mu_assert("init", q_init() == q_success);
    mu_assert("destroy", q_destroy() == q_success);
    return NULL;
}

char * test_one_insert(void) {
    mu_assert("init", q_init() == q_success);
    mu_assert("insert", q_insert(7) == q_success);
    mu_assert("destroy", q_destroy() == q_success);
    return NULL;
}

char * test_one_insert_remove(void) {
    int v;
    mu_assert("init", q_init() == q_success);
    mu_assert("insert", q_insert(16) == q_success);
    mu_assert("remove", q_remove(&v) == q_success);
    mu_assert("value", v == 16);
    mu_assert("destroy", q_destroy() == q_success);
    return NULL;
}

char * test_two_insert_remove(void) {
    int v;
    mu_assert("init", q_init() == q_success);
    mu_assert("insert", q_insert(8) == q_success);
    mu_assert("insert", q_insert(91) == q_success);
    mu_assert("remove", q_remove(&v) == q_success);
    mu_assert("value", v == 8);
    mu_assert("remove", q_remove(&v) == q_success);
    mu_assert("value", v == 91);
    mu_assert("destroy", q_destroy() == q_success);
    return NULL;
}

char * test_multi_insert_remove(void) {
    int v;
    mu_assert("init", q_init() == q_success);
    mu_assert("insert", q_insert(8) == q_success);
    mu_assert("insert", q_insert(91) == q_success);
    for(int i = 0; i < 1000; i++) {
        mu_assert("insert", q_insert(8) == q_success);
        mu_assert("insert", q_insert(91) == q_success);
        mu_assert("remove", q_remove(&v) == q_success);
        mu_assert("value", v == 8);
        mu_assert("remove", q_remove(&v) == q_success);
        mu_assert("value", v == 91);
    }
    mu_assert("remove", q_remove(&v) == q_success);
    mu_assert("value", v == 8);
    mu_assert("destroy", q_destroy() == q_success);
    return NULL;
}

char * test_indexed_insert_remove(void) {
    int v;
    mu_assert("init", q_init() == q_success);
    for(int i = 0; i < 30; i++) {
        mu_assert("insert", q_insert(i) == q_success);
    }
    for(int i = 0; i < 1000; i++) {
        mu_assert("remove", q_remove(&v) == q_success);
        mu_assert("value", v == i);
        mu_assert("insert", q_insert(30 + i) == q_success);
    }
    mu_assert("destroy", q_destroy() == q_success);
    return NULL;
}

char * test_too_many_remove(void) {
    int v;
    mu_assert("init", q_init() == q_success);
    mu_assert("remove", q_remove(&v) == q_failure);
    return NULL;
}

char * test_insert_too_many_remove(void) {
    int v;
    mu_assert("init", q_init() == q_success);
    for(int i = 0; i < 30; i++) {
        mu_assert("insert", q_insert(i) == q_success);
    }
    for(int i = 0; i < 30; i++) {
        mu_assert("remove", q_remove(&v) == q_success);
        mu_assert("value", v == i);
    }
    mu_assert("remove", q_remove(&v) == q_failure);
    return NULL;
}

char * test_peek(void) {
    int v;
    mu_assert("init", q_init() == q_success);
    mu_assert("insert", q_insert(18) == q_success);
    mu_assert("peek", q_peek(&v) == q_success);
    mu_assert("value", v == 18);
    mu_assert("destroy", q_destroy() == q_success);
    return NULL;
}

char * test_peek_two(void) {
    int v;
    mu_assert("init", q_init() == q_success);
    mu_assert("insert", q_insert(18) == q_success);
    mu_assert("insert", q_insert(42) == q_success);
    mu_assert("peek", q_peek(&v) == q_success);
    mu_assert("value", v == 18);
    mu_assert("destroy", q_destroy() == q_success);
    return NULL;
}

char * test_peek_deep(void) {
    int v;
    mu_assert("init", q_init() == q_success);
    for(int i = 0; i < 30; i++) {
        mu_assert("insert", q_insert(i) == q_success);
    }
    for(int i = 0; i < 1000; i++) {
        mu_assert("peek", q_peek(&v) == q_success);
        mu_assert("value", v == i);
        mu_assert("remove", q_remove(&v) == q_success);
        mu_assert("value", v == i);
        mu_assert("insert", q_insert(30 + i) == q_success);
    }
    mu_assert("destroy", q_destroy() == q_success);
    return NULL;
}

char * test_peek_empty(void) {
    int v;
    mu_assert("init", q_init() == q_success);
    mu_assert("peek", q_peek(&v) == q_failure);
    return NULL;
}

char * test_peek_two_empty(void) {
    int v;
    mu_assert("init", q_init() == q_success);
    mu_assert("insert", q_insert(18) == q_success);
    mu_assert("insert", q_insert(42) == q_success);
    mu_assert("remove", q_remove(&v) == q_success);
    mu_assert("remove", q_remove(&v) == q_success);
    mu_assert("peek", q_peek(&v) == q_failure);
    return NULL;
}

char * test_remove_null(void) {
    mu_assert("init", q_init() == q_success);
    mu_assert("insert", q_insert(18) == q_success);
    mu_assert("peek", q_remove(NULL) == q_failure);
    return NULL;
}

char * test_peek_null(void) {
    mu_assert("init", q_init() == q_success);
    mu_assert("insert", q_insert(18) == q_success);
    mu_assert("peek", q_peek(NULL) == q_failure);
    return NULL;
}

/* UNIT TEST #1 ADD AND CHECK THREE INTEGERS */
char * test_three_integers(void){
    int v;
    mu_assert("intit", q_init() == q_success);
    mu_assert("insert",q_insert(2) == q_success);
    mu_assert("insert",q_insert(4) == q_success);
    mu_assert("insert",q_insert(6) == q_success);
    mu_assert("remove",q_remove(&v) == q_success);
    mu_assert("value", v == 2);
    mu_assert("remove",q_remove(&v) == q_success);
    mu_assert("value", v == 4);
    mu_assert("remove",q_remove(&v) == q_success);
    mu_assert("value", v == 6);
    mu_assert("destroy", q_destroy() == q_success);
    return NULL;
}

/* UNIT TEST #2 CHECK TO SEE IF Q IS EMPTY FUNCTION IMPLEMENTATION IS A SUCCCESS*/
char * test_q_is_empty_success(void){
    mu_assert("init", q_init() == q_success);
    mu_assert("is_empty", q_is_empty() == q_success);
    return NULL;
}

/* UNIT TEST #3 CHECK TO SEE IF Q IS EMPTY FUNCTION IMPLEMENTATION IS A FAILURE */
char * test_q_is_empty_failure(void){
    mu_assert("init", q_init() == q_success);
    mu_assert("insert", q_insert(4) == q_success);
    mu_assert("is_empty", q_is_empty() == q_failure);
    return NULL;
}


char * all_tests(void) {
    /* Run all unit tests */
    mu_run_test(test_lifecycle);
    mu_run_test(test_one_insert);
    mu_run_test(test_one_insert_remove);
    mu_run_test(test_two_insert_remove);
    mu_run_test(test_multi_insert_remove);
    mu_run_test(test_indexed_insert_remove);
    mu_run_test(test_too_many_remove);
    mu_run_test(test_insert_too_many_remove);
    mu_run_test(test_peek);
    mu_run_test(test_peek_two);
    mu_run_test(test_peek_deep);
    mu_run_test(test_peek_empty);
    mu_run_test(test_peek_two_empty);
    mu_run_test(test_remove_null);
    mu_run_test(test_peek_null);
    mu_run_test(test_three_integers);
    mu_run_test(test_q_is_empty_success);
    mu_run_test(test_q_is_empty_failure);
    return NULL;
}

/**** Main unit test runner ****/

int main(int argc, char *argv[]) {
    printf("Queue Module unit tests\n");
    char *result = all_tests();
    if (result) {
        printf("FAILURE at %s\n", result);
    } else {
        printf("ALL TESTS PASS\n");
    }
    printf("Tests run: %d\n", tests_run);
    return EXIT_SUCCESS;
}
