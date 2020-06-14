class WordDictionary {
    TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root=new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode curr=root;
        for(char c:word.toCharArray()) {
            if(curr.children[c-'a']==null) {
                curr.children[c-'a']=new TrieNode();
            }
            curr=curr.children[c-'a'];
        }
        
        curr.leaf=true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return find(word,0,root);
    }
    
    public boolean find(String word,int index,TrieNode node) {
        if(index==word.length()) {
            return node.leaf;
        }
        
        char curr=word.charAt(index);
        if(curr=='.') {
            for(int i=0;i<26;i++) {
                if(node.children[i]!=null) {
                    boolean child=find(word,index+1,node.children[i]);
                    if(child)
                        return true;
                }
                
               
            }
             return false;
        } else {
            if(node.children[curr-'a']==null)
                return false;
            else 
                return find(word,index+1,node.children[curr-'a']);
        }
        
      
    }
}

class TrieNode{
    boolean leaf;
    TrieNode[] children;
    TrieNode() {
        leaf=false;
        children=new TrieNode[26];
        Arrays.fill(children,null);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
