#include <iostream>
using namespace std;

int main() {
    int x = 10;

    // The operator & gives the address of x, stored in ptr
    int* ptr = &x;      
    cout << "Address of x: " << ptr << endl;    
    // Outputs memory address of X
    
    // The operator * accesses the value at that address and modifies it
    *ptr = 20;          
    cout << "Value at ptr: " << *ptr << endl;  
     // Outputs: 20

    return 0;
}
