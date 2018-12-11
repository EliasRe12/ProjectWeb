package controladores;

import modelos.Colores;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class ColoresControlador {
    public static boolean agregar(Colores color){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into colores (nombre_color)" 
                    + "values ('" + color.getNombre_color() + "')";
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
            } catch (SQLException ex) {
                Logger.getLogger(ColoresControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        return valor;
    }
    
    public static boolean modificar(Colores color){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update colores set nombre_color='" + color.getNombre_color() + "'"
                    + " where id_color=" + color.getId_color();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                Logger.getLogger(ColoresControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        return valor;
    }
    
    public static boolean eliminar(Colores color){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from colores where id_color=" + color.getId_color();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
            } catch (SQLException ex) {
                Logger.getLogger(ColoresControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        return valor;
    }
    
    public static Colores buscarId(Colores color) {
        if (Conexion.conectar()){
            String sql = "select * from colores where id_color ='"+color.getId_color()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    color.setId_color(rs.getInt("id_color"));
                    color.setNombre_color(rs.getString("nombre_color"));
                } else {
                    color.setId_color(0);
                    color.setNombre_color("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return color;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from colores where upper(nombre_color) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_color offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td class='centrado'>" + rs.getString("id_color") + "</td>"
                                + "<td class='centrado'>" + rs.getString("nombre_color") + "</td>"
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
