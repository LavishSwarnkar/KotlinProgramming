fun main() {
    print(buddyStrings("abc", "acd"))
    print(buddyStrings("VALISH", "LAVISH"))
    print(buddyStrings("ab", "ba"))
    print(buddyStrings("ab", "ab"))
    print(buddyStrings("aa", "aa"))
    print(buddyStrings("aaaaaaabc", "aaaaaaacb"))
}

/**
 *


"aa"
"aa"

 */
fun buddyStrings(s: String, goal: String): Boolean {
    if(s.length != goal.length) return false

    //Mismatch characters
    var actual: Char? = null
    var expected: Char? = null

    //Iteration
    for(i in s.indices){
        if(s[i] != goal[i]){
            when {
                //First mismatch
                actual == null -> {
                    actual = s[i]
                    expected = goal[i]
                }

                //Second mismatch
                s[i] == expected && goal[i] == actual -> {
                    actual = '$'
                }

                //Other mismatch
                else -> {
                    return false
                }
            }
        }
    }

    //No mismatch found
    if(actual == null){
        val map = s.toCharArray().toList().groupingBy { it }.eachCount()
        for(i in map.values)
            if(i > 1) return true
    }

    return actual == '$'
}