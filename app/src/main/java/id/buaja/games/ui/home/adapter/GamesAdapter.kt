package id.buaja.games.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import id.buaja.core.domain.model.GamesModel
import id.buaja.games.R
import id.buaja.games.databinding.ItemListGamesBinding
import id.buaja.games.utils.dipToPx

/**
 * Created by Julsapargi Nursam on 1/28/21.
 */


class GamesAdapter(private val data: List<GamesModel>, private val listener: (GamesModel) -> Unit) :
    RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemListGamesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(data[position], listener)

    class ViewHolder(private val binding: ItemListGamesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GamesModel, listener: (GamesModel) -> Unit) {
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
                    placeholder(R.drawable.empty)
                }

                tvTitle.text = item.title
                tvReview.text = String.format("%s Reviews Count", item.reviewCount)

                container.setOnClickListener {
                    listener(item)
                }
            }
        }
    }
}