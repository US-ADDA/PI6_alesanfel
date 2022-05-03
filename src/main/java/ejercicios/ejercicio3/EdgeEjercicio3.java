package main.java.ejercicios.ejercicio3;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record EdgeEjercicio3(VertexEjercicio3 source, VertexEjercicio3 target, Integer action, Double weight)
		implements SimpleEdgeAction<VertexEjercicio3, Integer>{
	
	public static EdgeEjercicio3 of(VertexEjercicio3 source, VertexEjercicio3 target, Integer action) {
		return new EdgeEjercicio3(source, target, action, DataEjercicio3.getIngresos(source.indice())*action*1.0);
	}
}