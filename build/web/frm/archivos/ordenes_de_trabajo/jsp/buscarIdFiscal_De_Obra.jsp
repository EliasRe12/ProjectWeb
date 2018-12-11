
<%@page import="controladores.Fiscales_De_ObrasControlador"%>
<%@page import="modelos.Fiscales_De_Obras"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_fiscaldeobra = Integer.parseInt(request.getParameter("id_fiscaldeobra"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Fiscales_De_Obras fiscaldeobra = new Fiscales_De_Obras();
    fiscaldeobra.setId_fiscaldeobra(id_fiscaldeobra);
    
   Fiscales_De_ObrasControlador.buscarId(fiscaldeobra);
    if (fiscaldeobra.getId_fiscaldeobra()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_fiscaldeobra", fiscaldeobra.getId_fiscaldeobra());
    obj.put("nombre_fiscaldeobra", fiscaldeobra.getNombre_fiscaldeobra());
    obj.put("apellido_fiscaldeobra", fiscaldeobra.getApellido_fiscaldeobra());
    obj.put("ci_fiscaldeobra", fiscaldeobra.getCi_fiscaldeobra());
    obj.put("telefono_fiscaldeobra", fiscaldeobra.getTelefono_fiscaldeobra());
    out.print(obj);
    out.flush();
%>
