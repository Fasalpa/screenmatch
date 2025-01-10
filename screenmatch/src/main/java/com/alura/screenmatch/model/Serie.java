package com.alura.screenmatch.model;

import jakarta.persistence.*;

import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.OptionalDouble;

@Entity
@Table(name = "Series")

public class Serie {
	@Id
	//generar un autoincrementable
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//No queremos que hayan dos nombres iguales
	@Column(unique = true)
	private String titulo;
	private Integer totalTemporadas;
	private Double evaluacion;
	private String poster;
	@Enumerated(EnumType.STRING)
	private Categoria genero;
	private String actores;
	private String lenguaje;
	private String sinopsis;

	public Serie(DatosSerie datosSerie) {
		this.titulo = datosSerie.titulo();
		this.totalTemporadas = datosSerie.totalTemporadas();
		this.evaluacion = OptionalDouble.of(Double.valueOf(datosSerie.evaluacion())).orElse(0);
		this.poster = datosSerie.poster();
		this.genero = Categoria.fromString(datosSerie.genero().split(",")[0].trim());
		this.actores = datosSerie.actores();
		this.lenguaje = datosSerie.lenguaje();
		this.sinopsis = datosSerie.sinopsis();
	}

	@Override
	public String toString() {
		return
				"Genero: " + genero +
						", Titulo: '" + titulo + '\'' +
						", Sinopsis: '" + sinopsis + '\'' +
						", Total Temporadas: " + totalTemporadas +
						", Evaluacion: " + evaluacion +
						", Poster: " + poster + '\'' +
						", Actores: '" + actores + '\'' +
						", Lenguaje: '" + lenguaje + '\'';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getTotalTemporadas() {
		return totalTemporadas;
	}

	public void setTotalTemporadas(Integer totalTemporadas) {
		this.totalTemporadas = totalTemporadas;
	}

	public Double getEvaluacion() {
		return evaluacion;
	}

	public void setEvaluacion(Double evaluacion) {
		this.evaluacion = evaluacion;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public Categoria getGenero() {
		return genero;
	}

	public void setGenero(Categoria genero) {
		this.genero = genero;
	}

	public String getActores() {
		return actores;
	}

	public void setActores(String actores) {
		this.actores = actores;
	}

	public String getLenguaje() {
		return lenguaje;
	}

	public void setLenguaje(String lenguaje) {
		this.lenguaje = lenguaje;
	}
}
