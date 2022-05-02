package main.java.ejercicios.ejercicio4;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record EdgeEjercicio4(VertexEjercicio4 source, VertexEjercicio4 target, Integer action, Double weight)
		implements SimpleEdgeAction<VertexEjercicio4, Integer>{
	
	public static EdgeEjercicio4 of(VertexEjercicio4 source, VertexEjercicio4 target, Integer action, Double weight) {
		/*
		 * Modificar peso
		 */
		return new EdgeEjercicio4(source, target, action, weight);
	}
}
