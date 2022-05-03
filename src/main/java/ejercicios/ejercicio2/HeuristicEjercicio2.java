package main.java.ejercicios.ejercicio2;

import java.util.List;
import java.util.function.Predicate;

import us.lsi.common.List2;

public class HeuristicEjercicio2 {
	public static Double heuristic(VertexEjercicio2 source, Predicate<VertexEjercicio2> goal, VertexEjercicio2 target) {
		
		/*
		if (source.indice() == 3) {
			System.out.println(source.candidatosSeleccionados());
			System.out.println(DataEjercicio2.getCualidadesACubrir(source.candidatosSeleccionados()));
		}
		*/
		/*
		if (DataEjercicio2.getCualidadesACubrir(source.candidatosSeleccionados()).isEmpty()) {
			if (source.indice() == 3) {
				//System.out.println(source.candidatosSeleccionados());
			}
			return 0.;
		}
		*/
		Double h = 0.;
		for (int i=source.indice(); i<DataEjercicio2.getNumCandidatos(); i++) {
			Integer valor = DataEjercicio2.getValoracion(i);
			h += valor*1.;
		}
		
		return h;
	}
}
