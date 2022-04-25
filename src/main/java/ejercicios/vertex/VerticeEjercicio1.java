package main.java.ejercicios.vertex;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import main.java.ejercicios.classes.Memoria;
import main.java.ejercicios.data.DatosEjercicio1;
import main.java.ejercicios.edge.AristaEjercicio1;
import us.lsi.graphs.virtual.VirtualVertex;

public record VerticeEjercicio1(Integer indice, List<Integer> capacidadRestante) implements VirtualVertex<VerticeEjercicio1, AristaEjercicio1, Integer>{

	public static VerticeEjercicio1 initialVertex() {
		return of(0, DatosEjercicio1.getMemorias().stream().map(Memoria::capacidad).toList());
	}
	
	public static VerticeEjercicio1 copy(VerticeEjercicio1 v) {
		return of(v.indice, v.capacidadRestante);
	}
	
	public static VerticeEjercicio1 of(Integer indice, List<Integer> capacidadRestante) {
		return new VerticeEjercicio1(indice, capacidadRestante);
	}
	
	public static VerticeEjercicio1 lastVertex() {
		return of(DatosEjercicio1.getNumFichero(), IntStream.range(0, DatosEjercicio1.getNumMemoria()).map(x -> 0).boxed().toList());
	}
	
	public static Predicate<VerticeEjercicio1> goal() {
		return v -> Objects.equals(v.indice, DatosEjercicio1.getNumFichero());
	}
	
	public static Predicate<VerticeEjercicio1> constraints() {
		return null;
	}
	
	@Override
	public Boolean isValid() {
		return indice >= 0 && indice <= DatosEjercicio1.getNumFichero();
	}
	
	@Override
	public List<Integer> actions() {
		// TODO
		return null;
	}

	@Override
	public VerticeEjercicio1 neighbor(Integer a) {
		// TODO
		return null;
	}

	@Override
	public AristaEjercicio1 edge(Integer a) {
		return AristaEjercicio1.of(this, neighbor(a), a, a*1.0);
	}
}
