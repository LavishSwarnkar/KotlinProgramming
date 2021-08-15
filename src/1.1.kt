fun main() {
    //Words of variable length in between
    print(findSubstring("barfoothefoobarman", arrayOf("foo","bar")))
    print(findSubstring("wordgoodgoodgoodbestword", arrayOf("word","good","best","word")))
    print(findSubstring("barfoofoobarthefoobarman", arrayOf("bar","foo","the")))
    print(findSubstring("lingmindraboofooowingdingbarrwingmonkeypoundcake", arrayOf("fooo","barr","wing","ding","wing")))
}

//Best Kotlin code on Leetcode
private fun findSubstring(s: String, words: Array<String>): List<Int> {
    if (s.isBlank() || words.isEmpty() || words[0].isBlank()) return emptyList()

    val n = s.length
    val lenOfWord = words[0].length
    val noOfWords = words.size
    val wordsCount = words.groupingBy{ it }.eachCount() // count each word
    val result = mutableListOf<Int>()

    for (start in 0 until lenOfWord) { // try all different start positions
        val accWordsCount = mutableMapOf<String, Int>() // accumulating words count
        val maxWord = (n - start) / lenOfWord
        var startWord = 0

        for (endWord in 0 until maxWord) {
            // add endWord to the accWordsCount
            val sub = s.substring(start + lenOfWord * endWord, start + lenOfWord * (endWord + 1))
            accWordsCount[sub] = accWordsCount.getOrDefault(sub, 0) + 1

            // maintain condition accWordsCount[key] <= wordsCount[key] for every key
            while (accWordsCount[sub]!! > wordsCount.getOrDefault(sub, 0)) {
                // remove startWord and move the window
                val startSub = s.substring(start + lenOfWord * startWord, start + lenOfWord * (startWord + 1))
                accWordsCount[startSub] = accWordsCount[startSub]!! - 1
                startWord++
            }

            // current word count == w && accWordsCount[key] <= wordsCount[key] ==> accWordsCount == wordsCount
            if (endWord - startWord + 1 == noOfWords) result.add(start + lenOfWord * startWord)
        }
    }
    return result
}