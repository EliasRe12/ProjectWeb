<%@page import="controladores.PersonalesControlador"%>
<%@page import="modelos.Personales"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_personal = Integer.parseInt(request.getParameter("id_personal"));
    String nombre_personal = request.getParameter("nombre_personal");
    String apellido_personal = request.getParameter("apellido_personal");
    String telefono_personal = request.getParameter("telefono_personal");
    String ci_personal = request.getParameter("ci_personal");
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Personales personal = new Personales();
    personal.setId_personal(id_personal);
    personal.setNombre_personal(nombre_personal);
    personal.setApellido_personal(apellido_personal);
    personal.setTelefono_personal(telefono_personal);
    personal.setCi_personal(ci_personal);
    
    if (PersonalesControlador.agregar(personal)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>