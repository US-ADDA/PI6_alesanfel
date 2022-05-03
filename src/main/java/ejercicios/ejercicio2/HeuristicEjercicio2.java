package main.java.ejercicios.ejercicio2;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class HeuristicEjercicio2 {
	
	/**
	 * Suma la valoración de todos los candidatos que aún no han sido analizados.
	 * 
	 * @param source el vértice origen.
	 * @param goal   restricción que indica que no quedan más vértices por analizar.
	 * @param target el vértice destino.
	 * @return la valoración total menos los candidatos ya analizados.
	 */
	public static Double heuristic(VertexEjercicio2 source, Predicate<VertexEjercicio2> goal, VertexEjercicio2 target) {
		/*
		List<Integer> ls = IntStream.range(source.indice(), DataEjercicio2.getNumCandidatos()).boxed()
				.sorted(Comparator.comparing(
						i -> DataEjercicio2.getValoracion(i)/ DataEjercicio2.getSueldo(i)))
				.toList();
		
		Double h = 0.;
		Double presupuesto = DataEjercicio2.getPresupuestoRestante(source.candidatosSeleccionados());
		var f = 2;
		if (source.indice() == f) {
			System.out.println(presupuesto);
		}
		for (int p = 0; p < ls.size() && presupuesto != 0; p++) {
			Integer i = ls.get(p);
			Double action = Math.min(1., presupuesto/ DataEjercicio2.getSueldo(i));
			h += DataEjercicio2.getValoracion(i)*action;
			presupuesto -= DataEjercicio2.getSueldo(i)*action;
		}
		
		if (source.indice() == 15) {
			System.out.println(source.actions());
			System.out.println(source.candidatosSeleccionados());
			System.out.println(h);
		}
		return h;
		*/
		return IntStream.range(source.indice(), DataEjercicio2.getNumCandidatos())
				.map(DataEjercicio2::getValoracion)
				.sum()*1.0;
	}
	
	private HeuristicEjercicio2() {}
}
