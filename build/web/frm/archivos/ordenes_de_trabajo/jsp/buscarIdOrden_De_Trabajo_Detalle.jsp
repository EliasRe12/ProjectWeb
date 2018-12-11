<%@page import="modelos.Personales"%>
<%@page import="modelos.Ordenes_De_Trabajo"%>
<%@page import="controladores.Ordenes_De_Trabajo_DetallesControlador"%>
<%@page import="modelos.Ordenes_De_Trabajo_Detalles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_ordendetrabajodetalle = Integer.parseInt(request.getParameter("id_ordendetrabajodetalle"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    
    Ordenes_De_Trabajo_Detalles ordendetrabajodetalle = Ordenes_De_Trabajo_DetallesControlador.buscarId(id_ordendetrabajodetalle);
    if (ordendetrabajodetalle != null){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        ordendetrabajodetalle = new Ordenes_De_Trabajo_Detalles();
        ordendetrabajodetalle.setId_ordendetrabajodetalle(0);
        
        Ordenes_De_Trabajo ordendetrabajo = new Ordenes_De_Trabajo();
        ordendetrabajo.setId_ordendetrabajo(0);
        ordendetrabajodetalle.setOrdendetrabajo(ordendetrabajo);
        
        Personales personal = new Personales();
        personal.setId_personal(0);
        personal.setNombre_personal("");
        personal.setApellido_personal("");
        personal.setCi_personal("");
        ordendetrabajodetalle.setPersonal(personal);
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    obj.put("id_ordendetrabajodetalle",ordendetrabajodetalle.getId_ordendetrabajodetalle());
    obj.put("id_ordendetrabajo",ordendetrabajodetalle.getOrdendetrabajo().getId_ordendetrabajo());
    obj.put("id_personal",ordendetrabajodetalle.getPersonal().getId_personal());
    obj.put("nombre_personal",ordendetrabajodetalle.getPersonal().getNombre_personal());
    obj.put("apellido_personal",ordendetrabajodetalle.getPersonal().getApellido_personal());
    obj.put("ci_personal",ordendetrabajodetalle.getPersonal().getCi_personal());
    
    
    out.print(obj);
    out.flush();
%>
