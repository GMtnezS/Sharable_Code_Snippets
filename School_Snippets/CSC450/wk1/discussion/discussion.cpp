#include <iostream>
#include <string>

// left some notes and dividers for myself... :D first time with C++ and want to make sure I don't miss anything!
int main() {
	// defining a variable called rawInput to store a 'string' or more accurately, this is a char[]
	// notice that it has an specific length like java arrays usually do, this makes it unsafe and inefficient. 
	
    char rawInput[5];       // Fixed-size, risky if input is too long
    
    ///////////////////////////////
    
    //defining a variable called safeInput to store a dynamic string. 
    // This really is a dynamic string handle so it is safe and efficient for dynamic strings. 
    
    std::string safeInput;  // Dynamically sized, safer option
    
    ///////////////////////////////

	// This is like a console.log or print for those that come from Python and Javascript - just printing to the console.
	
    std::cout << "Enter your name (unsafe for possibly long strings): ";
    
    ///////////////////////////////
    
    // this is the line that "catches" and "listens for" the input and assigns it to rawInput (or variable in mention)
    // it it kinda like the input() method in python that was split into two steps, 
    // and also notice that assignment in here is left to right instead of right to left. Personally it took be out of guard. 
    
    std::cin >> rawInput;   // Risk of overflow if user types more than 4 characters
    
    // Notice that is "RISK" it turns out wild things can happen when you take up more space than reserved o.o the wildest is that it does not always crash...

    ///////////////////////////////
	// then we just repeat... 
    ///////////////////////////////
	
    std::cout << "Enter your name (safe since string is handled dynamically): ";
    std::cin >> safeInput;
    
    ///////////////////////////////

	// Personally found std::endl; weird, as python and Javascript don't "hold back" on logs
	// Will be interesting to explore further into the rules behind when to use it and when not to. 
	
    std::cout << "Unsafe input: " << rawInput << "\n";
    std::cout << "Safe input: " << safeInput << std::endl;

    return 0;
}

// Note: personally, I really don't like ECLIPSE so far T~T but i'll survive! At least adding some dividers helped with readability. 