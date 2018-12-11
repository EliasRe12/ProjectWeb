<%@page import="controladores.Fiscales_De_ObrasControlador"%>
<%@page import="modelos.Fiscales_De_Obras"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_fiscaldeobra = Integer.parseInt(request.getParameter("id_fiscaldeobra"));
    String nombre_fiscaldeobra = request.getParameter("nombre_fiscaldeobra");
    String apellido_fiscaldeobra = request.getParameter("apellido_fiscaldeobra");
    String ci_fiscaldeobra = request.getParameter("ci_fiscaldeobra");
    String telefono_fiscaldeobra = request.getParameter("telefono_fiscaldeobra");
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Fiscales_De_Obras fiscaldeobra = new Fiscales_De_Obras();
    fiscaldeobra.setId_fiscaldeobra(id_fiscaldeobra);
    fiscaldeobra.setNombre_fiscaldeobra(nombre_fiscaldeobra);
    fiscaldeobra.setApellido_fiscaldeobra(apellido_fiscaldeobra);
    fiscaldeobra.setCi_fiscaldeobra(ci_fiscaldeobra);
    fiscaldeobra.setTelefono_fiscaldeobra(telefono_fiscaldeobra);
    
    if (Fiscales_De_ObrasControlador.modificar(fiscaldeobra)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
