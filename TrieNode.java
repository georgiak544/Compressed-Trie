
package ce326.hw1;

class TrieNode{
    
    String prefix;
    TrieNode [] children;
    boolean isTerminal;
    TrieNode parent;
    
    
    public TrieNode(String prefix, boolean isTerminal, TrieNode parent){
        this.prefix = prefix;
        this.parent = parent;
        this.isTerminal = isTerminal;
        children = new TrieNode[26];
 
    }
}
