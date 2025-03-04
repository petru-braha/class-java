#!/bin/bash

g++ bubble_sort.cpp -o bubble_sort.out
javac bubble_sort.java
g++ quicks_sort.cpp -o quicks_sort.out
javac quicks_sort.java

for i in $(seq 0 29)
do
  printf "iteration number: %d\n" $i
  ./bubble_sort.out
  java bubble_sort
  ./quicks_sort.out
  java quicks_sort
  printf "\n"
done

rm *.class
rm *.out
