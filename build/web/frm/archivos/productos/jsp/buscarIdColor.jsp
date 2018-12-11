<%@page import="controladores.ColoresControlador"%>
<%@page import="modelos.Colores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_color = Integer.parseInt(request.getParameter("id_color"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Colores color = new Colores();
    color.setId_color(id_color);
    
   ColoresControlador.buscarId(color);
    if (color.getId_color()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else {
        color = new Colores();
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_color", color.getId_color());
    obj.put("nombre_color", color.getNombre_color());
    out.print(obj);
    out.flush();
%>
