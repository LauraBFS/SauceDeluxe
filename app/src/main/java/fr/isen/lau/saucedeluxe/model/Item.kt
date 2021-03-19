package fr.isen.lau.saucedeluxe.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Item(
        @SerializedName("name_fr") val name: String,
        @SerializedName("images") private val images: List<String>,
        @SerializedName("ingredients") val ingredients: List<Ingredient>,
        @SerializedName("prices") private val prices: List<Price>,
) : Serializable {

    fun getAffichagePrice() = prices[0].price + "€"

    fun getFirstPicture() = if (images.isNotEmpty() && images[0].isNotEmpty()) {
        images[0]
    } else {
        null
    }
    fun getIngredients(): String = ingredients.map(Ingredient::name).joinToString(", ")
}