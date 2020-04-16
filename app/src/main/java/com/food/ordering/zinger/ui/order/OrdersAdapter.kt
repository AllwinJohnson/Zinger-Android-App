package com.food.ordering.zinger.ui.order

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.food.ordering.zinger.R
import com.food.ordering.zinger.data.model.OrderData
import com.food.ordering.zinger.databinding.ItemOrderBinding
import com.food.ordering.zinger.utils.AppConstants
import com.food.ordering.zinger.utils.StatusHelper
import java.text.SimpleDateFormat

class OrdersAdapter(private val orderList: List<OrderData>, private val listener: OnItemClickListener) : RecyclerView.Adapter<OrdersAdapter.OrderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): OrderViewHolder {
        val binding: ItemOrderBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_order, parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(orderList[position], holder.adapterPosition, listener)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    class OrderViewHolder(var binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(order: OrderData, position: Int, listener: OnItemClickListener) {
            //Picasso.get().load(menuItem.photoUrl).into(binding.imageShop)
            binding.textShopName.text = order.transactionModel.orderModel.shopModel?.name
            val apiDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
            val appDateFormat = SimpleDateFormat("dd MMMM yyyy, hh:mm aaa")
            val date = apiDateFormat.parse(order.transactionModel.orderModel.date)
            val dateString = appDateFormat.format(date)
            binding.textOrderTime.text = dateString
            binding.textOrderPrice.text = "₹ " + order.transactionModel.orderModel.price.toInt().toString()
            var items = ""
            order.orderItemsList.forEach {
                items += it.quantity.toString() + " X " + it.itemModel.name + ", "
            }
            items = items.substring(0, items.length - 2)
            binding.textOrderItems.text = items
            binding.textOrderStatus.text = StatusHelper.getStatusMessage(order.transactionModel.orderModel.orderStatus)
            if(order.transactionModel.orderModel.rating==0.0){
                binding.buttonTrackRate.visibility = View.VISIBLE
                binding.textOrderRating.visibility = View.GONE
            }else{
                binding.buttonTrackRate.visibility = View.GONE
                binding.textOrderRating.visibility = View.VISIBLE
                binding.textOrderRating.text = order.transactionModel.orderModel.rating.toString()
            }
            when (order.transactionModel.orderModel.orderStatus) {

                AppConstants.ORDER_STATUS_COMPLETED,
                AppConstants.ORDER_STATUS_DELIVERED,
                AppConstants.ORDER_STATUS_REFUND_COMPLETED -> {
                    binding.buttonTrackRate.text = "Rate Food"
                    binding.textOrderStatus.setCompoundDrawablesWithIntrinsicBounds(
                            binding.textOrderStatus.context.getDrawable(R.drawable.ic_checked),
                            null,
                            null,
                            null)
                }

                AppConstants.ORDER_STATUS_CANCELLED_BY_SELLER,
                AppConstants.ORDER_STATUS_CANCELLED_BY_USER,
                AppConstants.ORDER_STATUS_TXN_FAILURE -> {
                    binding.buttonTrackRate.text = "Rate Order"
                    binding.textOrderStatus.setCompoundDrawablesWithIntrinsicBounds(
                            binding.textOrderStatus.context.getDrawable(R.drawable.ic_cancelled),
                            null,
                            null,
                            null)
                }

                else -> {
                    binding.buttonTrackRate.text = "Track Order"
                    binding.textOrderStatus.setCompoundDrawablesWithIntrinsicBounds(
                            binding.textOrderStatus.context.getDrawable(R.drawable.ic_pending),
                            null,
                            null,
                            null)
                }

            }
            binding.layoutRoot.setOnClickListener { listener.onItemClick(order, position) }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(item: OrderData?, position: Int)
    }

}