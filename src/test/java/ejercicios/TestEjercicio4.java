package test.java.ejercicios;

import main.java.ejercicios.ejercicio4.*;
import test.java.tools.TestAlgorithmLast;
import test.java.tools.TestAlgorithmSum;

public class TestEjercicio4 {

    private static final String DATA_PATH = "data/PI6Ej4DatosEntrada";

    public static void main(String[] args) {

        String[] data = {DATA_PATH + 1 + ".txt", DATA_PATH + 2 + ".txt"};

        TestAlgorithmSum<VertexEjercicio4, EdgeEjercicio4, SolutionEjercicio4> algorithms = TestAlgorithmSum.of(
                DataEjercicio4::initialData,
                VertexEjercicio4::initialVertex,
                VertexEjercicio4.goal(),
                HeuristicEjercicio4::heuristic,
                SolutionEjercicio4::of);

        algorithms.testAStar(data);
        algorithms.testBT(data);
        algorithms.testPDR(data);
    }

    private TestEjercicio4() {
    }
}
