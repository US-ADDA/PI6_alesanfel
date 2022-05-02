package main.java.ejercicios.ejercicio2;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record VertexEjercicio2(Integer indice, List<Integer> candidatosSeleccionados) implements VirtualVertex<VertexEjercicio2, EdgeEjercicio2, Integer> {

	public static VertexEjercicio2 initialVertex() {
		return of(0, List2.empty());
	}
	
	public static VertexEjercicio2 copy(VertexEjercicio2 v) {
		return of(v.indice, v.candidatosSeleccionados);
	}
	
	public static VertexEjercicio2 of(Integer indice, List<Integer> candidatosSeleccionados) {
		return new VertexEjercicio2(indice, candidatosSeleccionados);
	}
	
	public static VertexEjercicio2 lastVertex() {
		return of(DataEjercicio2.getNumCandidatos(), IntStream.range(0, DataEjercicio2.getNumCandidatos()).map(x -> 1).boxed().toList());
	}
	
	public static Predicate<VertexEjercicio2> goal() {
		return V -> V.indice == DataEjercicio2.getNumCandidatos();
	}
	
	public static Predicate<VertexEjercicio2> constraints() {
		// TODO
		return null;
	}
	
	@Override
	public Boolean isValid() {
		return indice >= 0 && indice <= DataEjercicio2.getNumCandidatos();
	}
	
	@Override
	public List<Integer> actions() {
		// TODO
		return null;
	}

	@Override
	public VertexEjercicio2 neighbor(Integer a) {
		// TODO
		return null;
	}

	@Override
	public EdgeEjercicio2 edge(Integer a) {
		return EdgeEjercicio2.of(this, neighbor(a), a, a*1.0);
	}	
}
