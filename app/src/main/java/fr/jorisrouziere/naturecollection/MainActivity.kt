package fr.jorisrouziere.naturecollection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.jorisrouziere.naturecollection.fragments.CollectionFragment
import fr.jorisrouziere.naturecollection.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // charger le plante repo
        val repo = PlanteRepository()

        // mettre à jour + execution d'une portion de code
        repo.updateData{
            // injecter le fragment dans notre boite (fragment_container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, CollectionFragment(this))
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}