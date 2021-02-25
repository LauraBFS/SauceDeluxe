package fr.isen.lau.saucedeluxe.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DataResult(
        @SerializedName("data") val data: List<Category>,
) : Serializable
