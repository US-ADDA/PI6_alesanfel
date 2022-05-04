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


public class TestAlgorithmLast<V extends VirtualVertex<V, E, Integer>, E extends SimpleEdgeAction<V, Integer>, S>
        extends TestAlgorithm<V, E, S> {


    private final Function<V, Double> vertexWeight;

    public TestAlgorithmLast(Consumer<String> initialData, Supplier<V> initialVertex, Predicate<V> goal,
                             TriFunction<V, Predicate<V>, V, Double> heuristic, Function<GraphPath<V, E>, S> solution,
                             Function<V, Double> vertexWeight, Predicate<V> constraints) {
        super(initialData, initialVertex, goal, heuristic, solution, constraints);
        this.vertexWeight = vertexWeight;
    }

    public static <V extends VirtualVertex<V, E, Integer>, E extends SimpleEdgeAction<V, Integer>, S>
    TestAlgorithmLast<V, E, S> of(Consumer<String> initialData, Supplier<V> initialVertex, Predicate<V> goal,
                                  TriFunction<V, Predicate<V>, V, Double> heuristic, Function<GraphPath<V, E>, S> solution, Function<V, Double> vertexWeight) {
        return new TestAlgorithmLast<>(initialData, initialVertex, goal, heuristic, solution, vertexWeight, null);
    }

    public static <V extends VirtualVertex<V, E, Integer>, E extends SimpleEdgeAction<V, Integer>, S>
    TestAlgorithmLast<V, E, S> of(Consumer<String> initialData, Supplier<V> initialVertex, Predicate<V> goal,
                                  TriFunction<V, Predicate<V>, V, Double> heuristic, Function<GraphPath<V, E>, S> solution, Function<V, Double> vertexWeight,
                                  Predicate<V> constraints) {
        return new TestAlgorithmLast<>(initialData, initialVertex, goal, heuristic, solution, vertexWeight, constraints);
    }

    @Override
    public EGraph<V, E> getGraph() {
        return (constraints == null) ?
                SimpleVirtualGraph.last(initialVertex.get(), goal, vertexWeight) :
                SimpleVirtualGraph.last(initialVertex.get(), goal, vertexWeight, constraints);
    }
}
