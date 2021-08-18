//Print all n bit binary strings (in order)

fun main() {
    binary(3)
    binary(2)
    binary(5)
}

private var s: IntArray = IntArray(0)

//Prints all binary string of length n
fun binary(n: Int) {
    s = IntArray(n)
    recurse(n)
    println()
}

private fun recurse(n: Int) {
    if(n == 0)
        print("${s.contentToString().replace(Regex("[ \\]\\[,]"), "")}, ")
    else {
        s[s.size - n] = 0
        recurse(n - 1)
        s[s.size - n] = 1
        recurse(n - 1)
    }
}
