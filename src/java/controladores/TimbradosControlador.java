package controladores;

import modelos.Timbrados;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class TimbradosControlador {
    public static boolean agregar(Timbrados timbrado){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into timbrados (numero_timbrado, fechainicio_timbrado, fechavencimiento_timbrado, "
                    + "fechactual_timbrado, desde_timbrado, hasta_timbrado)" 
                    + "values ('" + timbrado.getNumero_timbrado() + "','"
                    + timbrado.getFechainicio_timbrado() + "','"
                    + timbrado.getFechavencimiento_timbrado() + "','"
                    + timbrado.getFechactual_timbrado() + "','"
                    + timbrado.getDesde_timbrado() + "','"
                    + timbrado.getHasta_timbrado() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                Logger.getLogger(TimbradosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        return valor;
    }
    
    public static boolean modificar(Timbrados timbrado){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update timbrados set numero_timbrado='" + timbrado.getNumero_timbrado() + "', "
                    + "fechainicio_timbrado='" + timbrado.getFechainicio_timbrado() + "', "
                    + "fechavencimiento_timbrado='" + timbrado.getFechavencimiento_timbrado() + "', "
                    + "fechactual_timbrado='" + timbrado.getFechactual_timbrado() + "', "
                    + "desde_timbrado='" + timbrado.getDesde_timbrado() + "', "
                    + "hasta_timbrado='" + timbrado.getHasta_timbrado() + "' "
                    + "where id_timbrado=" + timbrado.getId_timbrado();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                Logger.getLogger(TimbradosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        return valor;
    }
    
    public static boolean eliminar(Timbrados timbrado){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from timbrados where id_timbrado=" + timbrado.getId_timbrado();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                Logger.getLogger(TimbradosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        return valor;
    }
    
    public static Timbrados buscarId(Timbrados timbrado) {
        if (Conexion.conectar()){
            String sql = "select * from timbrados where id_timbrado ='"+timbrado.getId_timbrado()+"'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    timbrado.setId_timbrado(rs.getInt("id_timbrado"));
                    timbrado.setNumero_timbrado(rs.getString("numero_timbrado"));
                    timbrado.setFechainicio_timbrado(rs.getDate("fechainicio_timbrado"));
                    timbrado.setFechavencimiento_timbrado(rs.getDate("fechavencimiento_timbrado"));
                    timbrado.setFechactual_timbrado(rs.getDate("fechactual_timbrado"));
                    timbrado.setDesde_timbrado(rs.getInt("desde_timbrado"));
                    timbrado.setHasta_timbrado(rs.getInt("hasta_timbrado"));
                } else {
                    timbrado.setId_timbrado(0);
                    timbrado.setNumero_timbrado("");
                    timbrado.setFechainicio_timbrado(null);
                    timbrado.setFechavencimiento_timbrado(null);
                    timbrado.setFechactual_timbrado(null);
                    timbrado.setDesde_timbrado(0);
                    timbrado.setHasta_timbrado(0);
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return timbrado;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                  System.out.println(nombre);
                String sql = "select * from timbrados where upper(numero_timbrado) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_timbrado offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td class='centrado'>" + rs.getString("id_timbrado") + "</td>"
                                + "<td class='centrado'>" + rs.getString("numero_timbrado") + "</td>"
                                + "<td class='centrado'>" + rs.getString("fechainicio_timbrado") + "</td>"
                                + "<td class='centrado'>" + rs.getString("fechavencimiento_timbrado") + "</td>"
                                + "<td class='centrado'>" + rs.getString("fechactual_timbrado") + "</td>"
                                + "<td class='centrado'>" + rs.getString("desde_timbrado") + "</td>"
                                + "<td class='centrado'>" + rs.getString("hasta_timbrado") + "</td>"
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
