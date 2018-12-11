package modelos;

import java.util.logging.Logger;

/**
 *
 * @author Elias
 */
public class Presupuestos_Detalles {
    private int id_presupuestodetalle;
    private String detalle_presupuestodetalle;
    private int cantidad_presupuestodetalle;
    private int precio_presupuestodetalle;
    private String ancho_presupuestodetalle;
    private String alto_presupuestodetalle;
    private String m2_presupuestodetalle;
    private int mm_presupuestodetalle;
    private String color_presupuestodetalle;
    private Presupuestos presupuesto;
    private Ubicaciones ubicacion;
    private Sectores sector;
    
        
    public Presupuestos_Detalles() {
    }

    public Presupuestos_Detalles(int id_presupuestodetalle, String detalle_presupuestodetalle, int cantidad_presupuestodetalle, int precio_presupuestodetalle, String ancho_presupuestodetalle, String alto_presupuestodetalle, String m2_presupuestodetalle, int mm_presupuestodetalle, String color_presupuestodetalle, Presupuestos presupuesto, Ubicaciones ubicacion, Sectores sector) {
        this.id_presupuestodetalle = id_presupuestodetalle;
        this.detalle_presupuestodetalle = detalle_presupuestodetalle;
        this.cantidad_presupuestodetalle = cantidad_presupuestodetalle;
        this.precio_presupuestodetalle = precio_presupuestodetalle;
        this.ancho_presupuestodetalle = ancho_presupuestodetalle;
        this.alto_presupuestodetalle = alto_presupuestodetalle;
        this.m2_presupuestodetalle = m2_presupuestodetalle;
        this.mm_presupuestodetalle = mm_presupuestodetalle;
        this.color_presupuestodetalle = color_presupuestodetalle;
        this.presupuesto = presupuesto;
        this.ubicacion = ubicacion;
        this.sector = sector;
    }

    public int getId_presupuestodetalle() {
        return id_presupuestodetalle;
    }

    public void setId_presupuestodetalle(int id_presupuestodetalle) {
        this.id_presupuestodetalle = id_presupuestodetalle;
    }

    public String getDetalle_presupuestodetalle() {
        return detalle_presupuestodetalle;
    }

    public void setDetalle_presupuestodetalle(String detalle_presupuestodetalle) {
        this.detalle_presupuestodetalle = detalle_presupuestodetalle;
    }

    public int getCantidad_presupuestodetalle() {
        return cantidad_presupuestodetalle;
    }

    public void setCantidad_presupuestodetalle(int cantidad_presupuestodetalle) {
        this.cantidad_presupuestodetalle = cantidad_presupuestodetalle;
    }

    public int getPrecio_presupuestodetalle() {
        return precio_presupuestodetalle;
    }

    public void setPrecio_presupuestodetalle(int precio_presupuestodetalle) {
        this.precio_presupuestodetalle = precio_presupuestodetalle;
    }

    public String getAncho_presupuestodetalle() {
        return ancho_presupuestodetalle;
    }

    public void setAncho_presupuestodetalle(String ancho_presupuestodetalle) {
        this.ancho_presupuestodetalle = ancho_presupuestodetalle;
    }

    public String getAlto_presupuestodetalle() {
        return alto_presupuestodetalle;
    }

    public void setAlto_presupuestodetalle(String alto_presupuestodetalle) {
        this.alto_presupuestodetalle = alto_presupuestodetalle;
    }

    public String getM2_presupuestodetalle() {
        return m2_presupuestodetalle;
    }

    public void setM2_presupuestodetalle(String m2_presupuestodetalle) {
        this.m2_presupuestodetalle = m2_presupuestodetalle;
    }

    public int getMm_presupuestodetalle() {
        return mm_presupuestodetalle;
    }

    public void setMm_presupuestodetalle(int mm_presupuestodetalle) {
        this.mm_presupuestodetalle = mm_presupuestodetalle;
    }

    public String getColor_presupuestodetalle() {
        return color_presupuestodetalle;
    }

    public void setColor_presupuestodetalle(String color_presupuestodetalle) {
        this.color_presupuestodetalle = color_presupuestodetalle;
    }

    public Presupuestos getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuestos presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Ubicaciones getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicaciones ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Sectores getSector() {
        return sector;
    }

    public void setSector(Sectores sector) {
        this.sector = sector;
    }

}

