#!/bin/bash

javac -d tmp DialogueSample.java
javac -d tmp FrameSample.java     
javac -d tmp WindowSample.java

java -cp tmp DialogueSample 
java -cp tmp FrameSample   
java -cp tmp WindowSample
