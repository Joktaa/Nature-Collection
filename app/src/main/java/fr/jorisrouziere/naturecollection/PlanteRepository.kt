package fr.jorisrouziere.naturecollection

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PlanteRepository {
    
    object Singleton {
        // Connection
        val databaseRef = FirebaseDatabase.getInstance("https://naturecollection-4c4b2-default-rtdb.europe-west1.firebasedatabase.app/").getReference("plants")

        // Créer la liste de plante
        val plantList = arrayListOf<PlantModel>()
    }

    fun updateData(callback: () -> Unit) {
        // absorber les données de la BDD dans la liste
        Singleton.databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                // retirer l'ancienne liste
                Singleton.plantList.clear()

                // recolter la liste
                for (ds in snapshot.children){
                    // construire un objet plante
                    val plant = ds.getValue(PlantModel::class.java)

                    // verifier que la plante n'est pas nulle
                    if(plant != null) {
                        Singleton.plantList.add(plant)
                    }
                }
                // actionner le callback
                callback()
            }
        })
    }

    // MAJ
    fun updatePlant(plant: PlantModel) {
        Singleton.databaseRef.child(plant.id).setValue(plant)
    }
}