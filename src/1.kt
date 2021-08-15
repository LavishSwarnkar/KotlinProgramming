fun main() {
    print(findSubstring("barfoothefoobarman", arrayOf("foo","bar")))
    print(findSubstring("wordgoodgoodgoodbestword", arrayOf("word","good","best","word")))
    print(findSubstring("barfoofoobarthefoobarman", arrayOf("bar","foo","the")))
}

fun findSubstring(s: String, words: Array<String>): List<Int> {
    //Data
    val res = mutableListOf<Int>()
    val noOfWords = words.size
    val lenOfWord = words[0].length

    //Create hashmap
    val map = mutableMapOf<String, Int>()
    for(word in words)
        map[word] = 1

    //Sliding window
    for(i in 0..s.length){
        var count = 0
        val mapCopy = map.toMutableMap()

        //Dividing into words of length lenOfWord
        for(j in i..(s.length - lenOfWord) step lenOfWord) {
            val curr = s.substring(j, j + lenOfWord)

            //Consume currWord
            mapCopy[curr] = mapCopy[curr] ?: 0.minus(1)

            if (mapCopy[curr]!! >= 0)
                count++

            //Pop first word, slide the window
            val popStart = j - (noOfWords * lenOfWord)
            if (popStart >= 0) {
                val pop = s.substring(popStart, popStart + lenOfWord)

                //Available for consumption
                mapCopy[pop] = mapCopy[pop] ?: 0.plus(1)

                if (mapCopy[pop]!! > 0)
                    count--
            }

            if (count == noOfWords)
                res.add(popStart + lenOfWord)
        }
    }

    return res
}