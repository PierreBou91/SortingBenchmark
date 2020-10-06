package com.emergence.sortingbenchmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emergence.sortingbenchmark.algorithms.bubbleSort
import com.emergence.sortingbenchmark.algorithms.selectionSort
import com.emergence.sortingbenchmark.model.Algorithm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random
import kotlin.system.measureTimeMillis

private const val TAG = "MainActivityViewModel"

class MainActivityViewModel : ViewModel() {

    private val names: List<String> = listOf(
        "Selection sort",
        "Bubble sort",
        "Merge sort",
        "Quick Sort"
    )

    var allAlgorithms: MutableList<Algorithm>

    private val _hasSorted = MutableLiveData<Boolean>()
    val hasSorted : LiveData<Boolean>
        get() = _hasSorted


    init {
        allAlgorithms = initializeAllAlgo()
        _hasSorted.value = true
    }

    private fun initializeAllAlgo(): MutableList<Algorithm> {
        val tempAlgoList = mutableListOf<Algorithm>()
        for (i in names) {
            tempAlgoList.add(Algorithm(i))
        }
        return tempAlgoList
    }

    fun startBench(size: Int, isSorted: Boolean) {
        if (_hasSorted.value == true) {
            _hasSorted.value = false
            viewModelScope.launch(Dispatchers.Default) {
                var executionTime: Long = 0
                val arr = createAnArray(size, isSorted)
                for ((i,v) in names.withIndex()) {
                    val tempArr = IntArray(arr.size)
                    for ((index,value) in arr.withIndex()) {
                        tempArr[index] = value
                    }
                    executionTime = measureTimeMillis {
                        superSort(v,tempArr)
                    }
                    allAlgorithms[i].time = executionTime.toString()
                }
                withContext(Main) {
                    _hasSorted.value = true
                }
            }
        }
    }

    private fun superSort(algo: String, arr: IntArray) {
        when (algo) {
            "Selection sort" -> selectionSort(arr)
            "Bubble sort" -> bubbleSort(arr)
            "Merge sort" -> arr.sort()
        }
    }

    private fun createAnArray(size: Int, isSorted: Boolean): IntArray {
        var arr = IntArray(size)
        if (isSorted) {
            for (i in 0 until size) {
                arr[i] = i
            }
        } else {
            arr = IntArray(size) { Random.nextInt()}
        }
        return arr
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