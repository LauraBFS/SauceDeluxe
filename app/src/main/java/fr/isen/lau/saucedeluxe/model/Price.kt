package fr.isen.lau.saucedeluxe.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Price(
        @SerializedName("price") val price: String,
        @SerializedName("size") val size: String,
) : Serializable