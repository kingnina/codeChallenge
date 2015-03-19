#!/bin/bash -x

# change to the directory of source code
cd ./src

#Compile Java Files
javac wordCount.java
javac RunMedian.java

#Finally, execute my programs
java wordCount
java RunMedian
