
<%@page import="controladores.PersonalesControlador"%>
<%@page import="modelos.Personales"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_personal = Integer.parseInt(request.getParameter("id_personal"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Personales personal = new Personales();
    personal.setId_personal(id_personal);
    
    if (PersonalesControlador.eliminar(personal)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>