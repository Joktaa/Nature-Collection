package fr.jorisrouziere.naturecollection.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.jorisrouziere.naturecollection.*

class PlantAdapter(val context: MainActivity,
                   private val plantList: List<PlantModel>,
                   private val layoutId: Int
                ) : RecyclerView.Adapter<PlantAdapter.ViewHolder>() {

    // boite de tous les composant à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val plantImage = view.findViewById<ImageView>(R.id.image_item)
        val plantName: TextView? = view.findViewById(R.id.name_item)
        val plantDescription: TextView? = view.findViewById<TextView>(R.id.description_item)
        val starIcon = view.findViewById<ImageView>(R.id.star_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return plantList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPlant = plantList[position]
        val repo = PlanteRepository()

        Glide.with(context).load(Uri.parse(currentPlant.imageUrl)).into(holder.plantImage)
        holder.plantName?.text = currentPlant.name
        holder.plantDescription?.text = currentPlant.description
        if(currentPlant.liked) {
            holder.starIcon.setImageResource(R.drawable.ic_like)
        } else {
            holder.starIcon.setImageResource(R.drawable.ic_unlike)
        }

        // ajouter une interaction sur l'étoile
        holder.starIcon.setOnClickListener {
            currentPlant.liked = !currentPlant.liked

            // maj
            repo.updatePlant(currentPlant)
            }

        // interaction lors du clique sur une plante
        holder.itemView.setOnClickListener {
            //afficher la popup
            PlantPopup(this, currentPlant).show()
        }
    }
}