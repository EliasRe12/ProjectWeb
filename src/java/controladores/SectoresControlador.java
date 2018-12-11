package controladores;

import modelos.Sectores;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class SectoresControlador {
    public static boolean agregar(Sectores sector){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into sectores (nombre_sector)" 
                    + "values ('" + sector.getNombre_sector() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                Logger.getLogger(SectoresControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        return valor;
    }
    
    public static boolean modificar(Sectores sector){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update sectores set nombre_sector='" + sector.getNombre_sector() + "'"
                    + " where id_sector=" + sector.getId_sector();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                Logger.getLogger(SectoresControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        return valor;
    }
    
    public static boolean eliminar(Sectores sector){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from sectores where id_sector=" + sector.getId_sector();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                Logger.getLogger(SectoresControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        return valor;
    }
    
    public static Sectores buscarId(Sectores sector) {
        if (Conexion.conectar()){
            String sql = "select * from sectores where id_sector ='"+sector.getId_sector()+"'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    sector.setId_sector(rs.getInt("id_sector"));
                    sector.setNombre_sector(rs.getString("nombre_sector"));
                } else {
                    sector.setId_sector(0);
                    sector.setNombre_sector("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return sector;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                  System.out.println(nombre);
                String sql = "select * from sectores where upper(nombre_sector) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_sector offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td class='centrado'>" + rs.getString("id_sector") + "</td>"
                                + "<td class='centrado'>" + rs.getString("nombre_sector") + "</td>"
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
