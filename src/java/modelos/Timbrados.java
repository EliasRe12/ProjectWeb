package modelos;

import java.sql.Date;

/**
 *
 * @author Elias
 */
public class Timbrados {
    private int id_timbrado;
    private String numero_timbrado;
    private Date fechainicio_timbrado;
    private Date fechavencimiento_timbrado;
    private Date fechactual_timbrado;
    private int desde_timbrado;
    private int hasta_timbrado;

    public Timbrados() {
    }

    public Timbrados(int id_timbrado, String numero_timbrado, Date fechainicio_timbrado, Date fechavencimiento_timbrado, Date fechactual_timbrado, int desde_timbrado, int hasta_timbrado) {
        this.id_timbrado = id_timbrado;
        this.numero_timbrado = numero_timbrado;
        this.fechainicio_timbrado = fechainicio_timbrado;
        this.fechavencimiento_timbrado = fechavencimiento_timbrado;
        this.fechactual_timbrado = fechactual_timbrado;
        this.desde_timbrado = desde_timbrado;
        this.hasta_timbrado = hasta_timbrado;
    }

    public int getId_timbrado() {
        return id_timbrado;
    }

    public void setId_timbrado(int id_timbrado) {
        this.id_timbrado = id_timbrado;
    }

    public String getNumero_timbrado() {
        return numero_timbrado;
    }

    public void setNumero_timbrado(String numero_timbrado) {
        this.numero_timbrado = numero_timbrado;
    }

    public Date getFechainicio_timbrado() {
        return fechainicio_timbrado;
    }

    public void setFechainicio_timbrado(Date fechainicio_timbrado) {
        this.fechainicio_timbrado = fechainicio_timbrado;
    }

    public Date getFechavencimiento_timbrado() {
        return fechavencimiento_timbrado;
    }

    public void setFechavencimiento_timbrado(Date fechavencimiento_timbrado) {
        this.fechavencimiento_timbrado = fechavencimiento_timbrado;
    }

    public Date getFechactual_timbrado() {
        return fechactual_timbrado;
    }

    public void setFechactual_timbrado(Date fechactual_timbrado) {
        this.fechactual_timbrado = fechactual_timbrado;
    }

    public int getDesde_timbrado() {
        return desde_timbrado;
    }

    public void setDesde_timbrado(int desde_timbrado) {
        this.desde_timbrado = desde_timbrado;
    }

    public int getHasta_timbrado() {
        return hasta_timbrado;
    }

    public void setHasta_timbrado(int hasta_timbrado) {
        this.hasta_timbrado = hasta_timbrado;
    }

}
