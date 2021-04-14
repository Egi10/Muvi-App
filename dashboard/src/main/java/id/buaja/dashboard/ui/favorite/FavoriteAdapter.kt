package id.buaja.dashboard.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.buaja.dashboard.databinding.ItemListFavoriteBinding
import id.buaja.domain.model.MovieFavorite
import id.buaja.domain.model.Popular
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Julsapargi Nursam on 4/12/21.
 */

class FavoriteAdapter(
    private val data: ArrayList<MovieFavorite>,
    private val listener: (MovieFavorite) -> Unit
) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>(), Filterable {

    var dataFilter = ArrayList<MovieFavorite>()

    init {
        dataFilter = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemListFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
                    val resultList = ArrayList<MovieFavorite>()
                    for (row in data) {
                        if (row.title.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
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
                dataFilter = results?.values as ArrayList<MovieFavorite>
                notifyDataSetChanged()
            }

        }
    }

    class ViewHolder(private val binding: ItemListFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieFavorite, listener: (MovieFavorite) -> Unit) {
            with(binding) {
                ivImage.load(item.image)
                tvTitle.text = item.title
                tvGenre.text = item.genre
                tvDate.text = item.date

                container.setOnClickListener {
                    listener.invoke(item)
                }
            }
        }
    }
}