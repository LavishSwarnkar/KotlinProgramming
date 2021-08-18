/* 2. Duplicate Zeroes
https://leetcode.com/problems/duplicate-zeros/ */

fun main() {
    test(intArrayOf(1,0,2,0,3,4))
    test(intArrayOf(1,0,2,0,0,4))
    test(intArrayOf(1,0,0))
    test(intArrayOf(8,4,5,0,0,0,0,7))
    test(intArrayOf(1,0,2,3,0,4,5,0))
}

fun test(arr: IntArray) {
    duplicateZeros(arr)
    println(arr.contentToString())
}

fun duplicateZeros(arr: IntArray): Unit {
    //Count #0
    var noOfZeroes = 0
    for(i in arr)
        if(i == 0) noOfZeroes++

    //Return if no 0s
    if(noOfZeroes == 0) return

    //Start copying from this index
    val end = arr.size - 1

    //Copy to this index in arr (may be out of bounds)
    var ptr = arr.size - 1 + noOfZeroes

    //Reverse iteration for in-place shifting
    for(i in end downTo  0){

        //Out of bounds check
        if(ptr > arr.size - 1){
            ptr--

            if(arr[i] == 0)
                if(ptr == arr.size - 1) arr[ptr--] = 0 else ptr--

            continue
        }

        //Copy
        arr[ptr--] = arr[i]

        //Copy twice if 0
        if(arr[i] == 0)
            arr[ptr--] = 0
    }
}