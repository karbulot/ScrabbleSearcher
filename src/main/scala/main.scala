import scala.io.Source

object main{
    def main(args: Array[String]):Unit = {
        val dictionary = Source.fromFile("data/slowa.txt")
        for (line <- dictionary.getLines) Trie addWord line.toLowerCase
        dictionary.close()
        //Trie addWord "abcd"
        //Trie addWord "dfdf"
        //Trie addWord "fsawrwqer"
        //Trie addWord "abdd"
        println(Trie.checkWord("mineralny"))
        println(Trie.checkWord("sdfgsdg"))
        val letters: List[Char] = List('a','b','c','d','e','f','g','h')
        //println(Trie.getWordsByPrefix("abd"))
        println(Trie.root.getWordsContainingLetters(letters))
    }
}