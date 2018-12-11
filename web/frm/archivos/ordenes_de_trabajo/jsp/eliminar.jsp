
<%@page import="controladores.Ordenes_De_TrabajoControlador"%>
<%@page import="modelos.Ordenes_De_Trabajo"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_ordendetrabajo = Integer.parseInt(request.getParameter("id_ordendetrabajo"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Ordenes_De_Trabajo ordendetrabajo = new Ordenes_De_Trabajo();
    ordendetrabajo.setId_ordendetrabajo(id_ordendetrabajo);
    
    if (Ordenes_De_TrabajoControlador.eliminar(ordendetrabajo)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>