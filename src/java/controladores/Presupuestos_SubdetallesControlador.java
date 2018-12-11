package controladores;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import modelos.Presupuestos_Detalles;
import modelos.Presupuestos_Subdetalles;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Elias
 */
public class Presupuestos_SubdetallesControlador {
    public static Presupuestos_Subdetalles buscarId(int id) {
         Presupuestos_Subdetalles presupuestosubdetalle = null;
        if (Conexion.conectar()) {
            
             String sql = "select * from presupuestos_subdetalles psd, "
                        + "presupuestos_detalles pd where "
                        + "psd.id_presupuestodetalle=pd.id_presupuestodetalle and "
                        + "id_presupuestosubdetalle='"+id+"'";
                System.out.println("sql 1"+ sql);
                try  {
                    ResultSet rs = Conexion.getSt().executeQuery(sql);
                    
                    if (rs.next()) {
                        presupuestosubdetalle = new Presupuestos_Subdetalles();
                        presupuestosubdetalle.setId_presupuestosubdetalle(rs.getInt("id_presupuestosubdetalle"));
                        presupuestosubdetalle.setCostovidrio_presupuestosubdetalle(rs.getInt("costovidrio_presupuestosubdetalle"));
                        presupuestosubdetalle.setTotalvidrio_presupuestosubdetalle(rs.getInt("totalvidrio_presupuestosubdetalle"));
                        presupuestosubdetalle.setLargop_presupuestosubdetalle(rs.getInt("largop_presupuestosubdetalle"));
                        presupuestosubdetalle.setAnchop_presupuestosubdetalle(rs.getInt("anchop_presupuestosubdetalle"));
                        presupuestosubdetalle.setTotalperfil_presupuestosubdetalle(rs.getInt("totalperfil_presupuestosubdetalle"));
                        presupuestosubdetalle.setCostoherrajes_presupuestosubdetalle(rs.getInt("costoherrajes_presupuestosubdetalle"));
                        presupuestosubdetalle.setMo_presupuestosubdetalle(rs.getInt("mo_presupuestosubdetalle"));
                        presupuestosubdetalle.setColocacion_presupuestosubdetalle(rs.getInt("colocacion_presupuestosubdetalle"));
                        
                        Presupuestos_Detalles presupuestodetalle = new Presupuestos_Detalles();
                        presupuestodetalle.setId_presupuestodetalle(rs.getInt("id_presupuestodetalle"));
                        presupuestodetalle.setAlto_presupuestodetalle(rs.getString("alto_presupuestodetalle"));
                        presupuestodetalle.setAncho_presupuestodetalle(rs.getString("ancho_presupuestodetalle"));
                        presupuestodetalle.setM2_presupuestodetalle(rs.getString("m2_presupuestodetalle"));
                        presupuestodetalle.setCantidad_presupuestodetalle(rs.getInt("cantidad_presupuestodetalle"));
                        presupuestodetalle.setPrecio_presupuestodetalle(rs.getInt("precio_presupuestodetalle"));
                        presupuestosubdetalle.setPresupuestodetalle(presupuestodetalle);
                    } 
                    
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return presupuestosubdetalle;
    }
    
    
    public static String buscarIdPresupuestoDetalle(int id) {
        String valor="";
        if (Conexion.conectar()) {
            try{
                String sql = "select * from presupuestos_subdetalles psd, presupuestos_detalles pd "
                        + "where psd.id_presupuestodetalle=pd.id_presupuestodetalle and "
                        + "pd.id_presupuestodetalle='"+id+"'";
                System.out.println("--> "+ sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat( "#,###" );
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    int gana = 0; 
                    int ganancia = 0;
                    int iva = 0;
                    int totalfinal = 0; 
                    
                    while (rs.next()) {
                        BigDecimal precio = rs.getBigDecimal("colocacion_presupuestosubdetalle");
                        gana = precio.intValueExact() * 25/100 ;
                        ganancia = gana + precio.intValueExact();
                        iva = ganancia * 10/100;
                        totalfinal = iva + ganancia;
                        
                        //totalfinal = totalfinal.add(precio);
                        //precio = precio.add(precio);
                        //BigDecimal iva = (total/11);
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                               + "<td class='centrado'>" + rs.getString("id_presupuestosubdetalle") + "</td>"
                               + "<td class='centrado'>" + rs.getString("totalvidrio_presupuestosubdetalle") + "</td>" 
                               + "<td class='centrado'>" + rs.getString("totalperfil_presupuestosubdetalle") + "</td>"
                               + "<td class='centrado'>" + rs.getString("costoherrajes_presupuestosubdetalle") + "</td>"
                               + "<td class='centrado'>" + rs.getString("mo_presupuestosubdetalle") + "</td>"
                               + "<td class='centrado'>" + rs.getString("colocacion_presupuestosubdetalle") + "</td>"
                              // + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                               + "<td class='centrado'>" 
                               + "<button onclick='editarLinea1("+rs.getString("id_presupuestosubdetalle")+")'"
                               + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                               + "</span></button></td>"
                               + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=10>No existen registros ...</td></tr>";
                    }else{
                        tabla += "<tr><td colspan=5>TOTAL + IVA</td><td class='centrado'>"+df.format(totalfinal)+"</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } 
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                System.out.println(nombre);
                String sql = "select * from presupuestos_subdetalles psd "
                        + "left join presupuestos_detalles pd on pd.id_presupuestodetalle=psd.id_presupuestodetalle "
                        + "where upper() like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_presupuestosubdetalle "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_presupuestodetalle") + "</td>"
                                + "<td>" + rs.getString("id_presupuestodetalle") + "</td>"
                                + "<td>" + rs.getString("vidrio_presupuestosubdetalle") + "</td>"
                                + "<td>" + rs.getString("largop_presupuestosubdetalle") + "</td>"
                                + "<td>" + rs.getString("anchop_presupuestosubdetalle") + "</td>"
                                + "<td>" + rs.getString("mo_presupuestodetalle") + "</td>" 
                                + "</tr>";
                    }   
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }  
            } catch (SQLException ex) {
                System.err.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }
    
    public static boolean agregar(Presupuestos_Subdetalles presupuestosubdetalle){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into presupuestos_subdetalles "
                    + "(costovidrio_presupuestosubdetalle, totalvidrio_presupuestosubdetalle, "
                    + "largop_presupuestosubdetalle, anchop_presupuestosubdetalle, totalperfil_presupuestosubdetalle, "
                    + "costoherrajes_presupuestosubdetalle, mo_presupuestosubdetalle, colocacion_presupuestosubdetalle, "
                    + "id_presupuestodetalle) "
                    + "values (?,?,?,?,?,?,?,?,?)";
            System.out.println("AGREGRAR" + sql);
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, presupuestosubdetalle.getCostovidrio_presupuestosubdetalle());
                ps.setInt(2, presupuestosubdetalle.getTotalvidrio_presupuestosubdetalle());
                ps.setInt(3, presupuestosubdetalle.getLargop_presupuestosubdetalle());
                ps.setInt(4, presupuestosubdetalle.getAnchop_presupuestosubdetalle());
                ps.setInt(5, presupuestosubdetalle.getTotalperfil_presupuestosubdetalle());
                ps.setInt(6, presupuestosubdetalle.getCostoherrajes_presupuestosubdetalle());
                ps.setInt(7, presupuestosubdetalle.getMo_presupuestosubdetalle());
                ps.setInt(8, presupuestosubdetalle.getColocacion_presupuestosubdetalle());
                ps.setInt(9, presupuestosubdetalle.getPresupuestodetalle().getId_presupuestodetalle());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                valor = true;
            }catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try{
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("-->" + ex1.getLocalizedMessage());
                }
            }        
        }
        Conexion.cerrar();
        return valor;
        
    }
    
