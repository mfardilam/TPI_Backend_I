const btnCrearTurno = document.getElementById('btn-crear');
window.addEventListener('load', function () {
    renderizarTurnos();
    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/turnos.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })
    })
btnCrearTurno.addEventListener("click", ()=>{
     console.log("El evento de crear paciente - btn funciona")
     window.location.replace('/crearTurno.html');
 });

 function renderizarTurnos(){
    fetch("/turnos")
      .then(response => response.json())
      .then(data => {
         for(turno of data){
            let table = document.getElementById("turnoTable");
            let turnoRow =table.insertRow();
            let tr_id = 'tr_' + turno.id;
            turnoRow.id = tr_id;

            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                                      ' type="button" onclick="eliminarTurno('+turno.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';

            turnoRow.innerHTML =
                    '<td class=\"td_id\">' + turno.id + '</td>' +
                    '<td class=\"td_pacienteId\">' + turno.paciente_id + '</td>' +
                    '<td class=\"td_odontologoId\">' + turno.odontologo_id + '</td>' +
                    '<td class=\"td_fecha\">' + turno.fecha + '</td>' +
                    '<td>' + deleteButton + '</td>';
        };
    })
 }

 function eliminarTurno(id){
     console.log("Entre a eliminar desde front")
     const url='/turnos/'+id
     const settings = {
             method:'DELETE'
         }
         fetch(url,settings)
         .then(response => {
             let row_id = "#tr_" + id;
             document.querySelector(row_id).remove();
         })
 }