package com.finalproject.cafePriceAnalysis.common;

class Nodes {
    private static final int CHAR_SIZE = 256;

    Nodes links[] = new Nodes[CHAR_SIZE];
    boolean eosflag = false;
    String fileName = null;
    int arr[];
    String prevFile = null;
    int count = 0;
    int id;

    public Nodes() {
        for (int i = 0; i < CHAR_SIZE; i++) {
            links[i] = null;
        }
    }

    boolean containsKey(char ch) {
        return links[ch] != null;
    }

    Nodes get(char ch) {
        return links[ch];
    }

    void put(char ch, Nodes node) {
        links[ch] = node;
    }

    void setEOS() {
        eosflag = true;
    }

    boolean isEOS() {
        return eosflag;
    }

    void printTrie(String nameOfItem) {
        if (isEOS()) {
            System.out.println(nameOfItem);
        }

        for (char ch = 0; ch < CHAR_SIZE; ch++) {
            if (containsKey(ch)) {
                get(ch).printTrie(nameOfItem + ch);
            }
        }
    }
}

public class Trie {
    private static Nodes root;

    // Initializing Trie
    public Trie() {
        root = new Nodes();
    }

    // Inserts String if it's not already present in the Trie
    public static void insertItem(int id, String nameOfItem, String fileName, int n, int index) {
        Nodes node = root;

        // Traversing through each character of the String and inserting in the Trie.
        for (int i = 0; i < nameOfItem.length(); i++) {
            char ch = nameOfItem.charAt(i);
            if(Character.isLetterOrDigit(ch) || ch == ' ') {
            	if (node.links[ch] == null) {
                    node.put(ch, new Nodes());
                }
                node = node.get(ch);
            } else {
            	break;
            }
        }

        // Storing frequency of occurrence of the string in an array.
        if (node.prevFile == null) {
            node.arr = new int[n];
            node.count = 1;
            node.arr[index] = node.count;
            node.prevFile = fileName;
        } else if (node.prevFile.equals(fileName)) {
            node.count++;
            node.arr[index] = node.count;
        } else {
            node.count = 1;
            node.arr[index] = node.count;
            node.prevFile = fileName;
        }

        String fName = getFileName(nameOfItem);

        if (fName != null) {
            fName = fName + ", " + fileName;
            node.fileName = fName;
        } else {
            node.fileName = fileName;
        }
        
        node.id = id;
        
        // Setting end of the String once the entire string has been traversed.
        node.setEOS();
    }

    // Searches for a String by checking if it's present in the Trie.
    public static boolean search(String nameOfItem) {
        Nodes node = root;

        for (int i = 0; i < nameOfItem.length(); i++) {
            char ch = nameOfItem.charAt(i);
            if (node.links[ch] == null) {
                return false;
            }
            node = node.get(ch);
        }

        return node.isEOS();
    }

    public static void printTrie() {
        Nodes node = root;

        node.printTrie("");
    }

    // Method to fetch the filename(s) where the search keyword is present.
    public static String getFileName(String nameOfItem) {
        Nodes node = root;

        for (int i = 0; i < nameOfItem.length(); i++) {
            char ch = nameOfItem.charAt(i);
            if(Character.isLetterOrDigit(ch) || ch == ' ') {
            	if (node.links[ch] == null) {
                    return null;
                }
                node = node.get(ch);
            } else {
            	break;
            }
        }

        return node.fileName;
    }
    
    public static int getItemId(String nameOfItem) {
        Nodes node = root;

        for (int i = 0; i < nameOfItem.length(); i++) {
            char ch = nameOfItem.charAt(i);
            if(Character.isLetterOrDigit(ch) || ch == ' ') {
            	if (node.links[ch] == null) {
                    return 0;
                }
                node = node.get(ch);
            } else {
            	break;
            }
        }

        return node.id;
    }

    // Method to fetch the Frequency of the search keyword.
    public static int[] getFreq(String nameOfItem) {
        Nodes node = root;

        for (int i = 0; i < nameOfItem.length(); i++) {
            char ch = nameOfItem.charAt(i);
            if(Character.isLetterOrDigit(ch) || ch == ' ') {
            	if (node.links[ch] == null) {
                    return null;
                }
                node = node.get(ch);
            } else {
            	break;
            }
        }

        return node.arr;
    }
}