    public static boolean modificar(Presupuestos_Subdetalles presupuestosubdetalle){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update presupuestos_subdetalles set "
                    + "costovidrio_presupuestosubdetalle=?, "
                    + "totalvidrio_presupuestosubdetalle=?, "
                    + "largop_presupuestosubdetalle=?, "
                    + "anchop_presupuestosubdetalle=?, "
                    + "totalperfil_presupuestosubdetalle=?, "
                    + "costoherrajes_presupuestosubdetalle=?, "
                    + "mo_presupuestosubdetalle=?, "
                    + "colocacion_presupuestosubdetalle=?, "
                    + "id_presupuestodetalle=? "
                    + "where id_presupuestosubdetalle=?";
            System.out.println("MOdificar "+ sql);
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, presupuestosubdetalle.getCostovidrio_presupuestosubdetalle());
                ps.setInt(2, presupuestosubdetalle.getTotalvidrio_presupuestosubdetalle());
                ps.setInt(3, presupuestosubdetalle.getLargop_presupuestosubdetalle());
                ps.setInt(4, presupuestosubdetalle.getAnchop_presupuestosubdetalle());
                ps.setInt(5, presupuestosubdetalle.getTotalperfil_presupuestosubdetalle());
                ps.setInt(6, presupuestosubdetalle.getCostoherrajes_presupuestosubdetalle());
                ps.setInt(7, presupuestosubdetalle.getMo_presupuestosubdetalle());
                ps.setInt(8, presupuestosubdetalle.getColocacion_presupuestosubdetalle());
                ps.setInt(9, presupuestosubdetalle.getPresupuestodetalle().getId_presupuestodetalle());
                ps.setInt(10, presupuestosubdetalle.getId_presupuestosubdetalle());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                valor = true;
            }catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try{
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("-->" + ex1.getLocalizedMessage());
                }
            }        
        }
        Conexion.cerrar();
        return valor;
    }
    
    public static boolean eliminar(Presupuestos_Subdetalles presupuestosubdetalle){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from presupuestos_subdetalles where id_presupuestosubdetalle=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, presupuestosubdetalle.getId_presupuestosubdetalle());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }        
        }
        Conexion.cerrar();
        return valor;
    }
}
