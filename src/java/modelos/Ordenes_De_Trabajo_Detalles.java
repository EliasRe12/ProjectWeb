package modelos;

/**
 *
 * @author Elias
 */
public class Ordenes_De_Trabajo_Detalles {
    private int id_ordendetrabajodetalle;
    private Ordenes_De_Trabajo ordendetrabajo;
    private Personales personal;
    
    
    public Ordenes_De_Trabajo_Detalles() {
    }

    public Ordenes_De_Trabajo_Detalles(int id_ordendetrabajodetalle, Ordenes_De_Trabajo ordendetrabajo, Personales personal) {
        this.id_ordendetrabajodetalle = id_ordendetrabajodetalle;
        this.ordendetrabajo = ordendetrabajo;
        this.personal = personal;
    }

    public int getId_ordendetrabajodetalle() {
        return id_ordendetrabajodetalle;
    }

    public void setId_ordendetrabajodetalle(int id_ordendetrabajodetalle) {
        this.id_ordendetrabajodetalle = id_ordendetrabajodetalle;
    }

    public Ordenes_De_Trabajo getOrdendetrabajo() {
        return ordendetrabajo;
    }

    public void setOrdendetrabajo(Ordenes_De_Trabajo ordendetrabajo) {
        this.ordendetrabajo = ordendetrabajo;
    }

    public Personales getPersonal() {
        return personal;
    }

    public void setPersonal(Personales personal) {
        this.personal = personal;
    }

}
