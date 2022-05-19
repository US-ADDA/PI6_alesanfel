package test.java.ejercicios;

import main.java.ejercicios.ejercicio3.*;
import test.java.tools.TestAlgorithmSum;

public class TestEjercicio3 {

    private static final String DATA_PATH = "data/PI6Ej3DatosEntrada";

    public static void main(String[] args) {

        String[] data = {DATA_PATH + 1 + ".txt", DATA_PATH + 2 + ".txt"};

        TestAlgorithmSum<VertexEjercicio3, EdgeEjercicio3, SolutionEjercicio3> algorithms = TestAlgorithmSum.of(
                DataEjercicio3::initialData,
                VertexEjercicio3::initialVertex,
                VertexEjercicio3.goal(),
                HeuristicEjercicio3::heuristic,
                SolutionEjercicio3::of);

        algorithms.testAStar(data);
        //algorithms.testBT(data);
        //algorithms.testPDR(data);
    }

    private TestEjercicio3() {
    }
}
