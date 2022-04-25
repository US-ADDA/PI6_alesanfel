package main.java.ejercicios.vertex;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import main.java.ejercicios.data.DatosEjercicio2;
import main.java.ejercicios.edge.AristaEjercicio2;
import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record VerticeEjercicio2(Integer indice, List<Integer> candidatosSeleccionados) implements VirtualVertex<VerticeEjercicio2, AristaEjercicio2, Integer> {

	public static VerticeEjercicio2 initialVertex() {
		return of(0, List2.empty());
	}
	
	public static VerticeEjercicio2 copy(VerticeEjercicio2 v) {
		return of(v.indice, v.candidatosSeleccionados);
	}
	
	public static VerticeEjercicio2 of(Integer indice, List<Integer> candidatosSeleccionados) {
		return new VerticeEjercicio2(indice, candidatosSeleccionados);
	}
	
	public static VerticeEjercicio2 lastVertex() {
		return of(DatosEjercicio2.getNumCandidatos(), IntStream.range(0, DatosEjercicio2.getNumCandidatos()).map(x -> 1).boxed().toList());
	}
	
	public static Predicate<VerticeEjercicio2> goal() {
		return V -> V.indice == DatosEjercicio2.getNumCandidatos();
	}
	
	public static Predicate<VerticeEjercicio2> constraints() {
		// TODO
		return null;
	}
	
	@Override
	public Boolean isValid() {
		return indice >= 0 && indice <= DatosEjercicio2.getNumCandidatos();
	}
	
	@Override
	public List<Integer> actions() {
		// TODO
		return null;
	}

	@Override
	public VerticeEjercicio2 neighbor(Integer a) {
		// TODO
		return null;
	}

	@Override
	public AristaEjercicio2 edge(Integer a) {
		return AristaEjercicio2.of(this, neighbor(a), a, a*1.0);
	}	
}
