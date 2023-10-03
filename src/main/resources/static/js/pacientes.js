const btnCrearPaciente = document.getElementById('btn-crear');
window.addEventListener('load', function () {
    renderizarPacientes();
    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/pacientes.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })
    actualizarPaciente();
    renderizarDomicilios();
    })
btnCrearPaciente.addEventListener("click", ()=>{
     console.log("El evento de crear paciente - btn funciona")
     window.location.replace('/crearPaciente.html');
 });
function renderizarPacientes(){
    fetch('/pacientes')
      .then(response => response.json())
      .then(data => {
         for(paciente of data){
            let table = document.getElementById("patientTable");
            let pacienteRow =table.insertRow();
            let tr_id = 'tr_' + paciente.id;
            pacienteRow.id = tr_id;

            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +
                                      ' type="button" onclick="borrarPaciente('+paciente.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';

            let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                                      ' type="button" onclick="buscarPaciente('+paciente.id+')" class="btn btn-info btn_id">' +
                                      "✏" +
                                      '</button>';

            pacienteRow.innerHTML =
                    '<td class=\"td_id\">' + paciente.id + '</td>' +
                    '<td class=\"td_nombre\">' + paciente.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_apellido\">' + paciente.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_dni\">' + paciente.dni.toUpperCase() + '</td>' +
                    '<td class=\"td_fechaIngreso\">' + paciente.fechaAlta + '</td>' +
                    '<td>' + updateButton + '</td>' +
                    '<td>' + deleteButton + '</td>';
        };
    })
}
function actualizarPaciente(){
    const form = document.querySelector('#update_patient_form');
        form.addEventListener('submit', (e) => {
            e.preventDefault();
            const formData = {
                id: document.querySelector('#patient_id').value,
                nombre: document.querySelector('#nombre').value,
                apellido: document.querySelector('#apellido').value,
                dni: document.querySelector('#dni').value,
                fechaAlta: document.querySelector('#date_Ingreso').value,
                domicilio: {calle: document.querySelector('#calle').value,
                            numero: document.querySelector('#numero').value,
                            localidad: document.querySelector('#localidad').value,
                            provincia: document.querySelector('#provincia').value
                            }
            };
            const url = '/pacientes';
            const settings = {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData)
            }
            fetch(url,settings)
            .then(response => {
                if (response.status == 404){
                    alert("Las modificaciones no fueron ejecutadas");
                }
                window.location.replace("/pacientes.html");
            })
        })
}

function buscarPaciente(id) {
    const url = '/pacientes/'+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
        let paciente = data;
        document.querySelector('#patient_id').value=id;
        document.querySelector('#nombre').value = paciente.nombre;
        document.querySelector('#apellido').value = paciente.apellido;
        document.querySelector('#dni').value = paciente.dni;
        document.querySelector('#date_Ingreso').value=paciente.fechaAlta;
        document.querySelector('#div_patient_updating').style.display = "block";
        })
    .catch(error => {
        alert("Error: " + error);
    })
}

function borrarPaciente(id){
    const url='/pacientes/'+id;
    const settings = {
        method:'DELETE'
    }
    fetch(url,settings)
    .then(response => {
        let row_id = "#tr_" + id;
        document.querySelector(row_id).remove();
    })
}

function renderizarDomicilios(){
    fetch('/domicilios')
          .then(response => response.json())
          .then(data => {
             for(domicilio of data){
                let table = document.getElementById("addressTable");
                let domicilioRow =table.insertRow();
                let tr_id = 'tr_' + domicilio.id;
                domicilioRow.id = tr_id;

                domicilioRow.innerHTML =
                        '<td class=\"td_id\">' + domicilio.id + '</td>' +
                        '<td class=\"td_calle\">' + domicilio.calle + '</td>' +
                        '<td class=\"td_numero\">' + domicilio.numero + '</td>' +
                        '<td class=\"td_localidad\">' + domicilio.localidad + '</td>' +
                        '<td class=\"td_provincia\">' + domicilio.provincia + '</td>';
            };
        })
}