package fr.isen.lau.saucedeluxe.categorie

import java.io.Serializable

class Items : Serializable {
    fun titleItem(string: String) {
        TODO("Not yet implemented")
    }

    fun pictureItem(int: Int) {
        TODO("Not yet implemented")
    }

    fun priceItem(int: String) {
        TODO("Not yet implemented")
    }

    val items: List<Items>?
        get() {
            TODO()
        }
    val name: Any
        get() {
            TODO()
        }
    var titleItem: String? = null
    var pictureItem = 0
    var priceItem: String? = null

        constructor() {}
        constructor(titleItem: String?, pictureItem: Int, priceItem: String?) {
            this.titleItem = titleItem
            this.pictureItem = pictureItem
            this.priceItem = priceItem
        }
}