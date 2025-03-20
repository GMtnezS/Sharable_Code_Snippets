#!/bin/bash

echo "Compiling Java files..."
javac -d . classes/*.java Discussion.java

if [ $? -eq 0 ]; then
    echo "Compilation successful. Running Discussion.java..."
    echo " "
    java discussion.Discussion
else
    echo "Compilation failed."
fi
