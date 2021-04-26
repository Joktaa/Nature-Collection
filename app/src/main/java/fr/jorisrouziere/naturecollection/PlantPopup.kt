package fr.jorisrouziere.naturecollection

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import fr.jorisrouziere.naturecollection.adapter.PlantAdapter

class PlantPopup(private val adapter: PlantAdapter, private val currentPlant: PlantModel) : Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_plants_details)
        setupComponents()
        setupCloseButon()
        setupDeleteButton()
        setupStarButton()
    }

    private fun updateStar(button: ImageView) {
        if(currentPlant.liked) {
            button.setImageResource(R.drawable.ic_like)
        } else {
            button.setImageResource(R.drawable.ic_unlike)
        }
    }

    private fun setupStarButton() {
        val starButton = findViewById<ImageView>(R.id.star_button)

        updateStar(starButton)

        // interaction
        starButton.setOnClickListener {
            currentPlant.liked = !currentPlant.liked
            val repo = PlanteRepository()
            repo.updatePlant(currentPlant)
            updateStar(starButton)
        }
    }

    private fun setupDeleteButton() {
        findViewById<ImageView>(R.id.delete_button).setOnClickListener {
            // supprimer la plante de la BDD
            val repo = PlanteRepository()
            repo.deletePlant(currentPlant)
            dismiss()
        }
    }

    private fun setupCloseButon() {
        findViewById<ImageView>(R.id.close_button).setOnClickListener {
            // fermer la fenetre
            dismiss()
        }
    }

    private fun setupComponents() {
        // actualiser l'image
        val planteImage = findViewById<ImageView>(R.id.image_item)
        Glide.with(adapter.context).load(Uri.parse(currentPlant.imageUrl)).into(planteImage)

        // actualiser le nom$
        findViewById<TextView>(R.id.popup_plante_name).text = currentPlant.name

        // actauliser la description
        findViewById<TextView>(R.id.popup_plante_description_subtitle).text = currentPlant.description

        // actualiser la croissance
        findViewById<TextView>(R.id.popup_plante_grow_subtitle).text = currentPlant.grow

        // actualiser la consommation d'eau
        findViewById<TextView>(R.id.popup_plante_water_subtitle).text = currentPlant.water
    }
}