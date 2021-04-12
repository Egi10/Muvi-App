package id.buaja.dashboard.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.buaja.dashboard.databinding.ItemListPopularBinding
import id.buaja.dashboard.databinding.ItemListPopularHomeBinding
import id.buaja.domain.model.ComingSoon

/**
 * Created by Julsapargi Nursam on 4/11/21.
 */


class ComingSoonAdapter(private val data: List<ComingSoon>, private val listener: (ComingSoon) -> Unit) :
    RecyclerView.Adapter<ComingSoonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemListPopularHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = if (data.size > 10) {
        10
    } else {
        data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(data[position], listener)

    class ViewHolder(private val binding: ItemListPopularHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ComingSoon, listener: (ComingSoon) -> Unit) {
            with(binding) {
                ivImage.load(item.image)
            }
        }
    }
}