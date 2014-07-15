#include <stdio.h>

int compute(int a) {
        int b = a * 5;
	return b;
}

int main(void) {
        
        int y = compute(20);

        printf("%d\n", y);

        return 0;
}

