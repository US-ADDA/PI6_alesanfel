package main.java.ejercicios.ejercicio1;

import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record VertexEjercicio1(Integer id,
                               List<Integer> capacidadRestante) implements VirtualVertex<VertexEjercicio1, EdgeEjercicio1, Integer> {

    public static VertexEjercicio1 initialVertex() {
        return of(0, DataEjercicio1.getMemorias().stream().map(Memoria::capacidad).toList());
    }

    public static VertexEjercicio1 of(Integer id, List<Integer> capacidadRestante) {
        return new VertexEjercicio1(id, capacidadRestante);
    }

    public static Predicate<VertexEjercicio1> goal() {
        return v -> Objects.equals(v.id, DataEjercicio1.getNumFichero());
    }

    @Override
    public List<Integer> actions() {
        // Si estamos en el último fichero, no se puede realizar ninguna acción.
        if (Objects.equals(id, DataEjercicio1.getNumFichero()))
            return List2.empty();
        List<Integer> acciones = IntStream.range(0, capacidadRestante.size())
                // Debe de haber espacio en esa memoria y no superar el tamaño máximo permitido.
                .filter(j -> DataEjercicio1.ficheroEnMemoria(id, j, capacidadRestante))
                .boxed().collect(Collectors.toList());
        // El fichero puede no ser almacenado en una memoria.
        acciones.add(DataEjercicio1.getNumMemoria());
        return acciones;
    }

    @Override
    public VertexEjercicio1 neighbor(Integer a) {
        var auxCapacidadRestante = List2.copy(capacidadRestante);
        // Comprobamos que el fichero se ha colocado en una memoria y si lo está, disminuimos la capacidad de la memoria correspondiente.
        if (!Objects.equals(a, DataEjercicio1.getNumMemoria()))
            auxCapacidadRestante.set(a, capacidadRestante.get(a) - DataEjercicio1.getCapacidadFichero(id));
        return VertexEjercicio1.of(id + 1, auxCapacidadRestante);
    }

    @Override
    public EdgeEjercicio1 edge(Integer a) {
        return EdgeEjercicio1.of(this, neighbor(a), a);
    }
}
