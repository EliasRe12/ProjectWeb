package controladores;

import java.math.BigDecimal;
import modelos.Presupuestos_Detalles;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Logger;
import modelos.Clientes;
import modelos.Presupuestos;
import modelos.Productos;
import modelos.Sectores;
import modelos.Ubicaciones;

public class Presupuestos_DetallesControlador {
    public static Presupuestos_Detalles buscarId(int id) {
         Presupuestos_Detalles presupuestodetalle = null;
        if (Conexion.conectar()) {
             String sql = "select * from presupuestos_detalles pd, presupuestos p, "
                        + "ubicaciones ub, sectores se where "
                        + "p.id_presupuesto=pd.id_presupuesto and "
                        + "ub.id_ubicacion=pd.id_ubicacion and "
                        + "se.id_sector=pd.id_sector and "
                        + "id_presupuestodetalle='"+id+"'";
                System.out.println("sql 1"+ sql);
                try  {
                    ResultSet rs = Conexion.getSt().executeQuery(sql);
                    if (rs.next()) {
                        presupuestodetalle = new Presupuestos_Detalles();
                        presupuestodetalle.setId_presupuestodetalle(rs.getInt("id_presupuestodetalle"));
                        presupuestodetalle.setCantidad_presupuestodetalle(rs.getInt("cantidad_presupuestodetalle"));
                        presupuestodetalle.setPrecio_presupuestodetalle(rs.getInt("precio_presupuestodetalle"));
                        presupuestodetalle.setAncho_presupuestodetalle(rs.getString("ancho_presupuestodetalle"));
                        presupuestodetalle.setAlto_presupuestodetalle(rs.getString("alto_presupuestodetalle"));
                        presupuestodetalle.setM2_presupuestodetalle(rs.getString("m2_presupuestodetalle"));
                        presupuestodetalle.setDetalle_presupuestodetalle(rs.getString("detalle_presupuestodetalle"));
                        presupuestodetalle.setMm_presupuestodetalle(rs.getInt("mm_presupuestodetalle"));
                        presupuestodetalle.setColor_presupuestodetalle(rs.getString("color_presupuestodetalle"));
                        
                        Presupuestos presupuesto = new Presupuestos();
                        presupuesto.setId_presupuesto(rs.getInt("id_presupuesto"));
                        presupuestodetalle.setPresupuesto(presupuesto);
                        
                        Ubicaciones ubicacion = new Ubicaciones();
                        ubicacion.setId_ubicacion(rs.getInt("id_ubicacion"));
                        ubicacion.setNombre_ubicacion(rs.getString("nombre_ubicacion"));
                        presupuestodetalle.setUbicacion(ubicacion);
                        
                        Sectores sector = new Sectores();
                        sector.setId_sector(rs.getInt("id_sector"));
                        sector.setNombre_sector(rs.getString("nombre_sector"));
                        presupuestodetalle.setSector(sector);
                        
                    } 
                    
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return presupuestodetalle;
    }
    
    public static String buscarIdPresupuesto(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try{
                 String sql = "select * from presupuestos_detalles pd, presupuestos p, ubicaciones ub, "
                        + "sectores se where "
                        + "p.id_presupuesto =pd.id_presupuesto and "
                        + "ub.id_ubicacion=pd.id_ubicacion and "
                        + "se.id_sector=pd.id_sector and "
                        + "p.id_presupuesto="+id+" "
                        + "order by id_presupuestodetalle";
                System.out.println("DETALLE--> "+ sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat( "#,###" );
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    int m2 = 0;
                    int cant = 0;
                                        
                    while (rs.next()) {
                        BigDecimal precio = rs.getBigDecimal("precio_presupuestodetalle");
                        total = total.add(precio);
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                               + "<td class='centrado'>" + rs.getString("id_presupuestodetalle") + "</td>"
                               + "<td class='centrado'>" + rs.getString("cantidad_presupuestodetalle") + "</td>" 
                               + "<td class='centrado'>" + rs.getString("detalle_presupuestodetalle") + "</td>"
                               + "<td class='centrado'>" + rs.getString("nombre_ubicacion") + "</td>" 
                               + "<td class='centrado'>" + rs.getString("nombre_sector") + "</td>"
                               + "<td class='centrado'>" + rs.getString("ancho_presupuestodetalle") + "</td>" 
                               + "<td class='centrado'>" + rs.getString("alto_presupuestodetalle") + "</td>"
                               + "<td class='centrado'>" + rs.getString("mm_presupuestodetalle") + "</td>"
                               + "<td class='centrado'>" + rs.getString("color_presupuestodetalle") + "</td>" 
                               + "<td class='centrado'>" + rs.getString("m2_presupuestodetalle") + "</td>" 
                               + "<td class='centrado'>" + rs.getString("precio_presupuestodetalle") + "</td>"
                              // + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                               + "<td class='centrado'>" 
                               + "<button onclick='editarLinea("+rs.getString("id_presupuestodetalle")+")'"
                               + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                               + "</span></button></td>"
                               + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=10>No existen registros ...</td></tr>";
                    }else{
                        tabla += "<tr><td colspan=10>TOTAL</td><td class='centrado'>"+df.format(total)+"</td></tr>";
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
                String sql = "select * from presupuestos_detalles pd "
                        + "left join presupuestos p on p.id_presupuesto=pd.id_presupuesto "
                        + "left join ubicaciones ub on ub.id_ubicacion=pd.id_ubicacion "
                        + "left join sectores se on se.id_sector=pd.id_sector "
                        + "where upper(nombre_producto) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_presupuestodetalle "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_presupuestodetalle") + "</td>"
                                + "<td>" + rs.getString("cantidad_presupuestodetalle") + "</td>"
                                + "<td>" + rs.getString("detalle_presupuestodetalle") + "</td>"
                                + "<td>" + rs.getString("id_ubicacion") + "</td>"
                                + "<td>" + rs.getString("id_sector") + "</td>"
                                + "<td>" + rs.getString("ancho_presupuestodetalle") + "</td>" 
                                + "<td>" + rs.getString("alto_presupuestodetalle") + "</td>"
                                + "<td>" + rs.getString("m2_presupuestodetalle") + "</td>"
                                + "<td>" + rs.getString("precio_presupuestodetalle") + "</td>"
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
    
    public static boolean agregar(Presupuestos_Detalles presupuestodetalle){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into presupuestos_detalles "
                    + "(id_presupuesto, detalle_presupuestodetalle, id_ubicacion, id_sector, "
                    + "cantidad_presupuestodetalle, precio_presupuestodetalle, ancho_presupuestodetalle, "
                    + "alto_presupuestodetalle, m2_presupuestodetalle, mm_presupuestodetalle, color_presupuestodetalle) "
                    + "values (?,?,?,?,?,?,?,?,?,?,?)";
            System.out.println("AGREGRAR" + sql);
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, presupuestodetalle.getPresupuesto().getId_presupuesto());
                ps.setString(2, presupuestodetalle.getDetalle_presupuestodetalle());
                ps.setInt(3, presupuestodetalle.getUbicacion().getId_ubicacion());
                ps.setInt(4, presupuestodetalle.getSector().getId_sector());
                ps.setInt(5, presupuestodetalle.getCantidad_presupuestodetalle());
                ps.setInt(6, presupuestodetalle.getPrecio_presupuestodetalle());
                ps.setString(7, presupuestodetalle.getAncho_presupuestodetalle());
                ps.setString(8, presupuestodetalle.getAlto_presupuestodetalle());
                ps.setString(9, presupuestodetalle.getM2_presupuestodetalle());
                ps.setInt(10, presupuestodetalle.getMm_presupuestodetalle());
                ps.setString(11, presupuestodetalle.getColor_presupuestodetalle());
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
    
    public static boolean modificar(Presupuestos_Detalles presupuestodetalle){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update presupuestos_detalles set "
                    + "id_presupuesto=?, " 
                    + "detalle_presupuestodetalle=?, "
                    + "id_ubicacion=?, " 
                    + "id_sector=?, " 
                    + "cantidad_presupuestodetalle=?, " 
                    + "precio_presupuestodetalle=?, "
                    + "ancho_presupuestodetalle=?, "
                    + "alto_presupuestodetalle=?, "
                    + "m2_presupuestodetalle=?, "
                    + "mm_presupuestodetalle=?, "
                    + "color_presupuestodetalle=? "
                    + "where id_presupuestodetalle=?";
            System.out.println("MOdificar "+ sql);
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, presupuestodetalle.getPresupuesto().getId_presupuesto());
                ps.setString(2, presupuestodetalle.getDetalle_presupuestodetalle());
                ps.setInt(3, presupuestodetalle.getUbicacion().getId_ubicacion());
                ps.setInt(4, presupuestodetalle.getSector().getId_sector());
                ps.setInt(5, presupuestodetalle.getCantidad_presupuestodetalle());
                ps.setInt(6, presupuestodetalle.getPrecio_presupuestodetalle());
                ps.setString(7, presupuestodetalle.getAncho_presupuestodetalle());
                ps.setString(8, presupuestodetalle.getAlto_presupuestodetalle());
                ps.setString(9, presupuestodetalle.getM2_presupuestodetalle());
                ps.setInt(10, presupuestodetalle.getMm_presupuestodetalle());
                ps.setString(11, presupuestodetalle.getColor_presupuestodetalle());
                ps.setInt(12, presupuestodetalle.getId_presupuestodetalle());
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
    
    public static boolean eliminar(Presupuestos_Detalles presupuestodetalle){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from presupuestos_detalles where id_presupuestodetalle=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, presupuestodetalle.getId_presupuestodetalle());
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


