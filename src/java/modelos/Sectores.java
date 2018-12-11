package modelos;

/**
 *
 * @author Elias
 */
public class Sectores {
    private int id_sector;
    private String nombre_sector;

    public Sectores() {
    }

    public Sectores(int id_sector, String nombre_sector) {
        this.id_sector = id_sector;
        this.nombre_sector = nombre_sector;
    }

    public int getId_sector() {
        return id_sector;
    }

    public void setId_sector(int id_sector) {
        this.id_sector = id_sector;
    }

    public String getNombre_sector() {
        return nombre_sector;
    }

    public void setNombre_sector(String nombre_sector) {
        this.nombre_sector = nombre_sector;
    }
    
}
