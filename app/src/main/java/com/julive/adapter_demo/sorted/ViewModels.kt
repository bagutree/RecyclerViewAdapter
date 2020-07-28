package com.julive.adapter_demo.sorted

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.julive.adapter.core.DefaultViewHolder
import com.julive.adapter.core.LayoutViewModel
import com.julive.adapter.core.SameModel
import com.julive.adapter.sorted.SortedListAdapter
import com.julive.adapter.sorted.SortedModel
import com.julive.adapter_demo.R
import kotlin.random.Random

/**
 * Model
 *
 * // 扩展DiffModel  兼容DiffUtil
 */
data class ModelTest(var title: String, var subTitle: String) : SameModel {
    override fun <T : SameModel> getChangePayload(newItem: T): Any? {
        return null
    }

    override var uniqueId: String = title
}

/**
 * sortedId 排序用
 * title 作为uniqueId ，RecyclerView ItemView 更新的时候，唯一值，注意列表是可以出现一样的uniqueId的，
 * 如果想更新请调用Adapter updateItem 这样才能保证列表中uniqueId唯一
 */
data class SortedModelTest(
    val sortedId: Int, var title: String, var subTitle: String,
    override var uniqueId: String = title
) : SortedModel {
    override fun <T : SortedModel> compare(model: T): Int {
        if (sortedId > (model as? SortedModelTest)?.sortedId ?: 0) return 1
        if (sortedId < (model as? SortedModelTest)?.sortedId ?: 0) return -1
        return 0
    }
}

/**
 *
 */
class SortedItemViewModelTest : LayoutViewModel<SortedModelTest>(R.layout.item_test) {
    init {
        onBindViewHolder { _ ->
            getView<TextView>(R.id.tv_title)?.text = model?.title
            getView<TextView>(R.id.tv_subTitle)?.text = model?.subTitle
        }
        onCreateViewHolder {
            itemView.setOnClickListener {
                val vm =
                    getAdapter<SortedListAdapter>()?.getItem(adapterPosition) as SortedItemViewModelTest
                vm.model?.subTitle = "刷新自己${Random.nextInt(100)}"
                getAdapter<SortedListAdapter>()?.set(adapterPosition, vm)
            }
        }
    }
}