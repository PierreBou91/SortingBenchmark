package com.emergence.sortingbenchmark.algorithms

fun selectionSort(A: IntArray) {
    val size = A.size
    for (i in 0 until size) {
        var minimumIndex = i
        for (j in i+1 until size) {
            if (A[j] < A[minimumIndex]) minimumIndex = j
        }
        if (minimumIndex != i) {
            val temp = A[minimumIndex]
            A[minimumIndex] = A[i]
            A[i] = temp
        }
    }
}