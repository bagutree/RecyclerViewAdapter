package com.julive.adapter.core

import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

interface ViewModel<M, VH : RecyclerView.ViewHolder, Adapter:IAdapter<*>> :
    ViewHolderFactory<VH> {
    var model: M?
    var adapter: Adapter?
    val itemViewType: Int
        get() = layoutRes

    @get:LayoutRes
    val layoutRes: Int
    fun bindVH(
        viewHolder: VH,
        model: M,
        payloads: List<Any>
    )

    fun unBindVH(viewHolder: VH)
}