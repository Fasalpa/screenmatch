package com.alura.screenmatch.model;

public enum Categoria {

	ACCION("Action"),
	ROMANCE("Romance"),
	COMEDIA("Comedy"),
	DRAMA("drama"),
	CRIMEN("Crimen");

	private String categoriaOmdb;

	Categoria (String categoriaOmdb){
		this.categoriaOmdb = categoriaOmdb;
	}

}
