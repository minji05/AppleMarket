package com.example.applemarket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.ItemListBinding
import java.text.DecimalFormat

class MyAdapter(val Items: MutableList<MyItem>) : RecyclerView.Adapter<MyAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view : View, position : Int)
    }

    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener {  //클릭이벤트추가부분
            itemClick?.onClick(it, position)
        }

        holder.productImg.setImageResource(Items[position].productImg)
        holder.productName.text = Items[position].productName
        holder.address.text = Items[position].address
        val priceFormat = DecimalFormat("#,###")
        val formattedPrice = priceFormat.format(Items[position].price.toInt())
        holder.price.text = formattedPrice
        holder.commentTxt.text = Items[position].like.toString()
        holder.interestTxt.text = Items[position].chat.toString()
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
    }
}