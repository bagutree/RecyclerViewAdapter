package com.julive.adapter_demo

import android.widget.TextView
import com.julive.adapter.anko.AnkoViewModel
import com.julive.adapter.anko.ankoViewModelDsl
import com.julive.adapter.anko.getAnkoView
import com.julive.adapter.binding.BindingViewModel
import com.julive.adapter.binding.bindingViewModelDsl
import com.julive.adapter.core.*
import com.julive.adapter_demo.anko.AnkoItemView
import com.julive.adapter_demo.sorted.ModelTest
import kotlin.random.Random

fun createViewModelList(max: Int = 10) = (0..max).map { _ ->
    layoutViewModelDsl(R.layout.item_test, ModelTest("title", "subTitle")) {
        onBindViewHolder {
            val model = getModel<ModelTest>()
            getView<TextView>(R.id.tv_title).text = model?.title
            getView<TextView>(R.id.tv_subTitle).text = model?.subTitle
        }
        itemView.setOnClickListener {
            val vm = getViewModel<LayoutViewModel<ModelTest>>()
            //修改Model数据
            vm?.model?.title = "测试更新${Random.nextInt(10000)}"
            //用Adapter更新数据
            getAdapter<ListAdapter>()?.set(adapterPosition, vm)
        }
    }
}

fun createAnkoViewModelList(max: Int = 10) = (0..max).map { _ ->
    //AnkoViewModel对象
    ankoViewModelDsl(
        ModelTest("title", "ankoViewModelDsl"),
        { AnkoItemView() }
    ) {
        onBindViewHolder { _ ->
            val model = getModel<ModelTest>()
            val ankoView = getAnkoView<AnkoItemView>()
            ankoView?.tvTitle?.text = model?.title
            ankoView?.tvSubTitle?.text = model?.subTitle
        }
        itemView.setOnClickListener {
            val viewModel = getViewModel<AnkoViewModel<ModelTest, AnkoItemView>>()
            viewModel?.model?.title = "点击更新${Random.nextInt(10000)}"
            getAdapter<ListAdapter>()?.set(adapterPosition, viewModel)
        }
    }
}

fun createBindingViewModelList(max: Int = 10) = (0..max).map {
    bindingViewModelDsl(
        R.layout.item_binding_layout,
        BR.model,
        ModelTest("title", "bindingViewModelDsl")
    ) {
        itemView.setOnClickListener {
            val viewModel = getViewModel<BindingViewModel<ModelTest>>()
            viewModel?.model?.title = "${java.util.Random().nextInt(100)}"
            getAdapter<ListAdapter>()?.set(adapterPosition, viewModel)
        }
    }
}