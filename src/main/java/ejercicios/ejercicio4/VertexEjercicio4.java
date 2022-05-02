package main.java.ejercicios.ejercicio4;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import us.lsi.graphs.virtual.VirtualVertex;

public record VertexEjercicio4(Integer indice, List<Integer> capacidadRestante) implements VirtualVertex<VertexEjercicio4, EdgeEjercicio4, Integer> {

	public static VertexEjercicio4 initialVertex() {
		return of(0, IntStream.range(0, DataEjercicio4.getNumContenedores()).boxed().map(DataEjercicio4::getCapacidadContenedor).toList());
	}
	
	public static VertexEjercicio4 copy(VertexEjercicio4 v) {
		return of(v.indice, v.capacidadRestante);
	}
	
	public static VertexEjercicio4 of(Integer indice, List<Integer> capacidadRestante) {
		return new VertexEjercicio4(indice, capacidadRestante);
	}
	
	public static VertexEjercicio4 lastVertex() {
		return of(DataEjercicio4.getNumElementos(), IntStream.range(0, DataEjercicio4.getNumContenedores()).boxed().map(x -> 0).toList());
	}
	
	public static Predicate<VertexEjercicio4> goal() {
		return v -> Objects.equals(v.indice, DataEjercicio4.getNumElementos());
	}
	
	public static Predicate<VertexEjercicio4> constraints() {
		// TODO
		return null;
	}
	
	@Override
	public Boolean isValid() {
		return indice >= 0 && indice <= DataEjercicio4.getNumElementos();
	} 
	
	@Override
	public List<Integer> actions() {
		// TODO
		return null;
	}

	@Override
	public VertexEjercicio4 neighbor(Integer a) {
		// TODO
		return null;
	}

	@Override
	public EdgeEjercicio4 edge(Integer a) {
		return EdgeEjercicio4.of(this, neighbor(a), a, a*1.0);
	}
}
