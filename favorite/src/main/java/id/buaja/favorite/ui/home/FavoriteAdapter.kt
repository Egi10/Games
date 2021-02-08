package id.buaja.favorite.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import id.buaja.core.domain.model.FavoriteModel
import id.buaja.core.utils.dipToPx
import id.buaja.favorite.databinding.ItemListFavoriteBinding

/**
 * Created by Julsapargi Nursam on 2/6/21.
 */


class FavoriteAdapter(
    private val data: List<FavoriteModel>,
    private val listener: (FavoriteModel) -> Unit
) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemListFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(data[position], listener)

    class ViewHolder(private val binding: ItemListFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FavoriteModel, listener: (FavoriteModel) -> Unit) {
            with(binding) {
                ivImage.load(item.backgroundImage) {
                    transformations(
                        RoundedCornersTransformation(
                            bottomLeft = 10f.dipToPx(ivImage.context),
                            bottomRight = 10f.dipToPx(ivImage.context),
                            topLeft = 10f.dipToPx(ivImage.context),
                            topRight = 10f.dipToPx(ivImage.context)
                        )
                    )
                }
                tvNameGame.text = item.nameGame
                tvGenre.text = item.genre
                container.setOnClickListener {
                    listener(item)
                }
            }
        }
    }
}