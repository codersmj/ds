package trie;

import java.util.HashMap;
import java.util.Map;

public class Trie {
	TrieNode root;
	public static class TrieNode{
		Map<Character, TrieNode> characterMap;
		Character ch;
		boolean isLeaf;
		public TrieNode() {
			characterMap =  new HashMap<Character, Trie.TrieNode>();
		}
		public TrieNode(char c) {
			ch = c;
			characterMap =  new HashMap<Character, Trie.TrieNode>();
		}
	}
	
	public void insert(String word) {
		if(word == null || word.isEmpty()){
			return;
		}
		Map<Character ,TrieNode> currentCharMap = root.characterMap;

		for(int i = 0; i< word.length(); i++){
			char c = word.charAt(i);
			TrieNode t ;

			if(currentCharMap.get(c) !=null){
				t = currentCharMap.get(c);
			}else{
				t = new TrieNode(c);
				currentCharMap.put(c, t);
			}
			currentCharMap = t.characterMap;
			if(i == word.length()-1){
				t.isLeaf = true;
			}
		}
	}
	
	
	public boolean search(String word){
		TrieNode t = null;
		Map<Character ,TrieNode> currentCharMap = root.characterMap;

		for(int i = 0; i< word.length(); i++){
			char c = word.charAt(i);
			if(currentCharMap.get(c)!= null){
				t = currentCharMap.get(c);
				currentCharMap = t.characterMap;
			}else{
				t = null;
				break;
			}
		}
		if(t != null && t.isLeaf){
			return true; 
		}
		return false;
	}
	
	public boolean searchPrefix(String str){

		TrieNode t = null;
		Map<Character ,TrieNode> currentCharMap = root.characterMap;

		for(int i = 0; i< str.length(); i++){
			char c = str.charAt(i);
			if(currentCharMap.get(c)!= null){
				t = currentCharMap.get(c);
				currentCharMap = t.characterMap;
			}else{
				t = null;
				break;
			}
		}
		if(t != null){// do not check for leaf condition since only want as prefix.
			return true; 
		}
		return false;
	
	}
	
	
	
	public Trie() {
		root = new TrieNode();
	}
	
	public static void main(String[] args) {
		
	}
}
