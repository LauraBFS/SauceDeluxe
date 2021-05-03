package fr.isen.lau.saucedeluxe.model

public class BLE {

    private var title: String? = null
    private var rating: String? = null
    private var year: String? = null
    private var plot: String? = null
    private var expanded = false

    fun BLE(title: String?, rating: String?, year: String?, plot: String?) {
        this.title = title
        this.rating = rating
        this.year = year
        this.plot = plot
        expanded = false
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getRating(): String? {
        return rating
    }

    fun setRating(rating: String?) {
        this.rating = rating
    }

    fun getYear(): String? {
        return year
    }

    fun setYear(year: String?) {
        this.year = year
    }

    fun getPlot(): String? {
        return plot
    }

    fun setPlot(plot: String?) {
        this.plot = plot
    }

    fun isExpanded(): Boolean {
        return expanded
    }

    fun setExpanded(expanded: Boolean) {
        this.expanded = expanded
    }

    override fun toString(): String {
        return "BLE{" +
                "title='" + title + '\'' +
                ", rating='" + rating + '\'' +
                ", year='" + year + '\'' +
                ", plot='" + plot + '\'' +
                ", expanded=" + expanded +
                '}'
    }
}