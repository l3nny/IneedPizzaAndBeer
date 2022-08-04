package com.example.ineedpizzabeer.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.*
import androidx.recyclerview.widget.RecyclerView
import com.example.ineedpizzabeer.BR

open class GenericRecyclerBindingAdapter<T>(
    private val holderLayout: Int
) :
    RecyclerView.Adapter<GenericRecyclerBindingAdapter.BindingHolder>() {
    private var items:  MutableList<T> = mutableListOf()
    private var onItemClickListener: OnItemClickListener<T>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(holderLayout, parent, false)
        return BindingHolder(v)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val item = items[position]
        holder.binding?.root?.setOnClickListener {
            if (onItemClickListener != null) onItemClickListener?.onItemClick(
                position,
                item
            )
        }
        holder.binding?.setVariable(BR.item, item)
        holder.binding?.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<T>?) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener<T> {
        fun onItemClick(position: Int, item: T)
    }

    class BindingHolder(v: View) : RecyclerView.ViewHolder(v) {
        val binding: ViewDataBinding?

        init {
            binding = v.let { DataBindingUtil.bind(it) }
        }
    }

    fun submitList(items: MutableList<T> = mutableListOf()) {
        this.items = items
        notifyItemChanged(BR.item)
    }
}