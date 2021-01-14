package csci310.weatherplanner.weathersource;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class TrieNode{
    char character;     
    LinkedList<TrieNode> children; 
    TrieNode parent;
    boolean isEnd;

    public TrieNode(char c) {
    	character = c;
        children = new LinkedList<TrieNode>();
        isEnd = false;        
    }
    public TrieNode getChild(char c) {
        if (children != null)
        	for(int i=0; i<children.size(); i++) {
        		TrieNode child = children.get(i);
        		if(child.character == c) {
        			return child;
        		}
        	}
        return null;
    }
    
    protected List<String> getWords() {
       List<String> list = new ArrayList<String>();      
       if (isEnd) {
    	   list.add(toString());
       }
       
       if (children != null) {
	       for (int i=0; i< children.size(); i++) {
	          if (children.get(i) != null) {
	             list.addAll(children.get(i).getWords());
	          }
	       }
       }       
       return list; 
    }
    
	public String toString() {
		if (parent == null) {
		     return "";
		} else {
		     return parent.toString() + new String(new char[] {character});
		}
	}
}
class Trie {
    private TrieNode root;
 
    public Trie() {
        root = new TrieNode(' '); 
    }
 
    public void addWord(String word) {
        if (SearchForWord(word) == true) 
            return;    
        
        TrieNode current = root; 
        TrieNode temp ;
        char[] c_array = word.toCharArray();
        for(int i=0; i<c_array.length; i++) {
        	temp = current;
        	char c = c_array[i];
        	TrieNode child = current.getChild(c);
        	if(child != null) {
        		current = child;
        		child.parent = temp;
        	}
        	else {
        		current.children.add(new TrieNode(c));
        		current = current.getChild(c);
        		current.parent = temp;
        	}
        current.isEnd = true;
        }
    }
    
    public boolean SearchForWord(String word) {
        TrieNode current = root;    
        char[] c_array = word.toCharArray();
        for(int i=0; i<c_array.length; i++) {
        	char c = c_array[i];
        	if(current.getChild(c) == null) {
        		return false;
        	}
        	else {
        		current = current.getChild(c);
        	}
        }
        if (current.isEnd == true) {       
            return true;
        }
        return false;
    }
    
    public List<String> autocomplete(String prefix) {     
       TrieNode lastNode = root;
       for (int i = 0; i< prefix.length(); i++) {
	       lastNode = lastNode.getChild(prefix.charAt(i));	     
	       if (lastNode == null) 
	    	   return new ArrayList<String>();      
       }
       
       return lastNode.getWords();
    }
}  