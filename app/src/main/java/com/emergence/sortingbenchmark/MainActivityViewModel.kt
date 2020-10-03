package com.emergence.sortingbenchmark

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emergence.sortingbenchmark.model.Algorithm
import kotlin.random.Random
import kotlin.system.measureTimeMillis

private const val TAG = "MainActivityViewModel"

class MainActivityViewModel : ViewModel() {

    init {

    }

    var arr: IntArray = intArrayOf(1,2,3)

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

    private var executionTime: Long = 0

    fun startBench() {
        for ((i,v) in names.withIndex()) {
            executionTime = measureTimeMillis {
                arr.sort()
            }
            allAlgorithms[i].time = executionTime.toString()
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