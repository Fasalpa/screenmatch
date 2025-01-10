package com.alura.screenmatch.model;

public enum Categoria {

	ACCION("Action"),
	ROMANCE("Romance"),
	COMEDIA("Comedy"),
	DRAMA("drama"),
	CRIMEN("Crimen");

	private String categoriaOmdb;

	Categoria(String categoriaOmdb) {
		this.categoriaOmdb = categoriaOmdb;
	}

	public static Categoria fromString(String text) {
		for (Categoria categoria : Categoria.values()) {
			if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
				return categoria;
			}
		}
		throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
	}
}
