package com.emergence.sortingbenchmark.algorithms

fun mergeSort (A: IntArray) {
    sort(A)
}
fun sort(A: IntArray): IntArray {

    val size = A.size

    if(size < 2) return A

    val mid: Int = size/2
    val left = A.slice(0 until mid).toIntArray()
    val right = A.slice(mid until size).toIntArray()

    return merge(sort(left), sort(right))
}
fun merge(left: IntArray, right: IntArray): IntArray {
    var L = 0
    var R = 0
    val sizeLeft = left.size
    val sizeRight = right.size
    val A: MutableList<Int> = mutableListOf()

    while (L < sizeLeft && R < sizeRight) {
        if (left[L] <= right[R]) {
            A.add(left[L])
            L++
        } else {
            A.add(right[R])
            R++
        }
    }
    while (L < sizeLeft) {
        A.add(left[L])
        L++
    }
    while (R < sizeRight) {
        A.add(right[R])
        R++
    }
    return A.toIntArray()
}