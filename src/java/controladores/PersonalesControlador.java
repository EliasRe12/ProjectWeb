package controladores;

import modelos.Personales;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class PersonalesControlador {
    public static boolean agregar(Personales personal){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into personales (nombre_personal, apellido_personal, ci_personal, telefono_personal)"
                    + "values ('" + personal.getNombre_personal() + "','"
                    + personal.getApellido_personal() + "','"
                    + personal.getCi_personal() + "','"
                    + personal.getTelefono_personal() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(PersonalesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean modificar(Personales personal){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update personales set nombre_personal='" + personal.getNombre_personal() + "', "
                    + "apellido_personal='" + personal.getApellido_personal() + "', "
                    + "ci_personal='" + personal.getCi_personal() + "', "
                    + "telefono_personal='" + personal.getTelefono_personal() + "' "
                    + "where id_personal=" + personal.getId_personal();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                Logger.getLogger(PersonalesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        return valor;
    }
    
    public static boolean eliminar(Personales personal){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from personales where id_personal=" + personal.getId_personal();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                Logger.getLogger(PersonalesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        return valor;
    }
    
    public static Personales buscarId(Personales personal) {
        if (Conexion.conectar()){
            String sql = "select * from personales where id_personal ='"+personal.getId_personal()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    personal.setId_personal(rs.getInt("id_personal"));
                    personal.setNombre_personal(rs.getString("nombre_personal"));
                    personal.setApellido_personal(rs.getString("apellido_personal"));
                    personal.setCi_personal(rs.getString("ci_personal"));
                    personal.setTelefono_personal(rs.getString("telefono_personal"));
                } else {
                    personal.setId_personal(0);
                    personal.setNombre_personal("");
                    personal.setApellido_personal("");
                    personal.setCi_personal("");
                    personal.setTelefono_personal("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return personal;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from personales where upper(nombre_personal) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_personal offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td class='centrado'>" + rs.getString("id_personal") + "</td>"
                                + "<td class='centrado'>" + rs.getString("nombre_personal") + "</td>"
                                + "<td class='centrado'>" + rs.getString("apellido_personal") + "</td>"
                                + "<td class='centrado'>" + rs.getString("ci_personal") + "</td>"
                                + "<td class='centrado'>" + rs.getString("telefono_personal") + "</td>"
                                + "</tr>";
                    }   
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
}
