#include <iostream>
#include <string>
#include <map>
#include <array>
#include <vector>
using namespace std;

struct InputConfig {
    array<string, 2> labels;
    string delimiter;
};

using InputMap = map<string, InputConfig>;

class InputHandler {
private:
    string inputType;
    array<string, 2> labels;
    array<string, 2> inputs;
    string delimiter;
    string combinedResult;

public:
    InputHandler(const string& type, const InputMap& inputConfigs) {
        inputType = type;
        labels = inputConfigs.at(type).labels;
        delimiter = inputConfigs.at(type).delimiter;
    }

    // We use getline instead of cin >> to support multi-word input like "Los Angeles" or "Mary Jane"
    void getInput() {
        for (int i = 0; i < 2; i++) {
            cout << "Enter " << labels[i] << ": ";
            getline(cin, inputs[i]);
        }
    }

    void combineInputs() {
        combinedResult = inputs[0] + delimiter + inputs[1];
    }

    string printResult(int index) {
        cout << "Result " << index << " (" << inputType << "): " << combinedResult << endl;
        return combinedResult;
    }
};

int main() {
	// is there a cleaner way to structure this in c++?
	// After a lot of back and forth, this was the cleanest approach I could come up with
	// but i'm not confident it is the best implementation

    InputMap inputConfigs = {
        {"name", { {"first name", "last name"}, " " }},
        {"email", { {"username", "domain"}, "@" }},
        {"location", { {"city", "state abbreviation"}, ", " }}
    };

    vector<string> inputOptions;
    for (InputMap::iterator it = inputConfigs.begin(); it != inputConfigs.end(); ++it) {
        inputOptions.push_back(it->first);
    }

    cout << "Choose input type (name, email, location, or all): ";
    string choice;
    getline(cin, choice);

    if (choice == "all") {
        for (size_t i = 0; i < inputOptions.size(); ++i) {
            cout << "\n--- Input Type: " << inputOptions[i] << " ---" << endl;
            InputHandler handler(inputOptions[i], inputConfigs);
            handler.getInput();
            handler.combineInputs();
            handler.printResult(i + 1);
        }
    } else if (inputConfigs.find(choice) != inputConfigs.end()) {
        InputHandler handler(choice, inputConfigs);
        handler.getInput();
        handler.combineInputs();
        handler.printResult(1);
    } else {
        cout << "Invalid input type selected." << endl;
    }

    return 0;
}
