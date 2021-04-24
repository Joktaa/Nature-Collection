package fr.jorisrouziere.naturecollection.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.jorisrouziere.naturecollection.MainActivity
import fr.jorisrouziere.naturecollection.PlantModel
import fr.jorisrouziere.naturecollection.PlanteRepository
import fr.jorisrouziere.naturecollection.R
import fr.jorisrouziere.naturecollection.adapter.PlantAdapter
import fr.jorisrouziere.naturecollection.adapter.PlantItemDecoration

class HomeFragment(private val context: MainActivity) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_home, container, false)


        // récupérer le reycler view
        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.adapter = PlantAdapter(context, PlanteRepository.Singleton.plantList, R.layout.item_horizontal_plant)

        //récupérer le second recycler view
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = PlantAdapter(context, PlanteRepository.Singleton.plantList, R.layout.item_vertical_plant)
        verticalRecyclerView.addItemDecoration(PlantItemDecoration())

        return view
    }
}