#include <iostream>
#include <string>
using namespace std;

class IntPointerHandler {
private:
    string label;
    int value;
    int* pointer;
    int counter;

public:
    IntPointerHandler(string lbl, int index) {
        label = lbl;
        counter = index + 1;
        // Create an integer pointer using new operator
        pointer = new int;
    }

void getInput() {
    string input;
    while (true) {
        cout << label << ": ";
        getline(cin, input);
        try {
            *pointer = stoi(input);
            value = *pointer;
            break;
        } catch (invalid_argument&) {
            cout << "Invalid input: not a number. Please try again." << endl;
        } catch (out_of_range&) {
            cout << "Invalid input: number out of range. Please try again." << endl;
        }
    }
}


    void printResult() {
        // Display the contents of the variables and pointers
        cout << "Result " << counter << " (" << label << "): Value = " << value
             << ", Pointer Value = " << *pointer << ", Pointer Address = " << pointer << endl;
    }

    void cleanup() {
        // Release memory using delete operator
        delete pointer;
    }
};

int main() {
    cout << "Enter three integer values" << endl;
    string labels[3] = {"First Value", "Second Value", "Third Value"};

    for (int i = 0; i < 3; ++i) {
        IntPointerHandler handler(labels[i], i);
        handler.getInput();
        handler.printResult();
        handler.cleanup();
    }

    return 0;
}
