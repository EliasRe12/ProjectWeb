function validarFormulario() {
    var valor = true;
    if ($("#fechaentrega_ordendetrabajo").val().trim() === null) {
        valor = false;
        $("#mensajes").html("'Fecha Entrega' No puede estar vacío.");
        $("#id_ordendetrabajo").focus();
    }
    if ($("#descripcion_ordendetrabajo").val().trim() === "") {
        valor = false;
        $("#mensajes").html("'Campo Descripción' No puede estar vacío.");
        $("#id_ordendetrabajo").focus();
    }
    if ($("#estado_ordendetrabajo").val().trim() === "") {
        valor = false;
        $("#mensajes").html("'Campo Estado' No puede estar vacío.");
        $("#id_ordendetrabajo").focus();
    }
    if ($("#direccion_ordendetrabajo").val().trim() === "") {
        valor = false;
        $("#mensajes").html("'Campo Dirección' No puede estar vacío.");
        $("#id_ordendetrabajo").focus();
    }
    if ($("#id_ciudad").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("'ID Ciudad' No puede estar vacío.");
        $("#id_ordendetrabajo").focus();
    }
    if ($("#id_fiscaldeobra").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("'ID FiscalDeObra' No puede estar vacío.");
        $("#id_ordendetrabajo").focus();
    }
    
    return valor;
}

function fechaHoy(){

var hoy = new  new Date().toJSON().slice(0,10);

console.log(hoy);
 $("#fecha_pedido").val(hoy);
}

function addZero(i) {
    if (i < 10) {
        i = '0' + i;
    }
    return i;
}

function buscarIdOrden_De_Trabajo() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_ordendetrabajo").val(json.id_ordendetrabajo);
            $("#id_fiscaldeobra").val(json.id_fiscaldeobra);
            $("#nombre_fiscaldeobra").val(json.nombre_fiscaldeobra);
            $("#apellido_fiscaldeobra").val(json.apellido_fiscaldeobra);
            $("#ci_fiscaldeobra").val(json.ci_fiscaldeobra);
            $("#descripcion_ordendetrabajo").val(json.descripcion_ordendetrabajo);
            $("#id_ciudad").val(json.id_ciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);
            $("#direccion_ordendetrabajo").val(json.direccion_ordendetrabajo);
            $("#estado_ordendetrabajo").val(json.estado_ordendetrabajo);
            $("#fechainicio_ordendetrabajo").val(json.fechainicio_ordendetrabajo);
            $("#fechaentrega_ordendetrabajo").val(json.fechaentrega_ordendetrabajo);
            var fecha1 = $("fechainicio_ordendetrabajo").serialize();
            var fecha2 = $("fechaentrega_ordendetrabajo").serialize();
            
            $("#contenidoDetalle").html(json.contenido_detalle);
            if (json.nuevo === "true") {
               $("#botonAgregar").prop('disabled', false);
               $("#botonModificar").prop('disabled', true);
               $("#botonEliminar").prop('disabled', true);
               siguienteCampo("#id_tipoordendetrabajo", "#botonAgregar", true);
               $("#detalle").prop('hidden', true);
           } else {
               $("#botonAgregar").prop('disabled', true);
               $("#botonModificar").prop('disabled', false);
               $("#botonEliminar").prop('disabled', true);
               siguienteCampo("#id_tipoordendetrabajo", "#botonModificar", true);
               $("#detalle").prop('hidden', false);
           }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}
function buscarNombreOrden_De_Trabajo() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombre.jsp',
       data: datosFormulario,
       dataType: 'json',
       beforeSend: function (objeto) {
           $("#mensajes").html("Enviando datos al Servidor ...");
           $("#contenidoBusqueda").css("display", "none");
       },
       success: function (json){
           $("#mensajes").html(json.mensaje);
           $("#contenidoBusqueda").html(json.contenido);
           $("#contenidoBusqueda").fadeIn("slow");
           $("tbody tr").on("click", function() {
              var id = $(this).find("td:first").html();
              $("#panelBuscar").html("");
              $("#id_ordendetrabajo").val(id);
              $("#descripcion_ordendetrabajo").focus();
              buscarIdOrden_De_Trabajo();
              $("#buscar").fadeOut("slow");
              $("#panelPrograma").fadeIn("slow");
          });
       },
       error: function(e) {
           $("#mensajes").html("No se pudo modificar los datos.");
       },
       complete: function(objeto, exito, error) {
           if (exito === "success"){
           }
       }
    });
}
function agregarOrden_De_Trabajo() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#botonAgregar").prop('disabled', true);
            $("#detalle").prop('hidden', false);
            $("#id_ordendetrabajo").val(json.id_ordendetrabajo);
            buscarIdOrden_De_Trabajo();
            //$("#id_ordendetrabajo").focus;
            //$("#id_ordendetrabajo").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error){
            if (exito === "success") {
            }
        }
    });
}

