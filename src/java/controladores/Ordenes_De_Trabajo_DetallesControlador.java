package controladores;

import java.math.BigDecimal;
import modelos.Ordenes_De_Trabajo_Detalles;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Logger;
import modelos.Ordenes_De_Trabajo;
import modelos.Personales;

public class Ordenes_De_Trabajo_DetallesControlador {
    
    public static Ordenes_De_Trabajo_Detalles buscarId(int id) {
         Ordenes_De_Trabajo_Detalles ordendetrabajodetalle = null;
        if (Conexion.conectar()) {
            
             String sql = "select * from ordenes_de_trabajo_detalles od, ordenes_de_trabajo ob, personales per where "
                        + "ob.id_ordendetrabajo=od.id_ordendetrabajo and "
                        + "per.id_personal=od.id_personal and "
                        + "id_ordendetrabajodetalle='"+id+"'";
                System.out.println("sql "+ sql);
                try  {
                    ResultSet rs = Conexion.getSt().executeQuery(sql);
                    
                    if (rs.next()) {
                        ordendetrabajodetalle = new Ordenes_De_Trabajo_Detalles();
                        ordendetrabajodetalle.setId_ordendetrabajodetalle(rs.getInt("id_ordendetrabajodetalle"));
                                                                        
                        Personales personal = new Personales();
                        personal.setId_personal(rs.getInt("id_personal"));
                        personal.setNombre_personal(rs.getString("nombre_personal"));
                        personal.setApellido_personal(rs.getString("apellido_personal"));
                        personal.setCi_personal(rs.getString("ci_personal"));
                        ordendetrabajodetalle.setPersonal(personal);
                        
                        Ordenes_De_Trabajo ordendetrabajo = new Ordenes_De_Trabajo();
                        ordendetrabajo.setId_ordendetrabajo(rs.getInt("id_ordendetrabajo"));
                        ordendetrabajodetalle.setOrdendetrabajo(ordendetrabajo);
                    } 
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return ordendetrabajodetalle;
    }
    
    public static String buscarIdObra(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try{
                 String sql = "select * from ordenes_de_trabajo_detalles od, ordenes_de_trabajo ob, personales per where "
                        + "ob.id_ordendetrabajo=od.id_ordendetrabajo and "
                        + "per.id_personal=od.id_personal and "
                        + "ob.id_ordendetrabajo="+id+" "
                        + "order by id_ordendetrabajodetalle";
                System.out.println("--> "+ sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat( "#,###" );
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    
                    while (rs.next()) {
//                        BigDecimal precio = rs.getBigDecimal("precio_ordendetrabajodetalle");
//                        total = total.add(precio);
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                               + "<td class='centrado'>" + rs.getString("id_ordendetrabajodetalle") + "</td>"
                               + "<td class='centrado'>" + rs.getString("nombre_personal") + "</td>"
                               + "<td class='centrado'>" + rs.getString("apellido_personal") + "</td>"
                               + "<td class='centrado'>" + rs.getString("ci_personal") + "</td>"
                               // + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                               + "<td class='centrado'>" 
                               + "<button onclick='editarLinea("+rs.getString("id_ordendetrabajodetalle")+")'"
                               + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                               + "</span></button></td>"
                               + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=10>No existen registros ...</td></tr>";
                    }else{
//                        tabla += "<tr><td colspan=8>TOTAL</td><td class='centrado'>"+df.format(total)+"</td></tr>";
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
                String sql = "select * from ordenes_de_trabajo_detalles od "
                        + "left join ordenes_de_trabajo o on o.id_ordendetrabajo=od.id_ordendetrabajo "
                        + "left join personales per on per.id_personal=od.id_personal "
                        + "where upper(nombre_personal) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_ordendetrabajodetalle "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td class='centrado'>" + rs.getString("id_ordendetrabajodetalle") + "</td>"
                                + "<td class='centrado'>" + rs.getString("nombre_personal") + "</td>"
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
    
    public static boolean agregar(Ordenes_De_Trabajo_Detalles ordendetrabajodetalle){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into ordenes_de_trabajo_detalles "
                    + "(id_ordendetrabajo, id_personal) "
                    + "values (?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, ordendetrabajodetalle.getOrdendetrabajo().getId_ordendetrabajo());
                ps.setInt(2, ordendetrabajodetalle.getPersonal().getId_personal());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
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
    
    public static boolean modificar(Ordenes_De_Trabajo_Detalles ordendetrabajodetalle){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update ordenes_de_trabajo_detalles set "
                    + "id_ordendetrabajo=?, " 
                    + "id_personal=?, "
                    + "where id_ordendetrabajodetalle=?";
            System.out.println("sql "+ sql);
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, ordendetrabajodetalle.getOrdendetrabajo().getId_ordendetrabajo());
                ps.setInt(2, ordendetrabajodetalle.getPersonal().getId_personal());
                ps.setInt(3, ordendetrabajodetalle.getId_ordendetrabajodetalle());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
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
    
    public static boolean eliminar(Ordenes_De_Trabajo_Detalles ordendetrabajodetalle){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from ordenes_de_trabajo_detalles where id_ordendetrabajodetalle=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, ordendetrabajodetalle.getId_ordendetrabajodetalle());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
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


