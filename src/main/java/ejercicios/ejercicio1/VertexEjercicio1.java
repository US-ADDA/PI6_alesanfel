package main.java.ejercicios.ejercicio1;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import main.java.ejercicios.classes.Fichero;
import main.java.ejercicios.classes.Memoria;
import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record VertexEjercicio1(Integer indice, List<Integer> capacidadRestante) implements VirtualVertex<VertexEjercicio1, EdgeEjercicio1, Integer>{

	public static VertexEjercicio1 initialVertex() {
		return of(0, DataEjercicio1.getMemorias().stream().map(Memoria::capacidad).toList());
	}
	
	public static VertexEjercicio1 of(Integer indice, List<Integer> capacidadRestante) {
		return new VertexEjercicio1(indice, capacidadRestante);
	}
	
	public static Predicate<VertexEjercicio1> goal() {
		return v -> Objects.equals(v.indice, DataEjercicio1.getNumFichero());
	}
	
	@Override
	public List<Integer> actions() {
		if (indice >= DataEjercicio1.getNumFichero()) {
			return List2.empty();
		}
			
		Fichero fichero = DataEjercicio1.getFichero(indice);
		List<Integer> acciones = List2.of(DataEjercicio1.getNumMemoria());
		for (var i=0; i < capacidadRestante.size(); i++ ) {
			var memoria = DataEjercicio1.getMemoria(i);
			// Debe de haber espacio en esa memoria y no superar el tamaño máximo permitido.
			if (fichero.capacidad() <= memoria.tamanoMaximo() && capacidadRestante.get(i) - fichero.capacidad() >= 0) {
				acciones.add(i);
			}	
		}
		return acciones;
	}

	@Override
	public VertexEjercicio1 neighbor(Integer a) {
		var auxCapacidadRestante = List2.copy(capacidadRestante); 
		if (!Objects.equals(a, DataEjercicio1.getNumMemoria())) {
			auxCapacidadRestante.set(a, capacidadRestante.get(a) - DataEjercicio1.getCapacidadFichero(indice));
		}
		return VertexEjercicio1.of(indice+1, auxCapacidadRestante);
	}

	@Override
	public EdgeEjercicio1 edge(Integer a) {
		return EdgeEjercicio1.of(this, neighbor(a), a);
	}
}
