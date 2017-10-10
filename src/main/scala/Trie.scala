object Trie {
    val ALPHABET_LENGTH: Int = 35
    var root: TrieNode = new TrieNode('\0')
    def addWord(word: String): Unit = root.addWord(word)
    def getWords: List[String] = root.getWords
    def getWordsByPrefix(prefix: String): List[String] = root.getWordsByPrefix(prefix)
    def checkWord(word: String): Boolean = root.checkWord(word)
    def findBestWord(letters: List[Char]):String = findBestWordWithPoints(letters)._1
    def findBestWordWithPoints(letters: List[Char]): Tuple2[String,Int] = root.getWordsContainingLettersPoints(letters).maxBy(_._2)


    //root.getWordsContainingLetters(letters).sortWith(getWordScore)
    class TrieNode(val character: Char) {
        private var parent: TrieNode = _
        var children: Array[TrieNode] = new Array[TrieNode](ALPHABET_LENGTH)
        private var isLeaf: Boolean = true
        private var isWord: Boolean = false

        def addWord(word: String): Unit = {
            isLeaf = false
            var charPos: Int = getLetterNumber(word(0))
            if (children(charPos) == null) {
                children(charPos) = new TrieNode(word(0))
                children(charPos).parent = this
            }
            if (word.length() > 1) children(charPos).addWord(word.tail)
            else children(charPos).isWord = true
        }

        def getWords: List[String] = {
            var ret = List[String]()
            if (isWord) ret ::= this.toString
            if (!isLeaf) children.filter(_ != null).foreach(ret :::= _.getWords)
            ret
        }

        def getWordsByPrefix(prefix: String): List[String] = if (prefix.isEmpty) getWords
            else if (children(getLetterNumber(prefix(0)))!=null) children(getLetterNumber(prefix(0))).getWordsByPrefix(prefix.tail)
            else List()

        def getWordsContainingLetters(letters: List[Char]): List[String] = {
            var ret = List[String]()
            if (isWord) ret ::= this.toString
            if (!isLeaf) children.filter(_ != null).filter(letters contains _.character).foreach(child => ret :::= child.getWordsContainingLetters(letters.filter(_ != child.character)))
            ret
        }

        def getWordsContainingLettersPoints(letters: List[Char]): List[Tuple2[String,Int]] = {
            var ret = List[Tuple2[String,Int]]()
            if (isWord) ret ::= (this.toString, this.getScore)
            if (!isLeaf) children.filter(_ != null).filter(letters contains _.character).foreach(child => ret :::= child.getWordsContainingLettersPoints(letters.filter(_ != child.character)))
            ret
        }

        override def toString: String = if (parent == null) "" else parent + character.toString

        def getWord: String = toString

        def checkWord(word: String): Boolean = {
            if (word.isEmpty) if (isWord) true else false
            else if (children(getLetterNumber(word(0)))!=null) children(getLetterNumber(word(0))).checkWord(word.tail)
            else false
        }

        def getScore: Int = getWordScore(this.toString)
    }

    def getLetterNumber(c: Char): Int = c match {
        case 'ą' => 26
        case 'ć' => 27
        case 'ę' => 28
        case 'ł' => 29
        case 'ń' => 30
        case 'ó' => 31
        case 'ś' => 32
        case 'ż' => 33
        case 'ź' => 34
        case _ => c - 'a'
    }

    def getWordScore(s: String): Int = s.map(getLetterScore).sum

    def getLetterScore(c: Char): Int = c match {
        case 'a' | 'e' | 'i' | 'n' | 'o' | 'r' | 's' | 'w' | 'z' => 1
        case 'c' | 'd' | 'k' | 'l' | 'm' | 'p' | 't' | 'y' => 2
        case 'b' | 'g' | 'h' | 'j' | 'ł' | 'u' => 3
        case 'ą' | 'ę' | 'f' | 'ó' | 'ś' | 'ż' => 5
        case 'ć' => 6
        case 'ń' => 7
        case 'ź' => 9
    }


}