
package ce326.hw1;

class Trie {
    TrieNode root;
    
    public Trie(){
        root = new TrieNode(" ",false, root);
    }
   
    public TrieNode searchWord(TrieNode node, String word){
        int pos = letterPosition(word);
        
        if(node.children[pos] == null){
            return node.children[pos];
        }
        else{
            int lenNode = node.children[pos].prefix.length();
            int lenWord = word.length();
          
            if(lenNode < lenWord){
                boolean equals = word.startsWith(node.children[pos].prefix);
                if(equals){
                    word = word.substring(lenNode);
                    return searchWord(node.children[pos], word);
                }
            }
            else if(lenNode == lenWord){
                boolean equals = node.children[pos].prefix.equals(word);
                if(equals && node.children[pos].isTerminal){
                    
                    return node.children[pos];
                }
            }
        }
        return null; 
    }
   
    public int letterPosition(String word){
        char letter = word.charAt(0);
        
        int position = letter - 'a';
        
        return(position);  
    }
    
    public TrieNode insertWord(TrieNode node, String word){
        
      int pos = letterPosition(word);
      int i;
        //new node
        if(node.children[pos] == null){
          node.children[pos] = new TrieNode(word,true,node);
        }
        else{
            int lenWord = word.length();
            int lenNode = node.children[pos].prefix.length();
            
            int minLen = Math.min(lenWord,lenNode);
          
            for(i = 0; i < minLen; i++){
                if(word.charAt(i) != node.children[pos].prefix.charAt(i)){
                    break;
                }
            }
            if(i < lenNode){ //break
                //Create the new elements
                String firstPart = node.children[pos].prefix.substring(0,i);
                String secondPart = node.children[pos].prefix.substring(i,lenNode);
                TrieNode[] newChildren = node.children[pos].children;
                TrieNode newNode = new TrieNode(secondPart,true,node.children[pos]);
                
                newNode.children = newChildren;
                
                node.children[pos].children = new TrieNode[26];
                node.children[pos].prefix = firstPart;
                
                int newPos = letterPosition(secondPart);
                
                node.children[pos].children[newPos] = newNode;
                
                if(i < lenWord){
                    String secondWord = word.substring(i,lenWord);
                    node.children[pos].isTerminal = false;
                    return insertWord(node.children[pos], secondWord);
                }
                return null;   
            }
            if(i == lenNode){
                if(lenWord == i){
                    node.children[pos].isTerminal = true;    
                }
                else{
                    String secondWord = word.substring(i,lenWord);
                    return insertWord(node.children[pos], secondWord);
                }
            }
            
        }
        return null;
    }
    
    
    public void PreOrderPrint(TrieNode node){
        
        if(node == null){
            return;
        }
        if(!" ".equals(node.prefix)){
            if(node.isTerminal){
                System.out.printf("%s# ",node.prefix);
            }
            else{
                System.out.printf("%s ",node.prefix);
            }
        }
        
            
        
        for(TrieNode child: node.children){
            PreOrderPrint(child);
        }
    }
    public void printDictionary(TrieNode node, String word){

        if(node  == null){
            return;
        }
        word = word + node.prefix;
        
        if(node.isTerminal){
            System.out.printf("%s\n",word);
        }
        
        for(TrieNode child: node.children){
            printDictionary(child, word);
        }    
    }
    
    public boolean deleteWord(TrieNode node, String word){
        int numChild;
        String secChild = null;
        int i = 0;
    
        TrieNode newNode = null;
        TrieNode[] newChildren;
       
        node = searchWord(node,word);
        if(node == null){
            return false;
        }
        int pos = letterPosition(node.prefix);
        
        numChild = countNodes(node);
      
        if(numChild > 0){
            
            if(numChild == 1){
                for(TrieNode child: node.children){
                    if(child != null){
                        secChild = child.prefix;
                        break;
                    }
                }
                pos = letterPosition(secChild);
                node.prefix = node.prefix + secChild;
                node.children[pos] = newNode;
                return true;
            }
             node.isTerminal = false;
             return true;
        }
        if(node.parent.isTerminal){
            node.parent.children[pos] = newNode;
            return true;
        }
        numChild = countNodes(node.parent);
        
        node.parent.children[pos] = newNode;
        
        if(numChild == 2){
            for (TrieNode child : node.parent.children) {
                i++;
                if(child != null){
                    break;
                }
            }   
            node.parent.prefix = node.parent.prefix + node.parent.children[i-1].prefix;
            newChildren = node.parent.children[i-1].children;
            node.parent.children = newChildren;
            node.parent.isTerminal = true;
 
        }
        return true;
         
    }
        
    public int countNodes(TrieNode node){
        int count = 0;
        if(node == null){
            return 0;
        }
        for (TrieNode child : node.children) {
            if(child != null){
                count++;
            }
        }
        return count;
    }
}

    
        
        
        
        
        
        

    
            

    
    