package com.example.filmesfavoritos

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var moviesRecyclerView: RecyclerView
    private var isGridView = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val titleInput = findViewById<EditText>(R.id.titleInput)
        val directorInput = findViewById<EditText>(R.id.directorInput)
        val addButton = findViewById<Button>(R.id.addButton)
        val layoutToggleButton = findViewById<Button>(R.id.layoutToggleButton)
        val emptyMessage = findViewById<TextView>(R.id.emptyMessage)
        moviesRecyclerView = findViewById(R.id.moviesRecyclerView)

        val initialMovies = mutableListOf(
            Movie("Interestelar", "Christopher Nolan"),
            Movie("A Viagem de Chihiro", "Hayao Miyazaki"),
            Movie("O Poderoso Chefão", "Francis Ford Coppola"),
            Movie("Parasita", "Bong Joon-ho")
        )

        movieAdapter = MovieAdapter(initialMovies)
        moviesRecyclerView.adapter = movieAdapter
        applyLayoutManager()
        updateEmptyState(emptyMessage)

        addButton.setOnClickListener {
            val title = titleInput.text.toString().trim()
            val director = directorInput.text.toString().trim()

            titleInput.error = null
            directorInput.error = null

            when {
                title.isEmpty() -> titleInput.error = getString(R.string.title_required)
                director.isEmpty() -> directorInput.error = getString(R.string.director_required)
                else -> {
                    movieAdapter.addMovie(Movie(title, director))
                    titleInput.text.clear()
                    directorInput.text.clear()
                    updateEmptyState(emptyMessage)
                    moviesRecyclerView.scrollToPosition(movieAdapter.itemCount - 1)
                }
            }
        }

        layoutToggleButton.setOnClickListener {
            isGridView = !isGridView
            applyLayoutManager()
            layoutToggleButton.text = if (isGridView) {
                getString(R.string.show_list)
            } else {
                getString(R.string.show_grid)
            }
        }
    }

    private fun applyLayoutManager() {
        moviesRecyclerView.layoutManager = if (isGridView) {
            GridLayoutManager(this, 2)
        } else {
            LinearLayoutManager(this)
        }
    }

    private fun updateEmptyState(emptyMessage: TextView) {
        emptyMessage.visibility = if (movieAdapter.itemCount == 0) {
            TextView.VISIBLE
        } else {
            TextView.GONE
        }
    }
}
