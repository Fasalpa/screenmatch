package com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosEpisodio(@JsonAlias ("Title") String titulo,
														@JsonAlias ("Episode") Integer numeroEpisodio,
                            @JsonAlias("Runtime") String duracion,
                            @JsonAlias ("imdbRating") String puntuacion,
@JsonAlias("Released") String fechaDeLanzamiento ){
}
