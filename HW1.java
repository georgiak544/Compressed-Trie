
package ce326.hw1;

import java.util.Scanner;

public class HW1 {
    
    public static boolean checkChars(String str){
        for(int i = 0; i < str.length(); i++){
            if(!(Character.isLetter(str.charAt(i)))){
                return false;
            }
        }
        return true;
    }
    public static void main(String args[]) {
        
        Trie trie = new Trie();
        String word = null;
        
        

        while (true) {
            System.out.println("?: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] words = input.split(" ");
            int numWords = words.length;
            if(numWords < 1  || (numWords > 1) && (!(checkChars(words[1])))){
                continue;
            }
            if(numWords > 1){
                word = words[1].toLowerCase();
            }
            
                if(words[0].equals("-i") && numWords == 2){
                    TrieNode ret = trie.searchWord(trie.root, word);
                    if(ret == null){
                        trie.insertWord(trie.root,word);
                        
                        System.out.printf("ADD %s OK\n",word);
                    }
                    else{
                        System.out.printf("ADD %s NOK\n",word);
                    }
                } 
                else if(words[0].equals("-r") && numWords == 2){
                        TrieNode ret = trie.searchWord(trie.root, word);
                        if(ret != null){
                            trie.deleteWord(trie.root,word);
                            System.out.printf("RMV %s OK\n",word);
                        }
                        else{
                            System.out.printf("RMV %s NOK\n",word);
                        }       
                }
                else if(words[0].equals("-f") && numWords == 2){
                        TrieNode ret = trie.searchWord(trie.root, word);
                        if(ret != null){
                            System.out.printf("FND %s OK\n",word);
                        }
                        else{
                            System.out.printf("FND %s NOK\n",word);
                        }                          
                }
                else if(words[0].equals("-p")){
                    System.out.printf("PreOrder: ");
                    trie.PreOrderPrint(trie.root);
                    System.out.printf("\n");
                }
                else if(words[0].equals("-d")){
                    System.out.printf("\n***** Dictionary *****\n");
                    trie.printDictionary(trie.root, "");
                    System.out.printf("\n");
                }
                else if(words[0].equals("-w") && numWords == 3){
                    int X = Integer.parseInt(words[2]);
                    System.out.printf("Distant words of %s (%d):\n",word,X);
                }
                else if(words[0].equals("-q") && numWords == 1){
                    System.out.printf("Bye bye!\n");
                    break;
                }
                
            
        }
    }
}
