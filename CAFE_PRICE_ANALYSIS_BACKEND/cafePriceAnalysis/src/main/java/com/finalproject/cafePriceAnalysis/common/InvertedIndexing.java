package com.finalproject.cafePriceAnalysis.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InvertedIndexing {
	
	public void populateTrie(Trie trie, int id, String itemName, String fileId, int size, int fId, String key) {
		
		trie.insertItem(id, itemName, fileId, size, fId);
		
	}
	
	public String getFileName(Trie trie, String key) {
		return trie.getFileName(key);
	}
	
	public int getItemId(Trie trie, String key) {
		return trie.getItemId(key);
	}
	
	public static boolean searchTrie(Trie trie) {
		
		if(trie.search("egg")) {
			//trie.printTrie();
			System.out.println("TRUE>>>>>>>>>>>>");
		} else {
			System.out.println("False");
		}
		
		return trie.search("spicy");	
	}
	
//	public static void main(String args[]) {
//		Trie trie = new Trie();
//		
//		populateTrie(trie);
//		
//		//searchTrie(trie);
//	}
}
