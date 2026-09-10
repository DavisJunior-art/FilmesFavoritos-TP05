package com.example.filmesfavoritos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(private val movies: MutableList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText: TextView = itemView.findViewById(R.id.movieTitle)
        val directorText: TextView = itemView.findViewById(R.id.movieDirector)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.titleText.text = movie.title
        holder.directorText.text = holder.itemView.context.getString(
            R.string.directed_by,
            movie.director
        )
    }

    override fun getItemCount(): Int = movies.size

    fun addMovie(movie: Movie) {
        movies.add(movie)
        notifyItemInserted(movies.lastIndex)
    }
}
