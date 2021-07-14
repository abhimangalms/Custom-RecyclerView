package com.craysol.customrecyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class RecyclerItemAdapter(
    private val itemList: List<RecyclerItemModel>,
    val itemClickedListener: ItemClickedListener
) :
    RecyclerView.Adapter<RecyclerItemAdapter.ExampleViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item,
            parent, false
        )
        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.tvItemValue.text = currentItem.boxIdValue

        holder.cardViewItem.setOnClickListener {
            itemClickedListener.onItemClick(position)
        }

        holder.imageButtonRemove.setOnClickListener {

            (itemList as ArrayList<RecyclerItemModel>).removeAt(position)
            notifyItemRemoved(position)
            notifyDataSetChanged()

            Log.d("removedItem", "onBindViewHolder: $position")

        }

    }

    override fun getItemCount() = itemList.size


    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItemValue: TextView = itemView.findViewById(R.id.textViewScanBox)
        val imageButtonRemove: ImageButton = itemView.findViewById(R.id.imageBtnRemove)
        val cardViewItem: CardView = itemView.findViewById(R.id.cardViewItemBox)

    }


    interface ItemClickedListener {

        fun onItemClick(position: Int)

    }
}