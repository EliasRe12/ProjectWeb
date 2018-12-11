function validarFormulario() {
    var valor = true;
    if ($("#telefono_fiscaldeobra").val().length < 2) {
        valor = false;
        $("#mensajes").html("'Campo Teléfono' No puede estar vacio.");
        $("#id_fiscaldeobra").focus();
    }
    if ($("#ci_fiscaldeobra").val().length < 2) {
        valor = false;
        $("#mensajes").html("'Campo C.I.' No puede estar vacio.");
        $("#id_fiscaldeobra").focus();
    }
    if ($("#apellido_fiscaldeobra").val().length < 2) {
        valor = false;
        $("#mensajes").html("'Campo Apellido' No puede estar vacio.");
        $("#id_fiscaldeobra").focus();
    }
    if ($("#nombre_fiscaldeobra").val().trim() === "") {
        valor = false;
        $("#mensajes").html("'Campo Nombre' No puede estar vacio.");
        $("#id_fiscaldeobra").focus();
    }
    return valor;
}

function agregarFiscal_De_Obra() {
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
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_fiscaldeobra").focus();
            $("#id_fiscaldeobra").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            $("#id_fiscaldeobra").focus();
        }
    });
}

function modificarFiscal_De_Obra() {
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
            limpiarFormulario();
            $("#id_fiscaldeobra").focus();
            $("#id_fiscaldeobra").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            
        }
    });
}

function eliminarFiscal_De_Obra() {
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
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_fiscaldeobra").focus();
            $("#id_fiscaldeobra").select();
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
        url: 'jsp/buscarId.jsp',
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
            $("#telefono_fiscaldeobra").val(json.telefono_fiscaldeobra);
            if (json.nuevo === "true") {
               $("#botonAgregar").prop('disabled', false);
               $("#botonModificar").prop('disabled', true);
               $("#botonEliminar").prop('disabled', true);
               siguienteCampo("#nombre_fiscaldeobra", "#botonAgregar", true);
           } else {
               $("#botonAgregar").prop('disabled', true);
               $("#botonModificar").prop('disabled', false);
               $("#botonEliminar").prop('disabled', true);
               siguienteCampo("#nombre_fiscaldeobra", "#botonModificar", true);
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

function buscarNombreFiscal_De_Obra() {
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
     $("#id_fiscaldeobra").val("0");
     $("#nombre_fiscaldeobra").val("");
     $("#apellido_fiscaldeobra").val("");
     $("#ci_fiscaldeobra").val("");
     $("#telefono_fiscaldeobra").val("");
 }
