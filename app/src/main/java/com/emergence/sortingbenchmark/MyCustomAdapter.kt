package com.emergence.sortingbenchmark

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

private const val TAG = "MyCustomAdapter"

class MyCustomAdapter(context: Context): BaseAdapter() {

    private val mContext: Context = context

    override fun getCount(): Int {
        return MainActivityViewModel().allAlgorithms.size
    }

    override fun getItem(p0: Int): Any {
        return MainActivityViewModel().allAlgorithms[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val layoutInflater = LayoutInflater.from(mContext)
        val row = layoutInflater.inflate(R.layout.row_algorithm, p2, false)

        val nameTextView = row.findViewById<TextView>(R.id.algoName)
        nameTextView.text = MainActivityViewModel().allAlgorithms[p0].name

        val timer = row.findViewById<TextView>(R.id.timer)
        timer.text = MainActivityViewModel().allAlgorithms[p0].time

        return row
    }

}