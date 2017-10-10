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
        val letters: List[Char] = List('a','ż','c','d','ó','f','ń','h')
        println(Trie.findBestWordWithPoints(letters))
    }
}