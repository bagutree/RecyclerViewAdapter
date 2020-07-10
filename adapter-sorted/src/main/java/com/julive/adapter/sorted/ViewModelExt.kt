package com.julive.adapter.sorted

import androidx.recyclerview.widget.RecyclerView
import com.julive.adapter.core.DefaultItemViewModel

typealias SortedItemVMType = SortedItemViewModel<out SortedModel,out RecyclerView.ViewHolder>

abstract class SortedItemViewModel<M : SortedModel, VH : RecyclerView.ViewHolder> :
    DefaultItemViewModel<M,SortedListAdapter>()