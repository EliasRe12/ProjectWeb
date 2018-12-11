<%@page import="controladores.PersonalesControlador"%>
<%@page import="modelos.Personales"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_personal = Integer.parseInt(request.getParameter("id_personal"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Personales personal = new Personales();
    personal.setId_personal(id_personal);
    
   PersonalesControlador.buscarId(personal);
    if (personal.getId_personal()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else {
        personal = new Personales();
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_personal", personal.getId_personal());
    obj.put("nombre_personal", personal.getNombre_personal());
    obj.put("apellido_personal", personal.getApellido_personal());
    obj.put("ci_personal", personal.getCi_personal());
    obj.put("telefono_personal", personal.getTelefono_personal());
    
    out.print(obj);
    out.flush();
%>
