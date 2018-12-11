<%@page import="controladores.Presupuestos_SubdetallesControlador"%>
<%@page import="modelos.Productos"%>
<%@page import="modelos.Sectores"%>
<%@page import="modelos.Ubicaciones"%>
<%@page import="modelos.Colores"%>
<%@page import="modelos.Presupuestos"%>
<%@page import="controladores.Presupuestos_DetallesControlador"%>
<%@page import="modelos.Presupuestos_Detalles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_presupuestodetalle = Integer.parseInt(request.getParameter("id_presupuestodetalle"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    
    Presupuestos_Detalles presupuestodetalle = Presupuestos_DetallesControlador.buscarId(id_presupuestodetalle);
    if (presupuestodetalle != null){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        presupuestodetalle = new Presupuestos_Detalles();
        presupuestodetalle.setId_presupuestodetalle(0);
        presupuestodetalle.setCantidad_presupuestodetalle(0);
        presupuestodetalle.setM2_presupuestodetalle("");
        presupuestodetalle.setAncho_presupuestodetalle("");
        presupuestodetalle.setAlto_presupuestodetalle("");     
        presupuestodetalle.setAlto_presupuestodetalle("");     
        presupuestodetalle.setDetalle_presupuestodetalle("");
        presupuestodetalle.setPrecio_presupuestodetalle(0);
        presupuestodetalle.setColor_presupuestodetalle("");
        
        Ubicaciones ubicacion = new Ubicaciones();
        ubicacion.setId_ubicacion(0);
        ubicacion.setNombre_ubicacion("");
        presupuestodetalle.setUbicacion(ubicacion);
        
        Sectores sector = new Sectores();
        sector.setId_sector(0);
        sector.setNombre_sector("");
        presupuestodetalle.setSector(sector);
        
    }
    
    String contenido_detalle1 = Presupuestos_SubdetallesControlador.buscarIdPresupuestoDetalle(id_presupuestodetalle);
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    obj.put("id_presupuestodetalle",presupuestodetalle.getId_presupuestodetalle());
    obj.put("detalle_presupuestodetalle", presupuestodetalle.getDetalle_presupuestodetalle());
    obj.put("cantidad_presupuestodetalle", String.valueOf(presupuestodetalle.getCantidad_presupuestodetalle()));
    obj.put("ancho_presupuestodetalle", presupuestodetalle.getAncho_presupuestodetalle());
    obj.put("alto_presupuestodetalle", presupuestodetalle.getAlto_presupuestodetalle());
    obj.put("m2_presupuestodetalle", presupuestodetalle.getM2_presupuestodetalle());        
    obj.put("precio_presupuestodetalle", presupuestodetalle.getPrecio_presupuestodetalle());
    obj.put("color_presupuestodetalle", presupuestodetalle.getColor_presupuestodetalle());
    obj.put("mm_presupuestodetalle", presupuestodetalle.getMm_presupuestodetalle());
    
    obj.put("id_ubicacion", String.valueOf(presupuestodetalle.getUbicacion().getId_ubicacion()));
    obj.put("nombre_ubicacion", presupuestodetalle.getUbicacion().getNombre_ubicacion());
    obj.put("id_sector", String.valueOf(presupuestodetalle.getSector().getId_sector()));
    obj.put("nombre_sector", presupuestodetalle.getSector().getNombre_sector());

    
    obj.put("contenido_detalle1", contenido_detalle1);
    //System.out.println("contenido Detalle"+ contenido_detalle1);
    out.print(obj);
    out.flush();
%>
