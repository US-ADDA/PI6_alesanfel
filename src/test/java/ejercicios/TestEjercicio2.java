package test.java.ejercicios;

import java.util.Locale;

import main.java.ejercicios.ejercicio2.*;
import test.java.tools.TestAlgorithmSum;

public class TestEjercicio2 {

    private static final String DATA_PATH = "data/PI6Ej2DatosEntrada";

    public static void main(String[] args) {
    	
    	Locale.setDefault(new Locale("en", "US"));

        String[] data = {DATA_PATH + 1 + ".txt", DATA_PATH + 2 + ".txt"};

        TestAlgorithmSum<VertexEjercicio2, EdgeEjercicio2, SolutionEjercicio2> algorithms = TestAlgorithmSum.of(
                DataEjercicio2::initialData,
                VertexEjercicio2::initialVertex,
                VertexEjercicio2.goal(),
                HeuristicEjercicio2::heuristic,
                SolutionEjercicio2::of,
                VertexEjercicio2.constraints());

        //algorithms.testAStar(data);
        //algorithms.testBT(data);
        algorithms.testPDR(data);
    }

    private TestEjercicio2() {
    }
}
