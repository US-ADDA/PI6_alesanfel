package main.java.ejercicios.ejercicio1;

import org.jgrapht.GraphPath;
import us.lsi.common.List2;
import us.lsi.common.Map2;

import java.util.List;
import java.util.Map;

public class SolutionEjercicio1 {

    private final Map<Memoria, List<Fichero>> memorias;
    private Integer numFicheros;

    public static SolutionEjercicio1 of(GraphPath<VertexEjercicio1, EdgeEjercicio1> path) {
        List<Integer> la = path.getEdgeList().stream().map(EdgeEjercicio1::action).toList();
        return SolutionEjercicio1.of(la);
    }

    public static SolutionEjercicio1 of(List<Integer> ls) {
        return new SolutionEjercicio1(ls);
    }

    private SolutionEjercicio1(List<Integer> ls) {
        numFicheros = 0;
        memorias = Map2.empty();
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i) < DataEjercicio1.getNumMemoria()) {
                numFicheros++;
                Memoria key = DataEjercicio1.getMemoria(ls.get(i));
                Fichero value = DataEjercicio1.getFichero(i);
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
        return String.format("Reparto obtenido:%n%sSe almacenaron %s archivos.", cadenaMemorias, numFicheros);
    }
}
