package com.jkfort.aarv.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jkfort.aarv.R
import kotlinx.android.synthetic.main.marque_item.view.*

class MarqueeRecyclerAdapter(
        private val mData: List<String>,
        private val itemClickListener: (Int) -> Unit
) : RecyclerView.Adapter<MarqueeRecyclerAdapter.MarqueHolder>() {
    private val size: Int = mData.size
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MarqueHolder {
        val view =
                LayoutInflater.from(viewGroup.context).inflate(R.layout.marque_item, viewGroup, false)
        return MarqueHolder(view)
    }

    override fun onBindViewHolder(marqueHolder: MarqueHolder, position: Int) {
        marqueHolder.bind(mData[position % size])
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    inner class MarqueHolder(view: View) :
            RecyclerView.ViewHolder(view) {
        fun bind(text: String) {
            itemView.tvMarqueeItem.text = text
            itemView.setOnClickListener {
                itemClickListener(adapterPosition % size)
            }
        }
    }
}