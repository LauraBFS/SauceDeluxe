package fr.isen.lau.saucedeluxe.model

import com.google.gson.annotations.SerializedName
import fr.isen.lau.saucedeluxe.categorie.Items
import java.io.Serializable

data class Category (
        @SerializedName
        ("name_fr") val name: String,
        @SerializedName
        ("items") val items: List<Items>,
) : Serializable