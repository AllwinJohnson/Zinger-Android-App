package com.food.ordering.zinger.data.model
import com.google.gson.annotations.SerializedName


data class OrdersResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: List<OrderData>?,
    @SerializedName("message")
    val message: String
)

data class OrderData(
    @SerializedName("orderItemsList")
    val orderItemsList: List<OrderItems>,
    @SerializedName("transactionModel")
    val transactionModel: TransactionModel
)

data class OrderItems(
    @SerializedName("itemModel")
    val itemModel: ItemModel,
    @SerializedName("orderModel")
    val orderModel: OrderModel,
    @SerializedName("price")
    val price: Double,
    @SerializedName("quantity")
    val quantity: Int
)

data class TransactionModel(
    @SerializedName("bankName")
    val bankName: String,
    @SerializedName("bankTransactionId")
    val bankTransactionId: String,
    @SerializedName("checksumHash")
    val checksumHash: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("gatewayName")
    val gatewayName: String,
    @SerializedName("orderModel")
    val orderModel: OrderModel,
    @SerializedName("paymentMode")
    val paymentMode: String,
    @SerializedName("responseCode")
    val responseCode: String,
    @SerializedName("responseMessage")
    val responseMessage: String,
    @SerializedName("transactionId")
    val transactionId: String
)

data class ItemModel(
    @SerializedName("category")
    val category: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isAvailable")
    val isAvailable: Int,
    @SerializedName("isVeg")
    val isVeg: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("photoUrl")
    val photoUrl: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("shopModel")
    val shopModel: ShopModel?
)

data class OrderModel(
    @SerializedName("cookingInfo")
    val cookingInfo: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("deliveryLocation")
    val deliveryLocation: String?,
    @SerializedName("deliveryPrice")
    val deliveryPrice: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("lastStatusUpdatedTime")
    val lastStatusUpdatedTime: String?,
    @SerializedName("orderStatus")
    val orderStatus: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("rating")
    val rating: Double?,
    @SerializedName("secretKey")
    val secretKey: String?,
    @SerializedName("shopModel")
    val shopModel: ShopModel?,
    @SerializedName("userModel")
    val userModel: UserModel?
)