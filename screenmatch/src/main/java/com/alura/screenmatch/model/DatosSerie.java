package com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosSerie(@JsonAlias("Title") String titulo,
                         @JsonAlias("totalSeasons") Integer totalTemporadas,
                         @JsonAlias("imdbRating") String evaluacion,
                         @JsonAlias("Genre") String genero,
												 @JsonAlias("Plot") String sinopsis,
												 @JsonAlias("Actors") String actores,
                         @JsonAlias("Language") String lenguaje,
                         @JsonAlias("Poster") String poster
) {
	@Override
	public String toString() {
		return "DatosSerie: " +
				"titulo: '" + titulo + '\'' +
				", totalTemporadas: " + totalTemporadas +
				", evaluacion: '" + evaluacion + '\'' +
				", genero: '" + genero + '\'' +
				", sinopsis: '" + sinopsis + '\'' +
				", actores: '" + actores + '\'' +
				", lenguaje: '" + lenguaje + '\'' +
				", poster: '" + poster + '\'';
	}
}
