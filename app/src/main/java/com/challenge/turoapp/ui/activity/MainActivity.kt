package com.challenge.turoapp.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.challenge.turoapp.R
import com.challenge.turoapp.repository.Repository
import com.challenge.turoapp.repository.remote.model.Result
import com.challenge.turoapp.ui.adapter.BusinessAdapter
import com.challenge.turoapp.viewmodel.TuroViewModel
import com.challenge.turoapp.viewmodel.TuroViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject


class MainActivity : AppCompatActivity(), Observer<Result> {

    @Inject
    lateinit var repository: Repository

    private lateinit var businessAdapter: BusinessAdapter
    private lateinit var viewModel: TuroViewModel
    private lateinit var viewModelFactory: TuroViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)

        val searchButton = findViewById<Button>(R.id.search)
        val recyclerView = findViewById<RecyclerView>(R.id.business_list)
        businessAdapter = BusinessAdapter()

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context,
                layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.adapter = businessAdapter

        viewModelFactory = TuroViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(TuroViewModel::class.java)

        searchButton.setOnClickListener {
            viewModel.fetchPizzaBeerBusiness().observe(this, this)
        }
    }

    override fun onChanged(result: Result?) {
        result?.let {
            when (it) {
                is Result.Success -> {
                    val responseList = it.businessList
                    if (responseList.isNullOrEmpty()) {
                        Toast.makeText(applicationContext, "No results for Beer or Pizza", Toast.LENGTH_LONG).show()
                    } else {
                        businessAdapter.setData(responseList)
                        businessAdapter.notifyDataSetChanged()
                    }
                }

                is Result.Error -> {
                    Toast.makeText(applicationContext, it.exception.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}