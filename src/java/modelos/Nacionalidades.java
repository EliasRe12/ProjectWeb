package modelos;

/**
 *
 * @author Elias
 */
public class Nacionalidades {
    private int id_nacionalidad;
    private String nombre_nacionalidad;

    public Nacionalidades() {
    }

    public Nacionalidades(int id_nacionalidad, String nombre_nacionalidad) {
        this.id_nacionalidad = id_nacionalidad;
        this.nombre_nacionalidad = nombre_nacionalidad;
    }

    public int getId_nacionalidad() {
        return id_nacionalidad;
    }

    public void setId_nacionalidad(int id_nacinalidad) {
        this.id_nacionalidad = id_nacinalidad;
    }

    public String getNombre_nacionalidad() {
        return nombre_nacionalidad;
    }

    public void setNombre_nacionalidad(String nombre_nacionalidad) {
        this.nombre_nacionalidad = nombre_nacionalidad;
    }
}
