package com.emergence.sortingbenchmark

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

private const val TAG = "MyCustomAdapter"

class MyCustomAdapter(context: Context, vm: MainActivityViewModel) : BaseAdapter() {

    private val mContext: Context = context
    private val mViewModel: MainActivityViewModel = vm

    override fun getCount(): Int {
        return mViewModel.allAlgorithms.size
    }

    override fun getItem(p0: Int): Any {
        return mViewModel.allAlgorithms[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val layoutInflater = LayoutInflater.from(mContext)
        val row = layoutInflater.inflate(R.layout.row_algorithm, p2, false)

        val nameTextView = row.findViewById<TextView>(R.id.algoName)
        nameTextView.text = mViewModel.allAlgorithms[p0].name

        val timer = row.findViewById<TextView>(R.id.timer)
        timer.text = "${mViewModel.allAlgorithms[p0].time} ms"

        return row
    }

}