function modificarOrden_De_Trabajo() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/modificar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_ordendetrabajo").focus();
            $("#id_ordendetrabajo").select();
            buscarIdOrden_De_Trabajo();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            if (exito === "success") {
            }
        }
    });
}

function eliminarOrden_De_Trabajo() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            eliminarOrden_De_Trabajo_Detalle();
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#id_ordendetrabajo").focus();
            $("#id_ordendetrabajo").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            if (exito === "success") {
            }
        }
    });
}

function buscarIdFiscal_De_Obra() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdFiscal_De_Obra.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_fiscaldeobra").val(json.id_fiscaldeobra);
            $("#nombre_fiscaldeobra").val(json.nombre_fiscaldeobra);
            $("#apellido_fiscaldeobra").val(json.apellido_fiscaldeobra);
            $("#ci_fiscaldeobra").val(json.ci_fiscaldeobra);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}

function buscarIdCiudad() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCiudad.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_ciudad").val(json.id_ciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}

function buscarNombreCiudad() {
    var datosFormulario = $("#formBuscar").serialize();
  
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombreCiudad.jsp',
       data: datosFormulario,
       dataType: 'json',
       beforeSend: function (objeto) {
           $("#mensajes").html("Enviando datos al Servidor ...");
           $("#contenidoBusqueda").css("display", "none");
       },
       success: function (json){
           $("#mensajes").html(json.mensaje);
           $("#contenidoBusqueda").html(json.contenido);
           $("#contenidoBusqueda").fadeIn("slow");
           $("tbody tr").on("click", function(){
              var id = $(this).find("td:first").html();
              $("#panelBuscar").html("");
              $("#id_ciudad").val(id);
              $("#nombre_ciudad").focus();
              buscarIdCiudad();
              $("#buscar").fadeOut("slow");
              $("#panelPrograma").fadeIn("slow");
          });
       },
       error: function(e) {
           $("#mensajes").html("No se pudo modificar los datos.");
       },
       complete: function(objeto, exito, error) {
           if (exito === "success"){
               
           }
       }
    });
}

function buscarNombreFiscal_De_Obra() {
    var datosFormulario = $("#formBuscar").serialize();
  
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombreFiscal_De_Obra.jsp',
       data: datosFormulario,
       dataType: 'json',
       beforeSend: function (objeto) {
           $("#mensajes").html("Enviando datos al Servidor ...");
           $("#contenidoBusqueda").css("display", "none");
       },
       success: function (json){
           $("#mensajes").html(json.mensaje);
           $("#contenidoBusqueda").html(json.contenido);
           $("#contenidoBusqueda").fadeIn("slow");
           $("tbody tr").on("click", function(){
              var id = $(this).find("td:first").html();
              $("#panelBuscar").html("");
              $("#id_fiscaldeobra").val(id);
              $("#nombre_fiscaldeobra").focus();
              buscarIdFiscal_De_Obra();
              $("#buscar").fadeOut("slow");
              $("#panelPrograma").fadeIn("slow");
          });
       },
       error: function(e) {
           $("#mensajes").html("No se pudo modificar los datos.");
       },
       complete: function(objeto, exito, error) {
           if (exito === "success"){
               
           }
       }
    });
}

 function limpiarFormulario() {
    $("#id_ordendetrabajo").val("0");
    $("#id_fiscaldeobra").val("0");
    $("#nombre_fiscaldeobra").val("");
    $("#apellido_fiscaldeobra").val("");
    $("#ci_fiscaldeobra").val("0");
    $("#descripcion_ordendetrabajo").val("");
    $("#direccion_ordendetrabajo").val("");
    $("#id_ciudad").val("0");
    $("#nombre_ciudad").val("");
    $("#estado_ordendetrabajo").val("");
    $("#fechainicio_ordendetrabajo").val("");
    $("#fechaentrega_ordendetrabajo").val("");
}
function agregarLinea() {
    $("#id_ordendetrabajodetalle").val("0");
    $("#id_personal").val("0");
    $("#nombre_personal").val("");
    $("#apellido_personal").val("");
    $("#ci_personal").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    //siguienteCampo("#horas_detallepedido", "#botonAgregarLinea", true);
}
function editarLinea(id) {
    $("#id_ordendetrabajodetalle").val(id);
    $("#id_personal").val("0");
    $("#nombre_personal").val("");
    $("#apellido_personal").val("");
    $("#ci_personal").val("0")
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdOrden_De_Trabajo_Detalle();
    //siguienteCampo("#cantidad_ordendetrabajodetalle", "#botonModificarLinea", true);
}

