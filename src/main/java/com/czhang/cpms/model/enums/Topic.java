package com.czhang.cpms.model.enums;

public enum Topic {
	Array("Array"),
	HashTable("Hash Table"), 
	LinkedList("Linked List"), 
	Math("Math"), 
	TwoPointer("Two Pointer"),
	String("String"),
	BinarySearch("Binary Search"), 
	DAndC("Divide and Conquer"), 
	Backtrack("Backtracking"), 
	DP("Dynamic Programming"), 
	Design("Design"),
    Trie("Trie"),
    Tree("Tree"), 
    Sort("Sort"), 
    DFS("Depth-first Search"),
    Stack("Stack"),
    UnionFind("Union Find"),
    Greedy("Greedy"),
    Queue("Queue"),
    BFS("Breath-first Search"),
    Heap("Heap"),
    Matrix("Matrix"),
    BitManipulation("Bit Manipulation"),
    Graph("Graph"),
    TopologicalSort("Topological Sort"), 
    BITree("Binary Indexed Tree"),
    SegmentTree("Segment Tree"),
    BST("Binary Search Tree"),
    Memorization("Memorization"),
    Minimax("Minimax"), 
    Recursion("Recursion"),
    ReservoirSampling("Reservoir Sampling");

    private final String name;

    Topic(String name) {
        this.name = name;
    }
    
    public String getTagName() {
        return this.name;
    }
}
