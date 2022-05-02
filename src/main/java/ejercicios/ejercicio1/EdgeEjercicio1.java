package main.java.ejercicios.ejercicio1;

import java.util.Objects;

import us.lsi.graphs.virtual.SimpleEdgeAction;
import us.lsi.hypergraphs2.Datos;

public record EdgeEjercicio1(VertexEjercicio1 source, VertexEjercicio1 target, Integer action, Double weight)
		implements SimpleEdgeAction<VertexEjercicio1, Integer>{
	
	public static EdgeEjercicio1 of(VertexEjercicio1 source, VertexEjercicio1 target, Integer action) {
		return new EdgeEjercicio1(source, target, action, Objects.equals(action, DataEjercicio1.getNumMemoria()) ? 0.: 1.);
	}
}
