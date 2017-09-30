object Trie {
    val ALPHABET_LENGTH: Int = 35
    var root: TrieNode = new TrieNode('\0')
    def addWord(word: String): Unit = root.addWord(word)
    def getWords: List[String] = root.getWords
    def getWordsByPrefix(prefix: String): List[String] = root.getWordsByPrefix(prefix)
    def checkWord(word: String): Boolean = root.checkWord(word)
    class TrieNode(character: Char) {
        private var parent: TrieNode = _
        var children: Array[TrieNode] = new Array[TrieNode](ALPHABET_LENGTH)
        private var isLeaf: Boolean = true
        private var isWord: Boolean = false

        def addWord(word: String): Unit = {
            isLeaf = false
            var charPos: Int = getLetterNumber(word.charAt(0))
            if (children(charPos) == null) {
                children(charPos) = new TrieNode(word.charAt(0))
                children(charPos).parent = this
            }
            if (word.length() > 1) {
                children(charPos).addWord(word.tail)
            }
            else {
                children(charPos).isWord = true
            }
        }

        def getWords: List[String] = {
            var ret = List[String]()
            if (isWord) ret ::= this.toString
            if (!isLeaf) children.filter(_ != null).foreach(ret :::= _.getWords)
            ret
        }

        def getWordsByPrefix(prefix: String): List[String] = if (prefix.isEmpty) getWords
            else children(getLetterNumber(prefix(0))).getWordsByPrefix(prefix.tail)

        override def toString: String = {
            if (parent == null) ""
            else parent + character.toString
        }

        def getWord: String = toString

        def checkWord(word: String): Boolean = ???
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
}