package com.emergence.sortingbenchmark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        val adapter = MyCustomAdapter(this, viewModel)
        listView.adapter = adapter

        oneThousand.setOnClickListener {
            viewModel.createAnArray(1000)
            startBenchmark.text = "START BENCHMARK WITH ARRAY OF SIZE 1'000"
        }
        tenThousand.setOnClickListener {
            viewModel.createAnArray(10000)

            startBenchmark.text = "START BENCHMARK WITH ARRAY OF SIZE 10'000"
        }
        oneHundredThousand.setOnClickListener {
            viewModel.createAnArray(100000)
            startBenchmark.text = "START BENCHMARK WITH ARRAY OF SIZE 100'000"
        }
        oneMillion.setOnClickListener {
            viewModel.createAnArray(1000000)
            startBenchmark.text = "START BENCHMARK WITH ARRAY OF SIZE 1'000'000"
        }

        startBenchmark.setOnClickListener {
            viewModel.startBench()
            adapter.notifyDataSetChanged()
        }

        viewModel.arrayIsGenerated.observe(this, Observer { isGenerated ->
            if (!isGenerated) {
                oneThousand.isEnabled = false
                tenThousand.isEnabled = false
                oneHundredThousand.isEnabled = false
                oneMillion.isEnabled = false
                startBenchmark.isEnabled = false
            } else {
                oneThousand.isEnabled = true
                tenThousand.isEnabled = true
                oneHundredThousand.isEnabled = true
                oneMillion.isEnabled = true
                startBenchmark.isEnabled = true
            }
        })
    }
}