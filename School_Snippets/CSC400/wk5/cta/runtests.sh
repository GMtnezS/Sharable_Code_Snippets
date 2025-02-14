# Step 1: Ensure JUnit JAR exists
if [ ! -f "junit-4.13.2.jar" ]; then
    echo "JUnit JAR file not found! Download it from Maven."
    exit 1
fi

# Step 2: Compile Java Files
echo "Compiling Java files..."
javac -cp .:junit-4.13.2.jar:hamcrest-all-1.3.jar RadixSortNatural.java RadixSortNaturalTest.java

# Step 3: Verify Compilation
if [ $? -ne 0 ]; then
    echo "Compilation failed!"
    exit 1
fi
echo "Compilation successful!"

# Step 4: Run JUnit Tests
echo "Running tests..."
java -cp .:junit-4.13.2.jar:hamcrest-all-1.3.jar org.junit.runner.JUnitCore RadixSortNaturalTest
