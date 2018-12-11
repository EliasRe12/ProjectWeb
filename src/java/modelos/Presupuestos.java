package modelos;

import java.sql.Date;

/**
 *
 * @author Elias
 */
public class Presupuestos {
    private int id_presupuesto;
    private Date fecha_presupuesto;
    private Clientes cliente;

    
    public Presupuestos() {
    }

    public Presupuestos(int id_presupuesto, Date fecha_presupuesto, Clientes cliente) {
        this.id_presupuesto = id_presupuesto;
        this.fecha_presupuesto = fecha_presupuesto;
        this.cliente = cliente;
    }

    public int getId_presupuesto() {
        return id_presupuesto;
    }

    public void setId_presupuesto(int id_presupuesto) {
        this.id_presupuesto = id_presupuesto;
    }

    public Date getFecha_presupuesto() {
        return fecha_presupuesto;
    }

    public void setFecha_presupuesto(Date fecha_presupuesto) {
        this.fecha_presupuesto = fecha_presupuesto;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }
    
}
