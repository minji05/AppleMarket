package com.example.applemarket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.ItemListBinding
import java.text.DecimalFormat

class MyAdapter(val Items: MutableList<MyItem>) : RecyclerView.Adapter<MyAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    interface ItemLongClick {
        fun onLongClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null
    var itemLongClick: ItemLongClick? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = Items[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }

        holder.itemView.setOnLongClickListener {
            itemLongClick?.onLongClick(it, position)
            true
        }

        // 좋아요 상태에 따라 아이콘 변경
        if (item.isLike) {
            holder.binding.interestImg.setImageResource(R.drawable.heart)
        } else {
            holder.binding.interestImg.setImageResource(R.drawable.unheart)
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getItemCount(): Int {
        return Items.size
    }

    inner class Holder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        val productImg = binding.productImg
        val productName = binding.productName
        val address = binding.address
        val price = binding.price
        val commentTxt = binding.commentTxt
        val interestTxt = binding.interestTxt

        fun bind(item: MyItem) {
            productImg.setImageResource(item.productImg)

            // Handle product name
            val maxLines = 2
            val ellipsis = "..."
            val nameLines = item.productName.split("\\n")
            val nameToShow = if (nameLines.size > maxLines) {
                nameLines.take(maxLines).joinToString("\n") + ellipsis
            } else {
                item.productName
            }
            productName.text = nameToShow

            address.text = item.address

            val priceFormat = DecimalFormat("#,###")
            val formattedPrice = priceFormat.format(item.price.toInt())
            price.text = formattedPrice

            commentTxt.text = item.commentCount.toString()
            interestTxt.text = item.likeCount.toString() // 이 부분 추가

            // 좋아요 상태에 따라 아이콘 변경
            if (item.isLike) {
                binding.interestImg.setImageResource(R.drawable.heart)
            } else {
                binding.interestImg.setImageResource(R.drawable.unheart)
            }
        }
    }}

