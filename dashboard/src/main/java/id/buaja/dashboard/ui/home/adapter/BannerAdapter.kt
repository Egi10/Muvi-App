package id.buaja.dashboard.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.buaja.dashboard.databinding.ItemListBannerBinding
import id.buaja.domain.model.Banner

/**
 * Created by Julsapargi Nursam on 4/11/21.
 */


class BannerAdapter(private val data: List<Banner>) :
    RecyclerView.Adapter<BannerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemListBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(data[position])

    class ViewHolder(private val binding: ItemListBannerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Banner) {
            with(binding) {
                ivImage.load(item.image)
            }
        }
    }
}