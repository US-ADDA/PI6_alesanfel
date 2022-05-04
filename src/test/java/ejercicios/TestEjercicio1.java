package test.java.ejercicios;

import main.java.ejercicios.ejercicio1.EdgeEjercicio1;
import main.java.ejercicios.ejercicio1.DataEjercicio1;
import main.java.ejercicios.ejercicio1.HeuristicEjercicio1;
import main.java.ejercicios.ejercicio1.SolutionEjercicio1;
import main.java.ejercicios.ejercicio1.VertexEjercicio1;
import test.java.tools.TestAlgorithmSum;

public class TestEjercicio1 {

    private static final String DATA_PATH = "data/PI6Ej1DatosEntrada";

    public static void main(String[] args) {

        String[] data = {DATA_PATH + 1 + ".txt", DATA_PATH + 2 + ".txt"};

        TestAlgorithmSum<VertexEjercicio1, EdgeEjercicio1, SolutionEjercicio1> algorithms = TestAlgorithmSum.of(
                DataEjercicio1::initialData,
                VertexEjercicio1::initialVertex,
                VertexEjercicio1.goal(),
                HeuristicEjercicio1::heuristic,
                SolutionEjercicio1::of);

        algorithms.testAStar(data);
        algorithms.testBT(data);
        algorithms.testPDR(data);
    }
}
