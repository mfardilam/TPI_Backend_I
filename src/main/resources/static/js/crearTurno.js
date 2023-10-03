const btnVolver = document.getElementById('btn-volver');
btnVolver.addEventListener("click", ()=>{
    window.location.replace('/turnos.html');
});

window.addEventListener('load', ()=>{
    crearTurno();
})

function crearTurno(){
    const form = document.querySelector('#add_new_turno');
        form.addEventListener('submit', (e) => {
        console.log(e)
            e.preventDefault();
            const formData = {
                paciente_id: document.querySelector('#patientId').value,
                odontologo_id: document.querySelector('#dentistId').value,
                fecha: document.querySelector('#date').value,
            };
            const url = '/turnos';
            const settings = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData)
            }
            fetch(url, settings)
                .then(response => response.json())
                .then(data => {
                    let successAlert = '<div class="alert alert-success alert-dismissible fade show" role="alert">' +
                    '<div> <p>Se agregó correctamente el turno</p> </div>' +
                    '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>';
                    document.querySelector('#response').innerHTML = successAlert;
                    document.querySelector('#response').style.display = "block";
                    resetUploadForm();
                })
                .catch(error => {
                    let errorAlert = '<div class="alert alert-warning alert-dismissible fade show" role="alert">' +
                    '<div> <p>No se pudo guardar el turno. </p></div>' +
                    '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>';
                    document.querySelector('#response').innerHTML = errorAlert;
                    document.querySelector('#response').style.display = "block";
                    resetUploadForm();
                })
        });
        const resetUploadForm = () => {
            document.querySelector('#patientId').value = "";
            document.querySelector('#dentistId').value = "";
            document.querySelector('#date').value = "";
        }
}

