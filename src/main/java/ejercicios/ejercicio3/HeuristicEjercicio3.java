package main.java.ejercicios.ejercicio3;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class HeuristicEjercicio3 {
	public static Double heuristic(VertexEjercicio3 source, Predicate<VertexEjercicio3> goal, VertexEjercicio3 target) {
		
		/*
		if (source.indice() == 1) System.out.println((Objects.equals(source.indice(), DataEjercicio3.getNumProductos())) ? 0.:
			IntStream.range(source.indice(), DataEjercicio3.getNumProductos()).boxed()
			.mapToDouble(i -> DataEjercicio3.posibleNumProductos(i, source.tiempoProduccionRestante(), source.tiempoManualRestante()))
			.sum());
		*/
		return (Objects.equals(source.indice(), DataEjercicio3.getNumProductos())) ? 0.:
				IntStream.range(source.indice(), DataEjercicio3.getNumProductos()).boxed()
				.mapToDouble(i -> DataEjercicio3.posibleNumProductos(i, source.tiempoProduccionRestante(), source.tiempoManualRestante()))
				.sum();
	}

	
}
