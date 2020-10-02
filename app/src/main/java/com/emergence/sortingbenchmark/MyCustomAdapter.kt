package com.emergence.sortingbenchmark

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MyCustomAdapter(context: Context): BaseAdapter() {

    private val mContext: Context = context

    val names = MainActivityViewModel().allAlgorithms

    override fun getCount(): Int {
        return names.size
    }

    override fun getItem(p0: Int): Any {
        return names[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val layoutInflater = LayoutInflater.from(mContext)
        val row = layoutInflater.inflate(R.layout.row_algorithm, p2, false)

        val nameTextView = row.findViewById<TextView>(R.id.algoName)
        nameTextView.text = names[p0].name

        val timer = row.findViewById<TextView>(R.id.timer)
        timer.text = names[p0].time

        return row
    }

}