package com.suitmedia.competencytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.suitmedia.competencytest.adapter.ApiAdapter
import com.suitmedia.competencytest.adapter.LoadingStateAdapter
import com.suitmedia.competencytest.databinding.ActivityThirdBinding
import com.suitmedia.competencytest.utils.ViewModelFactory

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    private val viewModel: ThirdViewModel by viewModels {
        ViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.listItem.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.listItem.addItemDecoration(itemDecoration)

        getUsersWithApi()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_back, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean  = when(item.itemId){
        R.id.menuBack -> {
            finish()
            true
        }
        else -> true
    }

    private fun getUsersWithApi() {
        val adapter = ApiAdapter()
        viewModel.responses.observe(this) { data ->
            adapter.submitData(lifecycle, data)
        }
        binding.listItem.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        adapter.refresh()
    }
}