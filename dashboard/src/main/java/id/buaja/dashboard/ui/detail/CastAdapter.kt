package id.buaja.dashboard.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import id.buaja.dashboard.databinding.ItemListCastBinding
import id.buaja.domain.model.Cast

/**
 * Created by Julsapargi Nursam on 4/14/21.
 */


class CastAdapter(private val data: List<Cast>) :
    RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemListCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(data[position])

    class ViewHolder(private val binding: ItemListCastBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Cast) {
            with(binding) {
                ivImage.load(item.images) {
                    transformations(CircleCropTransformation())
                }
                tvName.text = item.name
            }
        }
    }
}