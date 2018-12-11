package modelos;

/**
 *
 * @author Elias
 */
public class Fiscales_De_Obras {
    private int id_fiscaldeobra;
    private String nombre_fiscaldeobra;
    private String apellido_fiscaldeobra;
    private String ci_fiscaldeobra;
    private String telefono_fiscaldeobra;


    public Fiscales_De_Obras() {
    }

    public Fiscales_De_Obras(int id_fiscaldeobra, String nombre_fiscaldeobra, String apellido_fiscaldeobra, String ci_fiscaldeobra, String telefono_fiscaldeobra) {
        this.id_fiscaldeobra = id_fiscaldeobra;
        this.nombre_fiscaldeobra = nombre_fiscaldeobra;
        this.apellido_fiscaldeobra = apellido_fiscaldeobra;
        this.ci_fiscaldeobra = ci_fiscaldeobra;
        this.telefono_fiscaldeobra = telefono_fiscaldeobra;
    }

    public int getId_fiscaldeobra() {
        return id_fiscaldeobra;
    }

    public void setId_fiscaldeobra(int id_fiscaldeobra) {
        this.id_fiscaldeobra = id_fiscaldeobra;
    }

    public String getNombre_fiscaldeobra() {
        return nombre_fiscaldeobra;
    }

    public void setNombre_fiscaldeobra(String nombre_fiscaldeobra) {
        this.nombre_fiscaldeobra = nombre_fiscaldeobra;
    }

    public String getApellido_fiscaldeobra() {
        return apellido_fiscaldeobra;
    }

    public void setApellido_fiscaldeobra(String apellido_fiscaldeobra) {
        this.apellido_fiscaldeobra = apellido_fiscaldeobra;
    }

    public String getCi_fiscaldeobra() {
        return ci_fiscaldeobra;
    }

    public void setCi_fiscaldeobra(String ci_fiscaldeobra) {
        this.ci_fiscaldeobra = ci_fiscaldeobra;
    }

    public String getTelefono_fiscaldeobra() {
        return telefono_fiscaldeobra;
    }

    public void setTelefono_fiscaldeobra(String telefono_fiscaldeobra) {
        this.telefono_fiscaldeobra = telefono_fiscaldeobra;
    }

}


