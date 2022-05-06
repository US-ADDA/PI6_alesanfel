package test.java.ejercicios;

import main.java.ejercicios.ejercicio4.*;
import test.java.tools.TestAlgorithmLast;

public class TestEjercicio4 {

    private static final String DATA_PATH = "data/PI6Ej4DatosEntrada";

    public static void main(String[] args) {

        String[] data = {DATA_PATH + 1 + ".txt", DATA_PATH + 2 + ".txt"};

        TestAlgorithmLast<VertexEjercicio4, EdgeEjercicio4, SolutionEjercicio4> algorithms = TestAlgorithmLast.of(
                DataEjercicio4::initialData,
                VertexEjercicio4::initialVertex,
                VertexEjercicio4.goal(),
                HeuristicEjercicio4::heuristic,
                SolutionEjercicio4::of,
                VertexEjercicio4::weight,
                VertexEjercicio4.constraint());

        algorithms.testAStar(data);
        algorithms.testBT(data);
        algorithms.testPDR(data);
    }

    private TestEjercicio4() {
    }
}
