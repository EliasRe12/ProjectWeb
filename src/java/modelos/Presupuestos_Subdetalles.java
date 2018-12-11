package modelos;

/**
 *
 * @author Elias
 */
public class Presupuestos_Subdetalles {
    private int id_presupuestosubdetalle;
    private int costovidrio_presupuestosubdetalle;
    private int totalvidrio_presupuestosubdetalle;
    private int largop_presupuestosubdetalle;
    private int anchop_presupuestosubdetalle;
    private int totalperfil_presupuestosubdetalle;
    private int costoherrajes_presupuestosubdetalle;
    private int mo_presupuestosubdetalle;
    private int colocacion_presupuestosubdetalle;
    private Presupuestos_Detalles presupuestodetalle;

    public Presupuestos_Subdetalles() {
    }

    public Presupuestos_Subdetalles(int id_presupuestosubdetalle, int costovidrio_presupuestosubdetalle, int totalvidrio_presupuestosubdetalle, int largop_presupuestosubdetalle, int anchop_presupuestosubdetalle, int totalperfil_presupuestosubdetalle, int costoherrajes_presupuestosubdetalle, int mo_presupuestosubdetalle, int colocacion_presupuestosubdetalle, Presupuestos_Detalles presupuestodetalle) {
        this.id_presupuestosubdetalle = id_presupuestosubdetalle;
        this.costovidrio_presupuestosubdetalle = costovidrio_presupuestosubdetalle;
        this.totalvidrio_presupuestosubdetalle = totalvidrio_presupuestosubdetalle;
        this.largop_presupuestosubdetalle = largop_presupuestosubdetalle;
        this.anchop_presupuestosubdetalle = anchop_presupuestosubdetalle;
        this.totalperfil_presupuestosubdetalle = totalperfil_presupuestosubdetalle;
        this.costoherrajes_presupuestosubdetalle = costoherrajes_presupuestosubdetalle;
        this.mo_presupuestosubdetalle = mo_presupuestosubdetalle;
        this.colocacion_presupuestosubdetalle = colocacion_presupuestosubdetalle;
        this.presupuestodetalle = presupuestodetalle;
    }

    public int getId_presupuestosubdetalle() {
        return id_presupuestosubdetalle;
    }

    public void setId_presupuestosubdetalle(int id_presupuestosubdetalle) {
        this.id_presupuestosubdetalle = id_presupuestosubdetalle;
    }

    public int getCostovidrio_presupuestosubdetalle() {
        return costovidrio_presupuestosubdetalle;
    }

    public void setCostovidrio_presupuestosubdetalle(int costovidrio_presupuestosubdetalle) {
        this.costovidrio_presupuestosubdetalle = costovidrio_presupuestosubdetalle;
    }

    public int getTotalvidrio_presupuestosubdetalle() {
        return totalvidrio_presupuestosubdetalle;
    }

    public void setTotalvidrio_presupuestosubdetalle(int totalvidrio_presupuestosubdetalle) {
        this.totalvidrio_presupuestosubdetalle = totalvidrio_presupuestosubdetalle;
    }

    public int getLargop_presupuestosubdetalle() {
        return largop_presupuestosubdetalle;
    }

    public void setLargop_presupuestosubdetalle(int largop_presupuestosubdetalle) {
        this.largop_presupuestosubdetalle = largop_presupuestosubdetalle;
    }

    public int getAnchop_presupuestosubdetalle() {
        return anchop_presupuestosubdetalle;
    }

    public void setAnchop_presupuestosubdetalle(int anchop_presupuestosubdetalle) {
        this.anchop_presupuestosubdetalle = anchop_presupuestosubdetalle;
    }

    public int getTotalperfil_presupuestosubdetalle() {
        return totalperfil_presupuestosubdetalle;
    }

    public void setTotalperfil_presupuestosubdetalle(int totalperfil_presupuestosubdetalle) {
        this.totalperfil_presupuestosubdetalle = totalperfil_presupuestosubdetalle;
    }

    public int getCostoherrajes_presupuestosubdetalle() {
        return costoherrajes_presupuestosubdetalle;
    }

    public void setCostoherrajes_presupuestosubdetalle(int costoherrajes_presupuestosubdetalle) {
        this.costoherrajes_presupuestosubdetalle = costoherrajes_presupuestosubdetalle;
    }

    public int getMo_presupuestosubdetalle() {
        return mo_presupuestosubdetalle;
    }

    public void setMo_presupuestosubdetalle(int mo_presupuestosubdetalle) {
        this.mo_presupuestosubdetalle = mo_presupuestosubdetalle;
    }

    public int getColocacion_presupuestosubdetalle() {
        return colocacion_presupuestosubdetalle;
    }

    public void setColocacion_presupuestosubdetalle(int colocacion_presupuestosubdetalle) {
        this.colocacion_presupuestosubdetalle = colocacion_presupuestosubdetalle;
    }

    public Presupuestos_Detalles getPresupuestodetalle() {
        return presupuestodetalle;
    }

    public void setPresupuestodetalle(Presupuestos_Detalles presupuestodetalle) {
        this.presupuestodetalle = presupuestodetalle;
    }
    
}
