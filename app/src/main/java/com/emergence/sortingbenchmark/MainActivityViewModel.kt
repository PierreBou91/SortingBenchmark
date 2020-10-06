package com.emergence.sortingbenchmark

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emergence.sortingbenchmark.algorithms.bubbleSort
import com.emergence.sortingbenchmark.algorithms.mergeSort
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
    val hasSorted: LiveData<Boolean>
        get() = _hasSorted

    private val _algoCurrentlySorting = MutableLiveData<String>()
    val algoCurrentlySorting: LiveData<String>
        get() = _algoCurrentlySorting

    private val _currentTime = MutableLiveData<Float>()
    val currentTime: LiveData<Float>
        get() = _currentTime

    private val timer: CountDownTimer

    init {
        allAlgorithms = initializeAllAlgo()
        _hasSorted.value = true
        timer = object : CountDownTimer(120000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = ((120000 - millisUntilFinished) / 1000.0).toFloat()
            }
            override fun onFinish() {
            }
        }
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
                    withContext(Main) {
                        nextAlgo(v)
                    }
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
                    timer.cancel()
                }
            }
        }
    }

    private fun superSort(algo: String, arr: IntArray) {
        when (algo) {
            "Selection sort" -> selectionSort(arr)
            "Bubble sort" -> bubbleSort(arr)
            "Merge sort" -> mergeSort(arr)
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

    private fun nextAlgo(algo: String) {
        timer.cancel()
        timer.start()
        _algoCurrentlySorting.value = algo
    }
}