package fr.jorisrouziere.naturecollection.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.jorisrouziere.naturecollection.MainActivity
import fr.jorisrouziere.naturecollection.PlantModel
import fr.jorisrouziere.naturecollection.R
import fr.jorisrouziere.naturecollection.adapter.PlantAdapter
import fr.jorisrouziere.naturecollection.adapter.PlantItemDecoration

class HomeFragment(private val context: MainActivity) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_home, container, false)

        // liste de plantes
        val plantList = arrayListOf<PlantModel>()
        plantList.add(PlantModel("Pissenlit", "Envole", "https://cdn.pixabay.com/photo/2014/05/01/17/51/flower-335662_960_720.jpg", false))
        plantList.add(PlantModel("Rose", "Piquante", "https://cdn.pixabay.com/photo/2016/09/03/23/18/rose-1642970_960_720.jpg", false))
        plantList.add(PlantModel("Cactus", "Piquant++", "https://cdn.pixabay.com/photo/2014/07/29/08/55/cactus-404362_960_720.jpg", true))
        plantList.add(PlantModel("Tulipe", "Beau", "https://cdn.pixabay.com/photo/2017/02/15/13/40/tulips-2068692_960_720.jpg", false))

        // récupérer le reycler view
        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.adapter = PlantAdapter(context, plantList, R.layout.item_horizontal_plant)

        //récupérer le second recycler view
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = PlantAdapter(context, plantList, R.layout.item_vertical_plant)
        verticalRecyclerView.addItemDecoration(PlantItemDecoration())

        return view
    }
}