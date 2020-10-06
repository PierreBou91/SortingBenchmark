package com.emergence.sortingbenchmark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        val adapter = MyCustomAdapter(this, viewModel)

        listView.adapter = adapter

        sortedButton.setOnClickListener {
            viewModel.startBench(Integer.parseInt(arraySize.text.toString()), true)
        }
        randomButton.setOnClickListener {
            viewModel.startBench(Integer.parseInt(arraySize.text.toString()), false)
        }
        viewModel.hasSorted.observe(this, Observer { hasSorted ->
            if (hasSorted) {
                toggleVisibility(true)
                adapter.notifyDataSetChanged()
            }else {
                toggleVisibility(false)
            }
        })

    }

    fun toggleVisibility(isVisible: Boolean) {
        if (!isVisible) {
            arraySize.visibility = View.INVISIBLE
            sortedButton.visibility = View.INVISIBLE
            randomButton.visibility = View.INVISIBLE
        } else {
            arraySize.visibility = View.VISIBLE
            sortedButton.visibility = View.VISIBLE
            randomButton.visibility = View.VISIBLE
        }
    }

}