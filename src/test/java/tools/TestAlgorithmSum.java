package test.java.tools;

import org.jgrapht.GraphPath;
import us.lsi.common.TriFunction;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.SimpleEdgeAction;
import us.lsi.graphs.virtual.SimpleVirtualGraph;
import us.lsi.graphs.virtual.VirtualVertex;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Definimos el peso del camino como tipo Last (peso del camino viene dado por una propiedad del último vértice).
 *
 * @param <V> tipo de los vértices
 * @param <E> tipo de las aristas
 * @param <S> tipo de la solución devuelta por el algoritmo.
 */
public class TestAlgorithmSum<V extends VirtualVertex<V, E, Integer>, E extends SimpleEdgeAction<V, Integer>, S>
        extends TestAlgorithm<V, E, S> {

    private TestAlgorithmSum(Consumer<String> initialData, Supplier<V> initialVertex, Predicate<V> goal,
                             TriFunction<V, Predicate<V>, V, Double> heuristic, Function<GraphPath<V, E>, S> solution,
                             Predicate<V> constraints) {
        super(initialData, initialVertex, goal, heuristic, solution, constraints);
    }

    public static <V extends VirtualVertex<V, E, Integer>, E extends SimpleEdgeAction<V, Integer>, S>
    TestAlgorithmSum<V, E, S> of(Consumer<String> initialData, Supplier<V> initialVertex, Predicate<V> goal,
                                 TriFunction<V, Predicate<V>, V, Double> heuristic, Function<GraphPath<V, E>, S> solution) {
        return new TestAlgorithmSum<>(initialData, initialVertex, goal, heuristic, solution, null);
    }

    public static <V extends VirtualVertex<V, E, Integer>, E extends SimpleEdgeAction<V, Integer>, S>
    TestAlgorithmSum<V, E, S> of(Consumer<String> initialData, Supplier<V> initialVertex, Predicate<V> goal,
                                 TriFunction<V, Predicate<V>, V, Double> heuristic, Function<GraphPath<V, E>, S> solution, Predicate<V> constraints) {
        return new TestAlgorithmSum<>(initialData, initialVertex, goal, heuristic, solution, constraints);
    }

    @Override
    protected EGraph<V, E> getGraph() {
        return (constraints == null) ?
                SimpleVirtualGraph.sum(initialVertex.get(), goal, SimpleEdgeAction::weight) :
                SimpleVirtualGraph.sum(initialVertex.get(), goal, SimpleEdgeAction::weight, constraints);
    }
}
