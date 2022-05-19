package main.java.ejercicios.ejercicio3;

import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public record VertexEjercicio3(Integer id, Integer tiempoProduccionRestante,
                               Integer tiempoManualRestante) implements VirtualVertex<VertexEjercicio3, EdgeEjercicio3, Integer> {

    public static VertexEjercicio3 initialVertex() {
        return of(0, DataEjercicio3.getMaxTiempoEnProduccion(), DataEjercicio3.getMaxTiempoEnManual());
    }

    public static VertexEjercicio3 of(Integer id, Integer tiempoProduccionRestante, Integer tiempoManualRestante) {
        return new VertexEjercicio3(id, tiempoProduccionRestante, tiempoManualRestante);
    }

    public static Predicate<VertexEjercicio3> goal() {
        return v -> Objects.equals(v.id, DataEjercicio3.getNumProductos());
    }

    @Override
    public List<Integer> actions() {
        return (id >= DataEjercicio3.getNumProductos()) ?
                List2.empty() :
                IntStream.rangeClosed(0, DataEjercicio3.getRatioUnidades(id, tiempoProduccionRestante, tiempoManualRestante))
                        .boxed().toList();
    }

    @Override
    public VertexEjercicio3 neighbor(Integer a) {
        return of(id + 1,
                tiempoProduccionRestante - DataEjercicio3.getTiempoTotalProduccionProducto(id) * a,
                tiempoManualRestante - DataEjercicio3.getTiempoTotalManualProducto(id) * a);
    }

    @Override
    public EdgeEjercicio3 edge(Integer a) {
        return EdgeEjercicio3.of(this, neighbor(a), a);
    }
}
