// =======================================================================// 
// list.c
// Here is a correction of a simple implementation of a singly linked list
// This program originally contained many bugs and memeory leaks when
// ran with valgrind
// A list of all corrections can be found in the file BUGS
// =======================================================================//  


#include <stdio.h>
#include <stdlib.h>

// A node in a singly-linked list
struct node {
    int value;
    struct node *next;
};

// Head of the linked list
struct node *head = NULL;

// Insert a value into linked list
void list_insert(int v) {
    struct node *n = malloc(sizeof(struct node));
    n->value = v;
    n->next = head;
    head = n;
}

// Insert two values at once into linked list
void list_insert2(int a, int b) {
    struct node *u = malloc(sizeof(struct node)); 
    struct node *v = malloc(sizeof(struct node));
    u->value = a;
    v->value = b;
    u->next = v;
    v->next=head;
    head = u;
    
}

// Remove an element from linked list
void list_remove(int v) {
    struct node *n = head;
    struct node *prev = head;
    while(n->value != v)
    {
        if(n->next == NULL){
            
        }else
        {
            prev = n;
            n = n->next;
        }
    }
    if(n == head){
        head = n->next;
        free(n);
    }else
        prev->next = n->next;
        free(n);

}
   


// Print out all values in linked list
void list_printall(void) {
    struct node *p = head;
    while(p != NULL) {
        printf("%d ", p->value);
        p=p->next;
    }
        printf("\n");
}

// Deallocate all memory used in linked list
void list_destroy(void) {

        struct node *p = head;
        struct node *next = NULL;

        while(p != NULL ) {
           next = p->next;
           free(p);
           p = next;
    
         }
       
}  

int main(int argc, char *argv[]) {
    printf("Test linked lists\n");
    list_printall(); // Should print nothing
    list_insert(42);
    list_insert2(17, 10);
    list_insert(18);
    list_remove(10);
    list_printall(); // Should print 18 17 42
    // Cleanup memory
    list_destroy();
    return 0;
}
