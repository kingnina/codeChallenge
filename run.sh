#!/bin/bash -x

#Change directory to the /src
cd src
#Compile Java Files
javac wordCount.java
javac RunMedian.java

#Finally, execute my programs
java wordCount
java RunMedian
