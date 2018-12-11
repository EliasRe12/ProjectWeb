<%@page import="modelos.Presupuestos_Detalles"%>
<%@page import="controladores.Presupuestos_SubdetallesControlador"%>
<%@page import="modelos.Presupuestos_Subdetalles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_presupuestosubdetalle = Integer.parseInt(request.getParameter("id_presupuestosubdetalle"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    
    Presupuestos_Subdetalles presupuestosubdetalle = Presupuestos_SubdetallesControlador.buscarId(id_presupuestosubdetalle);
    if (presupuestosubdetalle.getId_presupuestosubdetalle()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else {
        presupuestosubdetalle.setId_presupuestosubdetalle(0);
        presupuestosubdetalle.setCostovidrio_presupuestosubdetalle(0);
        presupuestosubdetalle.setTotalvidrio_presupuestosubdetalle(0);
        presupuestosubdetalle.setLargop_presupuestosubdetalle(0);
        presupuestosubdetalle.setAnchop_presupuestosubdetalle(0);
        presupuestosubdetalle.setCostoherrajes_presupuestosubdetalle(0);
        presupuestosubdetalle.setMo_presupuestosubdetalle(0);
        presupuestosubdetalle.setColocacion_presupuestosubdetalle(0);
        
        Presupuestos_Detalles presupuestodetalle = new Presupuestos_Detalles();
        presupuestodetalle.setId_presupuestodetalle(0);
        presupuestosubdetalle.setPresupuestodetalle(presupuestodetalle);
        
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    obj.put("id_presupuestosubdetalle", presupuestosubdetalle.getId_presupuestosubdetalle());
    System.out.println("idSUBDETALLE"+  presupuestosubdetalle.getId_presupuestosubdetalle());
    obj.put("costovidrio_presupuestosubdetalle", presupuestosubdetalle.getCostovidrio_presupuestosubdetalle());
    obj.put("totalvidrio_presupuestosubdetalle", presupuestosubdetalle.getTotalvidrio_presupuestosubdetalle());
    obj.put("largop_presupuestosubdetalle", presupuestosubdetalle.getLargop_presupuestosubdetalle());
    obj.put("anchop_presupuestosubdetalle", presupuestosubdetalle.getAnchop_presupuestosubdetalle());
    obj.put("totalperfil_presupuestosubdetalle", presupuestosubdetalle.getTotalperfil_presupuestosubdetalle());
    obj.put("costoherrajes_presupuestosubdetalle", presupuestosubdetalle.getCostoherrajes_presupuestosubdetalle());
    obj.put("mo_presupuestosubdetalle", presupuestosubdetalle.getMo_presupuestosubdetalle());
    obj.put("colocacion_presupuestosubdetalle", presupuestosubdetalle.getColocacion_presupuestosubdetalle());
    
    //PRESUPUESTO DETALLE
    obj.put("id_presupuestodetalle", presupuestosubdetalle.getPresupuestodetalle().getId_presupuestodetalle());
    obj.put("alto_presupuestodetalle", presupuestosubdetalle.getPresupuestodetalle().getAlto_presupuestodetalle());
    obj.put("ancho_presupuestodetalle", presupuestosubdetalle.getPresupuestodetalle().getAncho_presupuestodetalle());
    obj.put("m2_presupuestodetalle", presupuestosubdetalle.getPresupuestodetalle().getM2_presupuestodetalle());
    obj.put("cantidad_presupuestodetalle", presupuestosubdetalle.getPresupuestodetalle().getCantidad_presupuestodetalle());
    obj.put("precio_presupuestodetalle", presupuestosubdetalle.getPresupuestodetalle().getPrecio_presupuestodetalle());
    
    out.print(obj);
    out.flush();
%>
