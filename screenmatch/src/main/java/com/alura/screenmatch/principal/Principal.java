package com.alura.screenmatch.principal;

import com.alura.screenmatch.model.DatosEpisodio;
import com.alura.screenmatch.model.DatosSerie;
import com.alura.screenmatch.model.DatosTemporadas;
import com.alura.screenmatch.model.Episodio;
import com.alura.screenmatch.service.ConsumoAPI;
import com.alura.screenmatch.service.ConvierteDatos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

	private Scanner teclado = new Scanner(System.in);
	private ConsumoAPI consumoApi = new ConsumoAPI();
	//Una constante (final) se escribe en mayusculas
	private final String URL_BASE = "https://www.omdbapi.com/?t=";
	//esta key es solo local entonces por ahora no hay problema
	private final String KEY = "&apikey=34004e79";
	private ConvierteDatos conversor = new ConvierteDatos();

	public void mostrarMenu() {
		System.out.println("escriba el nombre de la serie que desea buscar:");
		//buscamos los datos generales de las series.
		var nombreSerie = teclado.nextLine();
		var nombreSinEspacios = nombreSerie.replace(" ", "%20");
		var json = consumoApi.obtenerDatos(URL_BASE +
				nombreSinEspacios +
				KEY);
		var datos = conversor.obtenerDatos(json, DatosSerie.class);
		System.out.println(datos);

//buscamos los datos de las temporadas
		List<DatosTemporadas> temporadas = new ArrayList<>();
		for (int i = 1; i <= datos.totalTemporadas(); i++) {
			json = consumoApi.obtenerDatos(URL_BASE +
					nombreSinEspacios + "&Season=" + i + KEY);
			var datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class);
			temporadas.add(datosTemporadas);
		}
//		temporadas.forEach(System.out::println);

		//Mostrar solo el titulo de los episodios para las temporadas.

//		for (int i = 0; i < datos.totalTemporadas(); i++) {
//			List<DatosEpisodio> episodiosTemporadas = temporadas.get(i).episodios();
//			for (int j = 0; j < episodiosTemporadas.size(); j++) {
//				System.out.println(episodiosTemporadas.get(j).titulo());
//			}
//		}
		//Esta es la forma equivalente simplificada del código anterior (funcion lamda)
//		temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

//Convertir la info a una lista de tipo DatosEpisodio
		List<DatosEpisodio> datosEpisodios = temporadas.stream()
				.flatMap(t -> t.episodios().stream()).collect(Collectors.toList());

		//top 5 episodios
		System.out.println("Episodios mejor puntuados: ");
		datosEpisodios.stream()
				.filter(t -> !t.puntuacion().equalsIgnoreCase("N/A"))
				.sorted(Comparator.comparing(DatosEpisodio::puntuacion).reversed())
				.limit(5).forEach(System.out::println);

		//Conversion de datos a una lista del tipo Episodio
		List<Episodio> episodios = temporadas.stream()
				.flatMap(t -> t.episodios().stream().map(d -> new Episodio(t.numeroDeTemporada(), d)))
				.collect(Collectors.toList());

//		episodios.forEach(System.out::println);

//		//busqueda de episodios por fechas
//		System.out.println("Digíta el año: ");
//		var fecha = teclado.nextInt();
//		teclado.nextLine();
//
//		LocalDate fechaBusqueda = LocalDate.of(fecha, 1, 1);
//
////Con esto le damos el formato que estamos acostumbrados en LatinoAmerica.
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//		episodios.stream()
//				.filter(e -> e.getFechaDeLanzamiento() != null && e.getFechaDeLanzamiento()
//						.isAfter(fechaBusqueda))
//				.forEach(e -> System.out.println(
//						"Temporada: [" + e.getTemporada() + "] " +
//								"Episodio: [" + e.getNumeroEpisodio() + "] " +
//								e.getTitulo() + " Fecha de lanzamiento: [" + e.getFechaDeLanzamiento().format(dtf) + "] "
//				));

		//Buscando episodios por partes del título.

//		System.out.println("Escribe el título del episodio a buscar: ");
//		var parteTitulo = teclado.nextLine();
//
//		Optional<Episodio> episodioABuscar = episodios.stream()
//				.filter(e -> e.getTitulo().toUpperCase().contains(parteTitulo.toUpperCase()))
//				.findFirst();
//		if (episodioABuscar.isPresent()){
//			System.out.println("Coincidencias encontradas: " + episodioABuscar.get());
//		}else {
//			System.out.println("No hay coincidencias.");
//		}

		//Cración de mapa de temporadas (evaluaciones)
		Map<Integer, Double> evaluacionesPorTemporada = episodios.stream()
				.filter(e -> e.getEvaluacion() > 0.0)
				.collect(Collectors.groupingBy(Episodio::getTemporada,
						Collectors.averagingDouble(Episodio::getEvaluacion)));
		System.out.println(evaluacionesPorTemporada);

//generación de estadisticas
		DoubleSummaryStatistics est = episodios.stream()
				.filter(e -> e.getEvaluacion() > 0.0).collect(Collectors.summarizingDouble(Episodio::getEvaluacion));

		System.out.println("Media de evaliuaciones: " + est.getAverage());
		System.out.println("Episodio mejor evaluado: " + est.getMax());
		System.out.println("Episodio peor puntuado: " + est.getMin());
		System.out.println("Total puntuaciones revisadas: " + est.getCount());

	}

}
