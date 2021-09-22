# Algorithm practice

A project for practice algorithm.

## Project structure

* /gist: intellij type code segment
* /index: for looking up my past code and sort them in a searchable manner
* /interactive: for solving google code jam
* /java: solution written in java
* /python: solution written in python

## Coding practice

Most of the code will be written in Java.

* UVA - Competitive Programming 3
* Leetcode
* Google code jam
* Facebook hackercup
* Code force

## Header

There is an indexer for this project at `/java/src/main/java/Indexing/Indexing.java`.

This is an example of a header. Here is the minimum information needed.
Only block comments are read.

```java
/**
problem: https://leetcode.com/problems/add-and-search-word-data-structure-design/
level: medium
solution: a trie. can search normal and regular expression

#back_tracking #dfs #trie #leetcode

 **/
```

As there can have multiple implementation for a solution, it is not restricted to have one header, but also have comments for each section
Should try to group related thing together, because the indexer also link relationship between tags if they appear together.

```java
/**
You can write anything here, but the indexer only index whatever start with hashtag

#back_tracking #time_O(n^2) #space_O(n)
**/
```

for the tag, underscore is automatically removed.

## Tag available

* site

	* uva
	* leetcode
	
* algorithm and data structure

	* bfs
	* interval tree
	
* complexity

	* time_O(n)
	* space_O(n^2)
	
* difficulty

	* easy
	* medium
	* 35pt
	* 
	
* others
	
	* reference: if an implementation is good to use with ease.
	
* skip: if the implementation is not correct