package fr.isen.lau.saucedeluxe.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Order(
        @SerializedName("item") var item: Item,
        @SerializedName("quantity") var quantity: Int,
) : Serializable