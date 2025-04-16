#!/bin/bash

# Step 1: Ensure JUnit and Hamcrest JAR files exist in the lib folder
if [ ! -f "lib/junit-4.13.2.jar" ] || [ ! -f "lib/hamcrest-all-1.3.jar" ]; then
    echo "JUnit or Hamcrest JAR file not found! Ensure they are in the lib/ directory."
    exit 1
fi

# Step 2: Ensure tmp directory exists for compiled files
mkdir -p tmp

# Step 3: Compile Java Files, outputting class files to tmp/
echo "Compiling Java files..."
javac -d tmp -cp .:lib/junit-4.13.2.jar:lib/hamcrest-all-1.3.jar \
    classes/*.java \
    ShapeTest.java

# Step 4: Verify Compilation Success
if [ $? -ne 0 ]; then
    echo "Compilation failed!"
    exit 1
fi
echo "Compilation successful!"

# Step 5: Run JUnit Tests using the Fully Qualified Class Name
echo "Running tests..."
java -cp tmp:lib/junit-4.13.2.jar:lib/hamcrest-all-1.3.jar org.junit.runner.JUnitCore ShapeTest
