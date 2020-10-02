package com.emergence.sortingbenchmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emergence.sortingbenchmark.model.Algorithm
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class MainActivityViewModel : ViewModel() {

    init {

    }

    var arr: IntArray = intArrayOf(1,2,3)
    val timer: Long = 0

    private val names = listOf(
        "Selection sort",
        "Bubble sort",
        "Merge sort",
        "Quick Sort"
    )

    var allAlgorithms: MutableList<Algorithm> = initializeAllAlgo()

    private fun initializeAllAlgo(): MutableList<Algorithm> {
        val tempAlgoList = mutableListOf<Algorithm>()
        for (i in names) {
            tempAlgoList.add(Algorithm(i))
        }
        return tempAlgoList
    }

    private var _arrayIsGenerated = MutableLiveData<Boolean>(true)
    val arrayIsGenerated: LiveData<Boolean>
        get() = _arrayIsGenerated

    fun createAnArray(size: Int): IntArray {
        _arrayIsGenerated.value = false
        arr = IntArray(size) { Random.nextInt() }
        _arrayIsGenerated.value = true
        return arr
    }

    private var _executionTime = MutableLiveData<Long>(timer)
    val executionTime: LiveData<Long>
        get() = _executionTime

    private var _index = MutableLiveData(0)
    val index: LiveData<Int>
        get() = _index

    fun startBench() {
        for ((i,v) in names.withIndex()) {
            _executionTime.value = measureTimeMillis {
                arr.sort()
            }
            _index.value = i
        }
    }

}



//    enum class AlgorithmsNames {
//        SELECTION,
//        BUBBLE,
//        BUBBLE_RECURSIVE,
//        INSERTION,
//        INSERTION_RECURSIVE,
//        INSERTION_BINARY,
//        MERGE,
//        MERGE_ITERATIVE,
//        QUICK,
//        QUICK_ITERATIVE,
//        HEAP,
//        COUNTING,
//        RADIX,
//        BUCKET,
//        SHELL,
//        TIM,
//        COMB,
//        PIGEONHOLE,
//        CYCLE,
//        COCKTAIL,
//        STRAND,
//        BITONIC,
//        PANCAKE,
//        PERMUTATION,
//        GNOME
//    }

//private val names = listOf(
//    "Selection sort",
//    "Bubble sort",
//    "Recursive Bubble sort",
//    "Insertion sort",
//    "Recursive Insertion sort",
//    "Binary Insertion sort",
//    "Merge sort",
//    "Iterative Merge sort",
//    "Quick Sort",
//    "Iterative Quick Sort",
//    "Heap sort",
//    "Counting sort",
//    "Radix sort",
//    "Bucket sort",
//    "Shell sort",
//    "Tim sort",
//    "Comb sort",
//    "Pigeonhole sort",
//    "Cycle sort",
//    "Cocktail sort",
//    "Strand sort",
//    "Bitonic sort",
//    "Pancake sort",
//    "Permutation sort",
//    "Gnome sort"
//)