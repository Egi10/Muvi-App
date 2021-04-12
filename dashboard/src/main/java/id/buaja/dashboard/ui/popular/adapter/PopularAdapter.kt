package id.buaja.dashboard.ui.popular.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.buaja.dashboard.databinding.ItemListPopularBinding
import id.buaja.domain.model.Popular

/**
 * Created by Julsapargi Nursam on 4/12/21.
 */


class PopularAdapter(private val data: List<Popular>, private val listener: (Popular) -> Unit) :
    RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemListPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(data[position], listener)

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