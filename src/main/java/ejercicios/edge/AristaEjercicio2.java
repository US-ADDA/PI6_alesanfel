package main.java.ejercicios.edge;

import main.java.ejercicios.vertex.VerticeEjercicio2;
import us.lsi.graphs.virtual.SimpleEdgeAction;

public record AristaEjercicio2(VerticeEjercicio2 source, VerticeEjercicio2 target, Integer action, Double weight)
		implements SimpleEdgeAction<VerticeEjercicio2, Integer>{
	
	public static AristaEjercicio2 of(VerticeEjercicio2 source, VerticeEjercicio2 target, Integer action, Double weight) {
		/*
		 * Modificar peso
		 */
		return new AristaEjercicio2(source, target, action, weight);
	}
}
