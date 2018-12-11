<%@page import="controladores.ColoresControlador"%>
<%@page import="modelos.Colores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_color = Integer.parseInt(request.getParameter("id_color"));
    String nombre_color = request.getParameter("nombre_color");
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Colores color = new Colores();
    color.setId_color(id_color);
    color.setNombre_color(nombre_color);
    
    if (ColoresControlador.modificar(color)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
