package trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RecreateText {
	Trie dict ; 
	public RecreateText() {
		dict = new Trie();
	}
	static Map<String, String> memoized;
	public static void main(String[] args) {
		String text = "thisisawesomes";
		Set<String> dictionary =  new HashSet<String>(Arrays.asList("this","is", "awe", "some", "awesome"));
		memoized = new HashMap<String, String>();
		System.out.println(SegmentString2(text, dictionary));
		System.out.println(wordBreak(text, dictionary));

	}

	public static boolean wordBreak(String s, Set<String> wordDict) {
	    int[] pos = new int[s.length()+1];
	 
	    Arrays.fill(pos, -1);
	 
	    pos[0]=0;
	 
	    for(int i=0; i<s.length(); i++){
	        if(pos[i]!=-1){
	            for(int j=i+1; j<=s.length(); j++){
	                String sub = s.substring(i, j);
	                if(wordDict.contains(sub)){
	                    pos[j]=i;
	                }
	            } 
	        }
	    }
	 
	    return pos[s.length()]!=-1;
	}
	static String SegmentString(String input, Set<String> dict) {
		  if (dict.contains(input)) return input;
		  int len = input.length();
		  for (int i = 1; i < len; i++) {
		    String prefix = input.substring(0, i);
		    if (dict.contains(prefix)) {
		      String suffix = input.substring(i, len);
		      String segSuffix = SegmentString(suffix, dict);
		      if (segSuffix != null) {
		        return prefix + " " + segSuffix;
		      }
		    }
		  }
		  return null;
		}
	
	static String SegmentString2(String input, Set<String> dict) {
		  if (dict.contains(input)) return input;
		  if (memoized.containsKey(input) ){
		    return memoized.get(input);
		  }
		  int len = input.length();
		  for (int i = 1; i < len; i++) {
		    String prefix = input.substring(0, i);
		    if (dict.contains(prefix)) {
		      String suffix = input.substring(i, len);
		      String segSuffix = SegmentString2(suffix, dict);
		      if (segSuffix != null) {
		        memoized.put(input, prefix + " " + segSuffix);
		        return prefix + " " + segSuffix;
		      }
		    }
		  }
		  memoized.put(input, null);
		  return null;
		  }
	public void buildDict(List<String> words){
		for(String s : words ){
			dict.insert(s);
		}
	}
}
