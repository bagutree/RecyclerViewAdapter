package com.julive.adapter_demo.binding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.julive.adapter.core.ListAdapter
import com.julive.adapter.core.into
import com.julive.adapter_demo.R
import com.julive.adapter_demo.createBindingViewModelList
import kotlinx.android.synthetic.main.activity_data_binding.*

class DataBindingActivity : AppCompatActivity() {

    private val mArrayListAdapter by lazy {
        ListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_binding)
        supportActionBar?.title = "ListAdapter"
        mArrayListAdapter.into(rv_binding_list)
        mArrayListAdapter.addAll(createBindingViewModelList(20))
    }

}