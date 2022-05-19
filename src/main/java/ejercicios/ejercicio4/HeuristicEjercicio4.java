package main.java.ejercicios.ejercicio4;

import java.util.function.Predicate;

public class HeuristicEjercicio4 {

    /**
     * Obtiene el número de contenedores que ya están llenos..
     *
     * @param source el vértice origen.
     * @param goal   restricción que indica que no quedan más vértices por analizar.
     * @param target el vértice destino.
     * @return número de contenedores llenos.
     */
    public static Double heuristic(VertexEjercicio4 source, Predicate<VertexEjercicio4> goal, VertexEjercicio4 target) {
        if(source.id()==DataEjercicio4.elementos.size()) return 0.;
        return Math.min(DataEjercicio4.contenedores.size()-DataEjercicio4.getNumeroContenedoresLLenos(source.capacidadRestante()),
                DataEjercicio4.elementos.size()-source.id())*1.0;
    }

    private HeuristicEjercicio4() {
    }
}
