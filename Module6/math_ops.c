#include <stdio.h>

int sum(int a, int b)
{
    return a + b;
}

void print_math(int *a, int *b)
{
    int sum_result = sum(*a, *b);
    int product = *a * *b;

    printf("Sum: %d\n", sum_result);
    printf("Product: %d\n", product);
}

int main()
{
    int a, b;
    printf("Enter first number: ");
    scanf("%d", &a);
    printf("Enter second number: ");
    scanf("%d", &b);
    print_math(&a, &b);
    return 0;
}