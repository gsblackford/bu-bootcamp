#include <stdio.h>

void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

// This function doesnt swap because I am passing the values of x and y to the function instead of their addresses. 
// In C, when you pass variables to a function, they are passed by value, meaning that the function receives copies of the variables. 
// Therefore, any changes made to the parameters inside the function do not affect the original variables in the calling function.
void broken_swap(int a, int b) {
    int temp = a;
    a = b;
    b = temp;
}

int main() {
    int x = 5, y = 10;
    printf("Before swap: x = %d, y = %d\n", x, y);
    broken_swap(x, y);
    printf("After swap: x = %d, y = %d\n", x, y);
    return 0;
}