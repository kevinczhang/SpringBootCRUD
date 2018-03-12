package com.czhang.cpms.util;

import com.czhang.cpms.model.enums.Company;
import com.czhang.cpms.model.enums.DifficultyLevel;
import com.czhang.cpms.model.enums.ProblemType;
import com.czhang.cpms.model.enums.Source;
import com.czhang.cpms.model.enums.Topic;

public class Constants {
	public static final Topic[] topics = {Topic.Array, Topic.HashTable, Topic.LinkedList, Topic.Math, 
			Topic.TwoPointer, Topic.String, Topic.BinarySearch, Topic.DAndC, Topic.Backtrack, Topic.DP, 
			Topic.Design, Topic.Trie, Topic.Tree, Topic.Sort, Topic.DFS, Topic.Stack, Topic.UnionFind, 
			Topic.Greedy, Topic.Queue,	Topic.BFS, Topic.Heap, Topic.Matrix, Topic.BitManipulation, 
			Topic.Graph, Topic.TopologicalSort, Topic.BITree, Topic.SegmentTree, Topic.BST, 
			Topic.Memorization, Topic.Minimax, Topic.Recursion, Topic.ReservoirSampling};
	
	public static final Source[] sources = {Source.LeetCode};
	
	public static final ProblemType[] types = {ProblemType.Algorithm, ProblemType.Database, 
			ProblemType.OODesign, ProblemType.SystemDesign};
	
	public static final DifficultyLevel[] levels = {DifficultyLevel.Easy, 
			DifficultyLevel.Medium, DifficultyLevel.Hard};
	
	public static final Company[] companies = {Company.Facebook, Company.Amazon, Company.Bloomberg, 
			Company.Google, Company.Microsoft, Company.Yelp};
}
