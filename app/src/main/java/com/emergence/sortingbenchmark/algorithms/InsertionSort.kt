package com.emergence.sortingbenchmark.algorithms

fun insertionSort(arr: IntArray) {
    val size = arr.size
    for (i in 1 until size) {
        val key = arr[i]
        var j = i - 1

        while (j >= 0 && arr[j] > key) {
            arr[j+1] = arr[j]
            j -= 1
        }
        arr[j+1] = key
    }
}