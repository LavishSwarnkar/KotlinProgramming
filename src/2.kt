import java.util.*

fun main() {
    //test(intArrayOf(1,0,2,0,3,4))
    test(intArrayOf(1,0,2,0,0,4))
    //test(intArrayOf(1,0,0))
}

fun test(arr: IntArray) {
    duplicateZeros(arr)
    println(arr.contentToString())
}

/**
 * [1,0,2,0,3,4]
 * [1,0,0,2,0,0]
 * noOfZeroes = 2
 * [1,0,2,0,3,4]
 * [1,0,0,2,0,0]
 *    .       -
 */

fun duplicateZeros(arr: IntArray): Unit {
    //Count #0
    var noOfZeroes = 0
    for(i in arr)
        if(i == 0) noOfZeroes++

    //Return if no 0s
    if(noOfZeroes == 0) return

    //Start copying from this index
    val start = arr.size - noOfZeroes - 1

    //Copy to this index in arr
    var ptr = arr.size - 1

    //Reverse iteration for in-place shifting
    for(i in start downTo  0){
        arr[ptr--] = arr[i]

        //Copy twice if 0
        if(arr[i] == 0)
            arr[ptr--] = 0
    }
}