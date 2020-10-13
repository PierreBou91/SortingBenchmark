package com.emergence.sortingbenchmark.algorithms

fun heapSort(arr: IntArray) {
    val size = arr.size

    for (i in size / 2 - 1 downTo 0) heapify(arr, size, i)

    for (i in size - 1 downTo 1) {
        val temp = arr[0]
        arr[0] = arr[i]
        arr[i] = temp
        heapify(arr, i, 0)
    }
}

fun heapify(arr: IntArray, size: Int, i: Int) {
    var largest = i
    val l = 2 * i + 1
    val r = 2 * i + 2

    if (l < size && arr[l] > arr[largest]) largest = l
    if (r < size && arr[r] > arr[largest]) largest = r

    if (largest != i) {
        val temp = arr[i]
        arr[i] = arr[largest]
        arr[largest] = temp

        heapify(arr, size, largest)
    }
}