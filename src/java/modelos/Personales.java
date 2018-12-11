package modelos;

/**
 *
 * @author Elias
 */
public class Personales {
    private int id_personal;
    private String nombre_personal;
    private String apellido_personal;
    private String ci_personal;
    private String telefono_personal;
    
    public Personales() {
    }

    public Personales(int id_personal, String nombre_personal, String apellido_personal, String ci_personal, String telefono_personal) {
        this.id_personal = id_personal;
        this.nombre_personal = nombre_personal;
        this.apellido_personal = apellido_personal;
        this.ci_personal = ci_personal;
        this.telefono_personal = telefono_personal;
    }

    public int getId_personal() {
        return id_personal;
    }

    public void setId_personal(int id_personal) {
        this.id_personal = id_personal;
    }

    public String getNombre_personal() {
        return nombre_personal;
    }

    public void setNombre_personal(String nombre_personal) {
        this.nombre_personal = nombre_personal;
    }

    public String getApellido_personal() {
        return apellido_personal;
    }

    public void setApellido_personal(String apellido_personal) {
        this.apellido_personal = apellido_personal;
    }

    public String getCi_personal() {
        return ci_personal;
    }

    public void setCi_personal(String ci_personal) {
        this.ci_personal = ci_personal;
    }

    public String getTelefono_personal() {
        return telefono_personal;
    }

    public void setTelefono_personal(String telefono_personal) {
        this.telefono_personal = telefono_personal;
    }

}
