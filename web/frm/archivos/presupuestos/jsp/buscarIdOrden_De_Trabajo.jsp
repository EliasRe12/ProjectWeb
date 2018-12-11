<%@page import="modelos.Ciudades"%>
<%@page import="modelos.Fiscales_De_Obras"%>
<%@page import="utiles.Utiles"%>
<%@page import="controladores.Ordenes_De_Trabajo_DetallesControlador"%>
<%@page import="controladores.Ordenes_De_TrabajoControlador"%>
<%@page import="modelos.Ordenes_De_Trabajo"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_ordendetrabajo = Integer.parseInt(request.getParameter("id_ordendetrabajo"));
    //String sfecha_ordendetrabajo = request.getParameter("fecha_ordendetrabajo");
    //java.sql.Date fecha_ordendetrabajo = Utiles.stringToSqlDate(sfecha_ordendetrabajo);
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    
    Ordenes_De_Trabajo ordenes_de_trabajo = Ordenes_De_TrabajoControlador.buscarId(id_ordendetrabajo);
    if (ordenes_de_trabajo != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        ordenes_de_trabajo = new Ordenes_De_Trabajo();
        ordenes_de_trabajo.setId_ordendetrabajo(0);
        ordenes_de_trabajo.setDescripcion_ordendetrabajo("");
        ordenes_de_trabajo.setDireccion_ordendetrabajo("");
        ordenes_de_trabajo.setEstado_ordendetrabajo("");
        ordenes_de_trabajo.setFechainicio_ordendetrabajo(null);
        ordenes_de_trabajo.setFechaentrega_ordendetrabajo(null);
        
        Fiscales_De_Obras fiscaldeobra = new Fiscales_De_Obras();
        fiscaldeobra.setId_fiscaldeobra(0);
        fiscaldeobra.setNombre_fiscaldeobra("");
        fiscaldeobra.setApellido_fiscaldeobra("");
        fiscaldeobra.setCi_fiscaldeobra("");
        ordenes_de_trabajo.setFiscaldeobra(fiscaldeobra);
        
        Ciudades ciudad = new Ciudades();
        ciudad.setId_ciudad(0);
        ciudad.setNombre_ciudad("");
        ordenes_de_trabajo.setCiudad(ciudad);
    }
    
    String contenido_detalle = Ordenes_De_Trabajo_DetallesControlador.buscarIdObra(id_ordendetrabajo);
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    obj.put("id_ordendetrabajo", ordenes_de_trabajo.getId_ordendetrabajo());
    obj.put("id_fiscaldeobra", ordenes_de_trabajo.getFiscaldeobra().getId_fiscaldeobra());
    obj.put("nombre_fiscaldeobra", ordenes_de_trabajo.getFiscaldeobra().getNombre_fiscaldeobra());
    obj.put("apellido_fiscaldeobra", ordenes_de_trabajo.getFiscaldeobra().getApellido_fiscaldeobra());
    obj.put("ci_fiscaldeobra", ordenes_de_trabajo.getFiscaldeobra().getCi_fiscaldeobra());
    obj.put("id_ciudad", String.valueOf(ordenes_de_trabajo.getCiudad().getId_ciudad()));
    obj.put("nombre_ciudad", ordenes_de_trabajo.getCiudad().getNombre_ciudad());
    obj.put("estado_ordendetrabajo", ordenes_de_trabajo.getEstado_ordendetrabajo());
    obj.put("descripcion_ordendetrabajo", ordenes_de_trabajo.getDescripcion_ordendetrabajo());
    obj.put("direccion_ordendetrabajo", ordenes_de_trabajo.getDireccion_ordendetrabajo());
    obj.put("fechainicio_ordendetrabajo", String.valueOf(ordenes_de_trabajo.getFechainicio_ordendetrabajo()));
    obj.put("fechaentrega_ordendetrabajo", String.valueOf(ordenes_de_trabajo.getFechaentrega_ordendetrabajo()));
    obj.put("contenido_detalle", contenido_detalle);
    
    out.print(obj);
    out.flush();
%>
