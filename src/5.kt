//Print all n bit k-ary strings (in order)

fun main() {
    kAry(3, 3)
    kAry(2, 3)
    kAry(2, 4)
}

private var s: IntArray = IntArray(0)

//Prints all binary string of length n
fun kAry(n: Int, k: Int) {
    s = IntArray(n)
    recurse(n, k)
    println()
}

private fun recurse(n: Int, k: Int) {
    if(n == 0)
        print("${s.contentToString().replace(Regex("[ \\]\\[,]"), "")}, ")
    else {
        for(j in 0 until k){
            s[s.size - n] = j
            recurse(n-1, k)
        }
    }
}