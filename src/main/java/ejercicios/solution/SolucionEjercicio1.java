package main.java.ejercicios.solution;

import java.util.List;
import java.util.Map;

import org.jgrapht.GraphPath;

import main.java.ejercicios.classes.Fichero;
import main.java.ejercicios.classes.Memoria;
import main.java.ejercicios.data.DatosEjercicio1;
import main.java.ejercicios.edge.AristaEjercicio1;
import main.java.ejercicios.vertex.VerticeEjercicio1;
import us.lsi.common.List2;
import us.lsi.common.Map2;

public class SolucionEjercicio1 {
	
	private final Map<Memoria, List<Fichero>> memorias;
    private Integer numFicheros;
	
	public static SolucionEjercicio1 of(GraphPath<VerticeEjercicio1, AristaEjercicio1> path) {
		List<Integer> la = path.getEdgeList().stream().map(AristaEjercicio1::action).toList();
		return SolucionEjercicio1.of(la);
	}
	
	public static SolucionEjercicio1 of(List<Integer> ls) {
		return new SolucionEjercicio1(ls);
	}
	
	private SolucionEjercicio1(List<Integer> ls) {
		numFicheros = 0;
        memorias = Map2.empty();
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i) < DatosEjercicio1.getNumMemoria()) {
                numFicheros++;
                Memoria key = DatosEjercicio1.getMemoria(ls.get(i));
                Fichero value = DatosEjercicio1.getFichero(i);
                if (memorias.containsKey(key))
                    memorias.get(key).add(value);
                else
                    memorias.put(key, List2.of(value));
            }
        }
	}
	
	 @Override
	 public String toString() {
		 String cadenaMemorias = memorias.entrySet().stream()
				 .map(entry -> entry.getKey() + ": " + entry.getValue())
				 .reduce("", (ac, nx) -> String.format("%s%s%n", ac, nx));
		 return String.format("Reparto obtenido:%n%sN�mero de archivos:%s", cadenaMemorias, numFicheros);
	    }
}
