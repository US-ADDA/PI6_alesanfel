package main.java.ejercicios.ejercicio1;

import java.util.function.Predicate;

public class HeuristicEjercicio1 {

	public static Double heuristic(VertexEjercicio1 source, Predicate<VertexEjercicio1> goal, VertexEjercicio1 target) {
		return (DataEjercicio1.getNumFichero() - source.indice())*1.0;
	}
}
