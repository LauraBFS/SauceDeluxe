package fr.isen.lau.saucedeluxe.model

import com.google.gson.annotations.SerializedName
import fr.isen.lau.saucedeluxe.categorie.Items
import java.io.Serializable

data class DataResult(
        @SerializedName
        ("data") val data: List<Items>,
) : Serializable
