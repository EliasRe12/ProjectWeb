<%@page import="utiles.Utiles"%>
<%@page import="modelos.Personales"%>
<%@page import="modelos.Ordenes_De_Trabajo"%>
<%@page import="controladores.Ordenes_De_Trabajo_DetallesControlador"%>
<%@page import="modelos.Ordenes_De_Trabajo_Detalles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_ordendetrabajodetalle = Integer.parseInt(request.getParameter("id_ordendetrabajodetalle"));
    int id_ordendetrabajo = Integer.parseInt(request.getParameter("id_ordendetrabajo"));
    int id_personal = Integer.parseInt(request.getParameter("id_personal"));
        
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Ordenes_De_Trabajo_Detalles ordendetrabajodetalle = new Ordenes_De_Trabajo_Detalles();
    ordendetrabajodetalle.setId_ordendetrabajodetalle(id_ordendetrabajodetalle);
    
    Ordenes_De_Trabajo ordendetrabajo = new Ordenes_De_Trabajo();
    ordendetrabajo.setId_ordendetrabajo(id_ordendetrabajo);
    ordendetrabajodetalle.setOrdendetrabajo(ordendetrabajo);
    
    Personales personal = new Personales();
    personal.setId_personal(id_personal);
    ordendetrabajodetalle.setPersonal(personal);
        
    if (Ordenes_De_Trabajo_DetallesControlador.modificar(ordendetrabajodetalle)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
