package fr.isen.lau.saucedeluxe.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OrderList(
        @SerializedName("order") val order: MutableList<Order>,
) : Serializable