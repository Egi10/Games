package id.buaja.games.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.buaja.games.R
import id.buaja.games.ui.home.HomeViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getDevelopers()
    }
}