# data-structures
Linear Data Structures
Final Project
December 2017
# Prompt:
Create a program that will read a text file, and place each token(or word) into a given data structure. 
Data structures are to be written completely by the student.

The data structures are as follows:
-Unsorted Linked List
-Sorted Linked List
-Modified Sorted Linked List
-Front Self-Adjusting Linked List (places each added item at the front of the linked list)
-Single Self-Adjusting Linked List (moves each added item up a single space in the linked list)
-Skip Lists
-Hash Table 1()
-Hash Table 2()
-Hash Table 3()
-Binary Tree

Record the time it takes to place each file into the data structure as well as the number of comparisons,
and the number of reference changes. Also remove each word from the filein the order that they were entered.
Record data for remove as well. These numbers will be used when analyzing the data.

Write a final report analyzing all the data. 

# Details
The text file to be parsed is specified within the code of 'HamletSorter.java'. 

A queue object (custom queue object written by the student) will hold each token in a first in first out 
order, while maintaining each object within the queue to allow the queue to be refreshed and used again to 
fill another data structure without reparsing the text file.

A method is then called within main for each data structure and data on each structure is diplayed. 

The following is an example of the output of a given text file:

Hamlet-Scene-1.txt.new
Unsorted: 
Words: UniqueWords: ReferenceChanges: Comparisons: Runtime: 
1413 561 356351 354668 0.011 secs
Remove:
Words: UniqueWords: ReferenceChanges: Comparisons: Runtime: 
0 0 794095 398180 0.008 secs 

Sorted: 
Words: UniqueWords: ReferenceChanges: Comparisons: Runtime: 
1413 561 452378 226330 0.015 secs
Remove:
Words: UniqueWords: ReferenceChanges: Comparisons: Runtime: 
0 0 484441 243353 0.004 secs 

Each piece of data given is separated by a space. The information the data is representing is given above,
also separated by a space. This was done to make it easier to place data into an excel spreadsheet.
