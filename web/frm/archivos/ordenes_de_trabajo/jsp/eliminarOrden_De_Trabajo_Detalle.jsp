<%@page import="controladores.Ordenes_De_Trabajo_DetallesControlador"%>
<%@page import="modelos.Ordenes_De_Trabajo_Detalles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_ordendetrabajodetalle = Integer.parseInt(request.getParameter("id_ordendetrabajodetalle"));
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Ordenes_De_Trabajo_Detalles ordendetrabajodetalle = new Ordenes_De_Trabajo_Detalles();
    ordendetrabajodetalle.setId_ordendetrabajodetalle(id_ordendetrabajodetalle);
    
    if (Ordenes_De_Trabajo_DetallesControlador.eliminar(ordendetrabajodetalle)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>