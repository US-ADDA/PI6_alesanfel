package main.java.ejercicios.edge;

import main.java.ejercicios.vertex.VerticeEjercicio4;
import us.lsi.graphs.virtual.SimpleEdgeAction;

public record AristaEjercicio4(VerticeEjercicio4 source, VerticeEjercicio4 target, Integer action, Double weight)
		implements SimpleEdgeAction<VerticeEjercicio4, Integer>{
	
	public static AristaEjercicio4 of(VerticeEjercicio4 source, VerticeEjercicio4 target, Integer action, Double weight) {
		/*
		 * Modificar peso
		 */
		return new AristaEjercicio4(source, target, action, weight);
	}
}
