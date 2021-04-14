package id.buaja.dashboard.ui.popular.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.buaja.dashboard.databinding.ItemListPopularBinding
import id.buaja.domain.model.Popular
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Julsapargi Nursam on 4/12/21.
 */

class PopularAdapter(private val data: ArrayList<Popular>, private val listener: (Popular) -> Unit) :
    RecyclerView.Adapter<PopularAdapter.ViewHolder>(), Filterable {

    var dataFilter = ArrayList<Popular>()

    init {
        dataFilter = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemListPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = dataFilter.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(dataFilter[position], listener)

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                dataFilter = if (charSearch.isEmpty()) {
                    data
                } else {
                    val resultList = ArrayList<Popular>()
                    for (row in data) {
                        if (row.title?.toLowerCase(Locale.ROOT)?.contains(charSearch.toLowerCase(Locale.ROOT)) == true) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = dataFilter
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataFilter = results?.values as ArrayList<Popular>
                notifyDataSetChanged()
            }

        }
    }

    class ViewHolder(private val binding: ItemListPopularBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Popular, listener: (Popular) -> Unit) {
            with(binding) {
                ivImage.load(item.image)
                tvTitle.text = item.title
                tvCast.text = item.actor
                tvGenre.text = item.genre
            }
        }
    }
}