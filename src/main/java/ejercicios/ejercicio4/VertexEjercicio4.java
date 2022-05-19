package main.java.ejercicios.ejercicio4;

import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record VertexEjercicio4(Integer id,List<Integer> capacidadRestante) implements VirtualVertex<VertexEjercicio4, EdgeEjercicio4, Integer> {

    public static VertexEjercicio4 initialVertex() {
        return VertexEjercicio4.of(0, DataEjercicio4.contenedores.stream().map(Contenedor::capacidad).toList());
    }

    public static VertexEjercicio4 of(Integer id, List<Integer> capacidades) {
        return new VertexEjercicio4(id,capacidades);
    }

    public static Predicate<VertexEjercicio4> goal() {
        return v -> Objects.equals(v.id(), DataEjercicio4.elementos.size());
    }

    @Override
    public List<Integer> actions() {
        // Si estamos en el último elemento, no se puede realizar ninguna acción.
        if(Objects.equals(id, DataEjercicio4.getNumElementos()))
            return List2.empty();


        List<Integer> acciones = IntStream.range(0, DataEjercicio4.getNumContenedores())
                // Para cada elemento y para cada contenedor, sólo se puede ubicar en caso de que esté permitido acorde a sus tipos.
                .filter(contenedor -> DataEjercicio4.elementoEnContenedor(id, contenedor, capacidadRestante))
                .boxed().collect(Collectors.toList());
        // El elemento puede no ser almacenado en un contenedor.
        acciones.add(0, DataEjercicio4.contenedores.size());
        return acciones;
    }

    @Override
    public VertexEjercicio4 neighbor(Integer a) {

        List<Integer> auxCapacidadRestante= List2.copy(capacidadRestante);
        // Comprobamos que el elemento se ha colocado en un contenedor y si lo está, disminuimos la capacidad del contenedor correspondiente.
        if (!Objects.equals(a, DataEjercicio4.getNumContenedores()))
            auxCapacidadRestante.set(a, capacidadRestante.get(a) - DataEjercicio4.getTamanoElemento(id));
        return of(id + 1, auxCapacidadRestante);
    }

    @Override
    public EdgeEjercicio4 edge(Integer a) {
        return EdgeEjercicio4.of(this,neighbor(a),a);
    }
}