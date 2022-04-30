package main.java.ejercicios.classes;

/**
 * El tipo correspondiente a un fichero que puede ser almacenada en una memoria.
 */
public record Fichero(String id, Integer capacidad) {

    /**
     * MÃ©todo de factorÃ­a de la clase {@code Fichero}.
     *
     * @param id        la clave primaria
     * @param capacidad la capacidad que ocupa el fichero en una memoria.
     * @return una instancia del tipo {@code Fichero}.
     */
    public static Fichero of(String id, Integer capacidad) {
        return new Fichero(id, capacidad);
    }

    /**
     * MÃ©todo para parsear un fichero siguiendo el siguiente criterio:
     * <ul>{@code id}: {@code capacidad}</ul>
     *
     * @param linea la lÃ­nea que va a ser parseada.
     * @return una instancia del tipo {@code Fichero}.
     */
    public static Fichero parse(String linea) {
        String id = linea.split(":")[0].trim();
        Integer capacidad = Integer.parseInt(linea.split(":")[1].trim());
        return of(id, capacidad);
    }
    
    @Override
    public String toString() {
    	return String.format("%s: %s", id, capacidad);
    }
}
