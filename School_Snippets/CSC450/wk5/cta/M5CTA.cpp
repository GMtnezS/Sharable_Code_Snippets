#include <iostream>
#include <fstream>
#include <string>
using namespace std;

/*
    This program lets users jot down lines of text that get added to a running log 
    stored in "CSC450_CT5_mod5.txt". Each time an entry is added, 
    the program reads the entire file and creates a reversed version of its contents, 
    storing it in "CSC450-mod5-reverse.txt".

    This setup helps demonstrate file I/O operations, string manipulation, and good 
    practices around separating logic using classes and methods. It ensures the reversed 
    file always mirrors the latest state of the original — no duplication, no data loss.

    The program keeps prompting for more input until the user decides to stop.
*/

class FileHandler {
private:
    string inputFile;
    string outputFile;

public:
    // Constructor to initialize file names
    FileHandler(string inputFileName, string outputFileName) {
        inputFile = inputFileName;
        outputFile = outputFileName;
    }

    // Reads the full content of the input file
    string readFile() {
        ifstream inFile(inputFile);
        if (!inFile) {
            cerr << "Error: Could not open " << inputFile << " for reading." << endl;
            exit(1);
        }

        string content, line;
        while (getline(inFile, line)) {
            content += line + "\n";
        }

        inFile.close();
        return content;
    }

    // Appends given text to the specified file
    void appendToFile(const string& filePath, const string& text) {
        ofstream outFile(filePath, ios::app);
        if (!outFile) {
            cerr << "Error: Could not open " << filePath << " for appending." << endl;
            exit(1);
        }

        outFile << text << endl;
        outFile.close();
    }

    // Reverses the input string and returns it
    string reverseContent(const string& text) {
        string reversedText = "";
        for (int i = text.length() - 1; i >= 0; --i) {
            reversedText += text[i];
        }
        return reversedText;
    }

    // logReversedContent was repeatedly appending full reversed content,
    // causing the reverse file to grow with duplicated mirrored blocks.
    // To ensure the reversed file remains a true mirror of the current input file,
    // we clear the reversed file before writing the newly reversed content.
    void clearFile(const string& filePath) {
        ofstream file(filePath, ios::out | ios::trunc);
        if (!file) {
            cerr << "Error: Could not clear file " << filePath << "." << endl;
            exit(1);
        }
        file.close();
    }

    // Reverses and appends the reversed string to output file
    void logReversedContent(const string& text) {
        string reversed = reverseContent(text);
        clearFile(outputFile);
        appendToFile(outputFile, reversed);
    }
};

int main() {
    const string inputFilename = "CSC450_CT5_mod5.txt";
    const string outputFilename = "CSC450-mod5-reverse.txt";

// Create file handler instance with filenames
    FileHandler handler(inputFilename, outputFilename);

    while (true) {
        // Prompt user for input
        cout << "Enter a line of text to append to the file: ";
        string inputLine;
        getline(cin, inputLine);

        // Append the user input to the input file
        handler.appendToFile(inputFilename, inputLine);

        // Read the full content of the input file
        string originalText = handler.readFile();

        // Reverse the content and write it to the output file
        handler.logReversedContent(originalText);

        // Inform user of action taken
        cout << "Input saved and reversed content written to " << outputFilename << "." << endl;

        // Ask user if they want to continue
        cout << "Do you want to add more input? (yes/no): ";
        string response;
        getline(cin, response);

        if (response != "yes") {
            break;
        }
    }


    return 0;
}
