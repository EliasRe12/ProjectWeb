package modelos;

import java.sql.Date;

/**
 *
 * @author Elias
 */
public class Ordenes_De_Trabajo {
    private int id_ordendetrabajo;
    private String descripcion_ordendetrabajo;
    private String estado_ordendetrabajo;
    private String direccion_ordendetrabajo;
    private Date fechainicio_ordendetrabajo;
    private Date fechaentrega_ordendetrabajo;
    private Fiscales_De_Obras fiscaldeobra;
    private Ciudades ciudad;    

    public Ordenes_De_Trabajo() {
    }

    public Ordenes_De_Trabajo(int id_ordendetrabajo, String estado_ordendetrabajo, String descripcion_ordendetrabajo, String direccion_ordendetrabajo, Date fechainicio_ordendetrabajo, Date fechaentrega_ordendetrabajo, Fiscales_De_Obras fiscaldeobra, Ciudades ciudad) {
        this.id_ordendetrabajo = id_ordendetrabajo;
        this.estado_ordendetrabajo = estado_ordendetrabajo;
        this.descripcion_ordendetrabajo = descripcion_ordendetrabajo;
        this.direccion_ordendetrabajo = direccion_ordendetrabajo;
        this.fechainicio_ordendetrabajo = fechainicio_ordendetrabajo;
        this.fechaentrega_ordendetrabajo = fechaentrega_ordendetrabajo;
        this.fiscaldeobra = fiscaldeobra;
        this.ciudad = ciudad;
    }

    public int getId_ordendetrabajo() {
        return id_ordendetrabajo;
    }

    public void setId_ordendetrabajo(int id_ordendetrabajo) {
        this.id_ordendetrabajo = id_ordendetrabajo;
    }

    public String getEstado_ordendetrabajo() {
        return estado_ordendetrabajo;
    }

    public void setEstado_ordendetrabajo(String estado_ordendetrabajo) {
        this.estado_ordendetrabajo = estado_ordendetrabajo;
    }

    public String getDescripcion_ordendetrabajo() {
        return descripcion_ordendetrabajo;
    }

    public void setDescripcion_ordendetrabajo(String descripcion_ordendetrabajo) {
        this.descripcion_ordendetrabajo = descripcion_ordendetrabajo;
    }

    public String getDireccion_ordendetrabajo() {
        return direccion_ordendetrabajo;
    }

    public void setDireccion_ordendetrabajo(String direccion_ordendetrabajo) {
        this.direccion_ordendetrabajo = direccion_ordendetrabajo;
    }

    public Date getFechainicio_ordendetrabajo() {
        return fechainicio_ordendetrabajo;
    }

    public void setFechainicio_ordendetrabajo(Date fechainicio_ordendetrabajo) {
        this.fechainicio_ordendetrabajo = fechainicio_ordendetrabajo;
    }

    public Date getFechaentrega_ordendetrabajo() {
        return fechaentrega_ordendetrabajo;
    }

    public void setFechaentrega_ordendetrabajo(Date fechaentrega_ordendetrabajo) {
        this.fechaentrega_ordendetrabajo = fechaentrega_ordendetrabajo;
    }

    public Fiscales_De_Obras getFiscaldeobra() {
        return fiscaldeobra;
    }

    public void setFiscaldeobra(Fiscales_De_Obras fiscaldeobra) {
        this.fiscaldeobra = fiscaldeobra;
    }

    public Ciudades getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudades ciudad) {
        this.ciudad = ciudad;
    }

}
