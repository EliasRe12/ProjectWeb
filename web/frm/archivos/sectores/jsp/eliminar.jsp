<%@page import="controladores.SectoresControlador"%>
<%@page import="modelos.Sectores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_sector = Integer.parseInt(request.getParameter("id_sector"));
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Sectores sector = new Sectores();
    sector.setId_sector(id_sector);
    
    if (SectoresControlador.eliminar(sector)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>