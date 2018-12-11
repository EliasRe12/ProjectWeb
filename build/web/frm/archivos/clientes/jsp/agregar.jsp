<%@page import="modelos.Nacionalidades"%>
<%@page import="modelos.Ciudades"%>
<%@page import="controladores.ClientesControlador"%>
<%@page import="modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
    String nombre_cliente = request.getParameter("nombre_cliente");
    String apellido_cliente = request.getParameter("apellido_cliente");
    String direccion_cliente = request.getParameter("direccion_cliente");
    String telefono_cliente = request.getParameter("telefono_cliente");
    String ci_cliente = request.getParameter("ci_cliente");
    String ruc_cliente = request.getParameter("ruc_cliente");
    int id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));
    int id_nacionalidad = Integer.parseInt(request.getParameter("id_nacionalidad"));
    
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Clientes cliente = new Clientes();
    cliente.setId_cliente(id_cliente);
    cliente.setNombre_cliente(nombre_cliente);
    cliente.setApellido_cliente(apellido_cliente);
    cliente.setDireccion_cliente(direccion_cliente);
    cliente.setTelefono_cliente(telefono_cliente);
    cliente.setCi_cliente(ci_cliente);
    cliente.setRuc_cliente(ruc_cliente);
    
    Nacionalidades nacionalidad = new Nacionalidades();
    nacionalidad.setId_nacionalidad(id_nacionalidad);
    cliente.setNacionalidad(nacionalidad);
    
    Ciudades ciudad = new Ciudades();
    ciudad.setId_ciudad(id_ciudad);
    cliente.setCiudad(ciudad);
      
    if (ClientesControlador.agregar(cliente)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>