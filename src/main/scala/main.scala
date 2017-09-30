object main{
    def main(args: Array[String]):Unit = {
        //Trie.clear
        Trie addWord "a"
        Trie addWord "b"
        Trie addWord "ba"
        Trie addWord "ab"
        Trie addWord "ac"
        Trie addWord "abc"
        Trie addWord "abd"
        Trie addWord "abcd"
        Trie addWord "acb"
        /*
        Trie addWord "duma"
        Trie addWord "c"
        */
        println(Trie.getWordsByPrefix("b"))
    }
}