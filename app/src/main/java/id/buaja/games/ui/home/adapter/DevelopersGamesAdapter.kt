package id.buaja.games.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import id.buaja.core.domain.model.DevelopersGameModel
import id.buaja.games.R
import id.buaja.games.databinding.ItemListDevelopersGameBinding
import id.buaja.games.utils.dipToPx

/**
 * Created by Julsapargi Nursam on 1/27/21.
 */


class DevelopersGamesAdapter(
    private val data: List<DevelopersGameModel>,
    private val listener: (DevelopersGameModel) -> Unit
) :
    RecyclerView.Adapter<DevelopersGamesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemListDevelopersGameBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(data[position], listener)

    class ViewHolder(private val binding: ItemListDevelopersGameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DevelopersGameModel, listener: (DevelopersGameModel) -> Unit) {
            with(binding) {

                ivImage.load(item.imageBackground) {
                    transformations(
                        RoundedCornersTransformation(
                            bottomLeft = 15f.dipToPx(ivImage.context),
                            bottomRight = 15f.dipToPx(ivImage.context)
                        )
                    )
                    placeholder(R.drawable.empty)
                }
                tvNameDevelopersGame.text = item.name
                tvTotalsGame.text =
                    tvTotalsGame.context.getString(R.string.jml_games, item.sizeGame.toString())

                btnDetails.setOnClickListener {
                    listener(item)
                }
            }
        }
    }
}