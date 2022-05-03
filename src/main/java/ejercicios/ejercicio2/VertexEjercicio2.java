package main.java.ejercicios.ejercicio2;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record VertexEjercicio2(Integer indice, List<Integer> candidatosSeleccionados) implements VirtualVertex<VertexEjercicio2, EdgeEjercicio2, Integer> {

	public static VertexEjercicio2 initialVertex() {
		return of(0, List2.empty());
	}
	
	public static VertexEjercicio2 of(Integer indice, List<Integer> candidatosSeleccionados) {
		return new VertexEjercicio2(indice, candidatosSeleccionados);
	}
	
	public static Predicate<VertexEjercicio2> goal() {
		return v -> Objects.equals(v.indice, DataEjercicio2.getNumCandidatos());
	}
	
	public static Predicate<VertexEjercicio2> constraints() {
		// La solución correcta debe de cubrir las cualidades deseadas.
		return v -> DataEjercicio2.getCualidadesACubrir(v.candidatosSeleccionados).isEmpty();
	}
	
	
	
	@Override
	public List<Integer> actions() {
		// Si estamos en el último candidato, no se puede realizar ninguna acción.
		if (Objects.equals(indice, DataEjercicio2.getNumCandidatos())) 
			return List2.empty();
		// No se pueden contratar candidatos incompatibles.
		for (var i: candidatosSeleccionados) {
			if (Boolean.TRUE.equals(DataEjercicio2.esIncompatible(i, indice)))
				return List.of(0);
		}
		// No se puede superar el presupuesto.
		return DataEjercicio2.getSueldo(indice) <= DataEjercicio2.getPresupuestoRestante(candidatosSeleccionados) ?
				List.of(0,1):
				List.of(0);
	}
	
	

	@Override
	public VertexEjercicio2 neighbor(Integer a) {
		List<Integer> auxCandidatosSeleccionados = List2.copy(candidatosSeleccionados);
		// Comprobamos que el candidato ha sido contratado.
		if (a == 1) 
			auxCandidatosSeleccionados.add(indice);
		return VertexEjercicio2.of(indice+1, auxCandidatosSeleccionados);
	}

	@Override
	public EdgeEjercicio2 edge(Integer a) {
		return EdgeEjercicio2.of(this, neighbor(a), a);
	}	
}
