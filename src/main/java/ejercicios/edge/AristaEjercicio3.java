package main.java.ejercicios.edge;

import main.java.ejercicios.vertex.VerticeEjercicio3;
import us.lsi.graphs.virtual.SimpleEdgeAction;

public record AristaEjercicio3(VerticeEjercicio3 source, VerticeEjercicio3 target, Integer action, Double weight)
		implements SimpleEdgeAction<VerticeEjercicio3, Integer>{
	
	public static AristaEjercicio3 of(VerticeEjercicio3 source, VerticeEjercicio3 target, Integer action, Double weight) {
		/*
		 * Modificar peso
		 */
		return new AristaEjercicio3(source, target, action, weight);
	}
}
