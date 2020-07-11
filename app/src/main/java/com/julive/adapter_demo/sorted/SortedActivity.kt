package com.julive.adapter_demo.sorted

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.julive.adapter.core.bindListAdapter
import com.julive.adapter.flex.flexboxLayoutMangerDefault
import com.julive.adapter.sorted.SortedListAdapter
import com.julive.adapter_demo.R
import kotlinx.android.synthetic.main.activity_array_list.*
import kotlinx.android.synthetic.main.include_button_bottom.*
import kotlin.random.Random

class SortedActivity : AppCompatActivity() {

    private val mSortedListAdapter by lazy {
        SortedListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "SortedListAdapter"
        setContentView(R.layout.activity_array_list)
        rv_list.bindListAdapter(mSortedListAdapter)
        (0..10).map {
            mSortedListAdapter.add(SortedItemViewModelTest().apply {
                model = SortedModelTest(it, "标题$it", "副标题$it")
            })
        }
        var index = 100
        new_add.setText("新增").setOnClickListener {
            // 新增的时候 只有sortId 与 uniqueId 都一样才会替换
            val randomInt =
                Random.nextInt(0, if (mSortedListAdapter.size > 0) mSortedListAdapter.size else 10)
            mSortedListAdapter.add(SortedItemViewModelTest().apply {
                model = SortedModelTest(randomInt, "标题$randomInt", "sortId与uniqueId都一样，触发替换")
            })
            // 要想根据uniqueId更新数据，需要调用updateItem方法
            mSortedListAdapter.add(SortedItemViewModelTest().apply {
                model = SortedModelTest(index++, "标题$randomInt", "sortId不一样 uniqueId一样")
            })
        }
        delete.setText("删除").setOnClickListener {
            if (mSortedListAdapter.size > 0) {
                val randomInt = Random.nextInt(0, mSortedListAdapter.size)
                mSortedListAdapter.removeItemAt(randomInt)
            }
        }
        update.setText("替换").setOnClickListener {
            // 根据uniqueId替换 如果sortId不一样就会触发排序
            if(mSortedListAdapter.size>0){
                val randomInt = Random.nextInt(0, mSortedListAdapter.size)
                mSortedListAdapter.updateItem(randomInt, SortedItemViewModelTest().apply {
                    model = SortedModelTest(randomInt, "标题$randomInt", "根据uniqueId替换标题${index++}")
                })
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.action_ll -> {
                rv_list.layoutManager = LinearLayoutManager(this)
                true
            }
            R.id.action_flex -> {
                rv_list.layoutManager = flexboxLayoutMangerDefault { }
                true
            }
            R.id.action_grid -> {
                rv_list.layoutManager = GridLayoutManager(this, 2)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}