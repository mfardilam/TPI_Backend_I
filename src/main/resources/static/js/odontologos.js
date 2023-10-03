const btnCrearO = document.getElementById('btn-crearOd');
window.addEventListener('load', ()=>{
    renderizarOdontologos();
    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/odontologos.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })
    actualizarOdontologo();
    })
btnCrearO.addEventListener("click", ()=>{
console.log("El evento de crear odontologo - btn funciona")
    window.location.replace('/crearOdontologo.html');
});

function renderizarOdontologos(){

    fetch('/odontologos')
            .then(respuesta => respuesta.json())
            .then(datos =>{
                console.log(datos)
                for(odontologo of datos){
                    let table = document.getElementById("dentistTable");
                    let odontologoRow = table.insertRow();
                    let tr_id = 'tr_' + odontologo.id;
                    odontologoRow.id = tr_id;

                    let deleteButton = '<button' +
                                              ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
                                              ' type="button" onclick="eliminarOdontologo('+odontologo.id+')" class="btn btn-danger btn_delete">' +
                                              '&times' +
                                              '</button>';

                    let updateButton = '<button' +
                                              ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                                              ' type="button" onclick="buscarOdontologo('+odontologo.id+')" class="btn btn-info btn_id">' +
                                              "✏" +
                                              '</button>';

                    odontologoRow.innerHTML =
                            '<td class=\"td_id\">' + odontologo.id + '</td>' +
                            '<td class=\"td_nombre\">' + odontologo.nombre + '</td>' +
                            '<td class=\"td_apellido\">' + odontologo.apellido + '</td>' +
                            '<td class=\"td_matricula\">' + odontologo.matricula + '</td>' +
                            '<td>' + updateButton + '</td>' +
                            '<td>' + deleteButton + '</td>';
                }

                });
}
function actualizarOdontologo(){
       const form = document.querySelector('#update_dentist_form');
       form.addEventListener('submit', (e) => {
           e.preventDefault();
           const formData = {
               id: document.querySelector('#dentist_id').value,
               nombre: document.querySelector('#name').value,
               apellido: document.querySelector('#lastName').value,
               matricula: document.querySelector('#license').value
               };
           const url = '/odontologos';
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
               window.location.replace("/odontologos.html");
           })
       })
}
function buscarOdontologo(id) {
    const url = '/odontologos/'+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
        let odontologo = data;
        console.log(odontologo);
        document.querySelector('#dentist_id').value = id;
        document.querySelector('#license').value = odontologo.matricula;
        document.querySelector('#name').value = odontologo.nombre;
        document.querySelector('#lastName').value = odontologo.apellido;
        document.querySelector('#div_dentist_updating').style.display = "block";
        })
    .catch(error => {
        alert("Error: " + error);
    })
}

function eliminarOdontologo(id){
    console.log("Entre a eliminar desde front")
    const url='/odontologos/'+id
    const settings = {
            method:'DELETE'
        }
        fetch(url,settings)
        .then(response => {
            let row_id = "#tr_" + id;
            document.querySelector(row_id).remove();
        })
}


