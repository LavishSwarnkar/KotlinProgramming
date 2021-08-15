fun main() {
    print(findSubstring("barfoothefoobarman", arrayOf("foo","bar")))
    print(findSubstring("wordgoodgoodgoodbestword", arrayOf("word","good","best","word")))
    print(findSubstring("barfoofoobarthefoobarman", arrayOf("bar","foo","the")))
    print(findSubstring("wordgoodgoodgoodbestword", arrayOf("word","good","best","good")))
    print(findSubstring("lingmindraboofooowingdingbarrwingmonkeypoundcake", arrayOf("fooo","barr","wing","ding","wing")))
}

//My solution
private fun findSubstring(s: String, words: Array<String>): List<Int> {
    //Data
    val res = mutableSetOf<Int>()
    val noOfWords = words.size
    val lenOfWord = words[0].length

    //Create hashmap
    val map = mutableMapOf<String, Int>()
    for(word in words)
        map[word] = if(map[word] == null) 1 else map[word]!!.plus(1)

    //Sliding window
    for(i in 0..s.length){
        var count = 0
        val mapCopy = map.toMutableMap()

        //Dividing into words of length lenOfWord
        for(j in i..(s.length - lenOfWord) step lenOfWord) {
            val curr = s.substring(j, j + lenOfWord)

            //Consume currWord
            if (mapCopy[curr] != null) {
                mapCopy[curr] = mapCopy[curr]!!.minus(1)

                //One Required word found
                if (mapCopy[curr]!! >= 0)
                    count++
            }

            //Pop first word, slide the window
            val popStart = j - (noOfWords * lenOfWord)
            if (popStart >= 0) {
                val pop = s.substring(popStart, popStart + lenOfWord)

                //Available for consumption
                if (mapCopy[pop] != null) {
                    mapCopy[pop] = mapCopy[pop]!!.plus(1)

                    //Word no more included
                    if (mapCopy[pop]!! > 0)
                        count--
                }
            }

            //We're done
            if (count == noOfWords)
                res.add(popStart + lenOfWord)
        }
    }

    return res.toList()
}