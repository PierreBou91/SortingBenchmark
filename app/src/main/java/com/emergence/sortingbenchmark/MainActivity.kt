package com.emergence.sortingbenchmark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
//import com.emergence.sortingbenchmark.databinding.ActivityMainBinding
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
            adapter.notifyDataSetChanged()
        }
        unsortedButton.setOnClickListener {
            viewModel.startBench(Integer.parseInt(arraySize.text.toString()), false)
            adapter.notifyDataSetChanged()
        }
    }
}