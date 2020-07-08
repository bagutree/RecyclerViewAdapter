package com.julive.adapter_demo.dsl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.julive.adapter.core.arrayItemViewModelDsl
import com.julive.adapter.core.arrayListAdapter
import com.julive.adapter_demo.ModelTest
import com.julive.adapter_demo.R
import kotlinx.android.synthetic.main.activity_adapter_dsl.*

class AdapterDslActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adapter_dsl)

        arrayListAdapter {

            (0..10).map {
                add(

                    arrayItemViewModelDsl<ModelTest> {

                        layoutId = if (it % 2 == 0) R.layout.item_test else R.layout.item_test_2

                        model = ModelTest("title$it", "subTitle$it")

                        onBindViewHolder { viewHolder ->
                            viewHolder.getView<TextView>(R.id.tv_title)?.text = model.title
                            viewHolder.getView<TextView>(R.id.tv_subTitle)?.text = model.subTitle
                        }

                        onItemClick { vm, vh ->

                            //这里需要注意，为什么直接从该对象获取的Model是不正确的？因为ViewHolder的复用
                            //导致click事件其实是在另外一个VM里触发的
                            Log.d("arrayItemViewModel", "不正确的model${model}")
                            Log.d("arrayItemViewModel", "正确的model${vm.model}")

                            Log.d("arrayItemViewModel", "adapter$adapter")
                            Log.d("arrayItemViewModel", "viewHolder${vh.adapterPosition}")
                            vm.model.title = "测试更新"
                            adapter.set(vh.adapterPosition,vm)
                        }
                    }
                )
            }
            into(rv_list_dsl)
        }

    }
}