package com.emergence.sortingbenchmark.algorithms

fun bubbleSort(A: IntArray) {
    val size = A.size
    for (i in 0 until size) {
        var swapped = false
        for (j in 0 until size - i - 1) {
            if (A[j] > A[j+1]) {
                val temp = A[j]
                A[j] = A[j+1]
                A[j+1] = temp
                swapped = true
            }
        }
        if (!swapped) break
    }
}