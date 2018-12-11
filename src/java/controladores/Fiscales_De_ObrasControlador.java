package controladores;

import modelos.Fiscales_De_Obras;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import modelos.Ciudades;

public class Fiscales_De_ObrasControlador {
    public static boolean agregar(Fiscales_De_Obras fiscaldeobra){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into fiscales_de_obras (nombre_fiscaldeobra, apellido_fiscaldeobra, ci_fiscaldeobra, telefono_fiscaldeobra) "
                    + "values ('" + fiscaldeobra.getNombre_fiscaldeobra() + "','"
                    + fiscaldeobra.getApellido_fiscaldeobra() + "','"
                    + fiscaldeobra.getCi_fiscaldeobra() + "','"
                    + fiscaldeobra.getTelefono_fiscaldeobra() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(Fiscales_De_ObrasControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean modificar(Fiscales_De_Obras fiscaldeobra){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update fiscales_de_obras set nombre_fiscaldeobra='" + fiscaldeobra.getNombre_fiscaldeobra() + "', "
                    + "apellido_fiscaldeobra='" + fiscaldeobra.getApellido_fiscaldeobra() + "',"
                    + "ci_fiscaldeobra='" + fiscaldeobra.getCi_fiscaldeobra() + "',"
                    + "telefono_fiscaldeobra='" + fiscaldeobra.getTelefono_fiscaldeobra()+ "' "
                    + "where id_fiscaldeobra=" + fiscaldeobra.getId_fiscaldeobra();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(Fiscales_De_ObrasControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Fiscales_De_Obras fiscaldeobra){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from fiscales_de_obras where id_fiscaldeobra=" + fiscaldeobra.getId_fiscaldeobra();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(Fiscales_De_ObrasControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static Fiscales_De_Obras buscarId(Fiscales_De_Obras fiscaldeobra) {
        if (Conexion.conectar()){
            String sql = "select * from fiscales_de_obras "
                    + "where id_fiscaldeobra ='"+fiscaldeobra.getId_fiscaldeobra()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    fiscaldeobra.setId_fiscaldeobra(rs.getInt("id_fiscaldeobra"));
                    fiscaldeobra.setNombre_fiscaldeobra(rs.getString("nombre_fiscaldeobra"));
                    fiscaldeobra.setApellido_fiscaldeobra(rs.getString("apellido_fiscaldeobra"));
                    fiscaldeobra.setCi_fiscaldeobra(rs.getString("ci_fiscaldeobra"));
                    fiscaldeobra.setTelefono_fiscaldeobra(rs.getString("telefono_fiscaldeobra"));
                } else {
                    fiscaldeobra.setId_fiscaldeobra(0);
                    fiscaldeobra.setNombre_fiscaldeobra("");
                    fiscaldeobra.setApellido_fiscaldeobra("");
                    fiscaldeobra.setCi_fiscaldeobra("");
                    fiscaldeobra.setTelefono_fiscaldeobra("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return fiscaldeobra;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from fiscales_de_obras "
                        + "where upper(nombre_fiscaldeobra) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_fiscaldeobra offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td class='centrado'>" + rs.getString("id_fiscaldeobra") + "</td>"
                                + "<td class='centrado'>" + rs.getString("nombre_fiscaldeobra") + "</td>"
                                + "<td class='centrado'>" + rs.getString("apellido_fiscaldeobra") + "</td>"
                                + "<td class='centrado'>" + rs.getString("ci_fiscaldeobra") + "</td>"
                                + "<td class='centrado'>" + rs.getString("telefono_fiscaldeobra") + "</td>"
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
