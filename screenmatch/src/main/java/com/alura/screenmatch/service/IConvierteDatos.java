package com.alura.screenmatch.service;

public interface IConvierteDatos {
	//este <T> T es un tipo de dato generico (puede retornarme una cosa u otra)
	<T> T obtenerDatos(String json, Class<T> clase);
}