//Orden_De_Trabajos Detalles
function buscarIdOrden_De_Trabajo_Detalle() {
    var datosFormulario = $("#formLinea").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdOrden_De_Trabajo_Detalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_ordendetrabajodetalle").val(json.id_ordendetrabajodetalle);
            //$("#id_ordendetrabajo").val(json.id_ordendetrabajo);
            $("#id_personal").val(json.id_personal);
            $("#nombre_personal").val(json.nombre_personal);
            $("#apellido_personal").val(json.apellido_personal);
            $("#ci_personal").val(json.ci_personal);
            
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}

function buscarIdOrden_De_TrabajoOrden_De_Trabajo_Detalle() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdOrden_De_TrabajoOrden_De_Trabajo_Detalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoDetalle").html(json.contenido_detalle);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}
function agregarOrden_De_Trabajo_Detalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_ordendetrabajo = $("#id_ordendetrabajo").val();
    datosFormulario += "&id_ordendetrabajo=" + id_ordendetrabajo;

    $.ajax({
        type: 'POST',
        url: 'jsp/agregarOrden_De_Trabajo_Detalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdOrden_De_Trabajo();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            if(exito === "success") {
            }
        }
    });
}

function modificarOrden_De_Trabajo_Detalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_ordendetrabajo = $("#id_ordendetrabajo").val();
    datosFormulario += "&id_ordendetrabajo=" + id_ordendetrabajo;
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarOrden_De_Trabajo_Detalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdOrden_De_Trabajo();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            if (exito === "success") {
            }
        }
    });
}

function eliminarOrden_De_Trabajo_Detalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_ordendetrabajo = $("#id_ordendetrabajo").val();
    datosFormulario += "&id_ordendetrabajo=" + id_ordendetrabajo;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarOrden_De_Trabajo_Detalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdOrden_De_Trabajo();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            if (exito === "success") {
            }
        }
    });
}

function buscarIdPersonal() {
    var datosFormulario = $("#formLinea").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPersonal.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_personal").val(json.id_personal);
            $("#nombre_personal").val(json.nombre_personal);
            $("#apellido_personal").val(json.apellido_personal);
            $("#telefono_personal").val(json.telefono_personal);
            $("#direccion_personal").val(json.direccion_personal);
            $("#ci_personal").val(json.ci_personal);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}

function buscarNombrePersonal() {
    var datosFormulario = $("#formBuscar").serialize();
  
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombrePersonal.jsp',
       data: datosFormulario,
       dataType: 'json',
       beforeSend: function (objeto) {
           $("#mensajes").html("Enviando datos al Servidor ...");
           $("#contenidoBusqueda").css("display", "none");
       },
       success: function (json){
           $("#mensajes").html(json.mensaje);
           $("#contenidoBusqueda").html(json.contenido);
           $("#contenidoBusqueda").fadeIn("slow");
           $("tbody tr").on("click", function(){
              var id = $(this).find("td:first").html();
              $("#panelBuscar").html("");
              $("#id_personal").val(id);
              $("#nombre_personal").focus();
              buscarIdPersonal();
              $("#buscar").fadeOut("slow");
              $("#panelPrograma").fadeIn("slow");
          });
       },
       error: function(e) {
           $("#mensajes").html("No se pudo modificar los datos.");
       },
       complete: function(objeto, exito, error) {
           if (exito === "success"){
               
           }
       }
    });
}




