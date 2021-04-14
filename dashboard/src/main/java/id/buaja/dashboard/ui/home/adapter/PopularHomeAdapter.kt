package id.buaja.dashboard.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.buaja.dashboard.databinding.ItemListPopularHomeBinding
import id.buaja.domain.model.Popular

/**
 * Created by Julsapargi Nursam on 4/11/21.
 */


class PopularHomeAdapter(private val data: List<Popular>, private val listener: (Popular) -> Unit) :
    RecyclerView.Adapter<PopularHomeAdapter.ViewHolder>() {

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
        fun bind(item: Popular, listener: (Popular) -> Unit) {
            with(binding) {
                ivImage.load(item.image)

                container.setOnClickListener {
                    listener.invoke(item)
                }
            }
        }
    }
}