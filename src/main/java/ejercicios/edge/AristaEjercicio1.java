package main.java.ejercicios.edge;

import main.java.ejercicios.vertex.VerticeEjercicio1;
import us.lsi.graphs.virtual.SimpleEdgeAction;

public record AristaEjercicio1(VerticeEjercicio1 source, VerticeEjercicio1 target, Integer action, Double weight)
		implements SimpleEdgeAction<VerticeEjercicio1, Integer>{
	
	public static AristaEjercicio1 of(VerticeEjercicio1 source, VerticeEjercicio1 target, Integer action, Double weight) {
		/*
		 * Modificar peso
		 */
		return new AristaEjercicio1(source, target, action, weight);
	}
}
