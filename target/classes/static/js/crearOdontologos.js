const btnVolver = document.getElementById('btn-volver');
btnVolver.addEventListener("click", ()=>{
    window.location.replace('/odontologos.html');
});

window.addEventListener('load', ()=>{
    crearOdontologo();
})

function crearOdontologo(){
    const formCrearOd = document.getElementById('add_new_dentist');
    formCrearOd.addEventListener('submit', (e) => {
        console.log(e);
        console.log("Entre a crear Odontologo del bnt guardar");
        e.preventDefault();
        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value
        };
        console.log(formData)
        const url = '/odontologos';
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
                console.log("Entre al fetch de guardar "+data);
                let successAlert = '<div class="alert alert-success alert-dismissible fade show" role="alert">' +
                '<div> <p>Se agregó correctamente el odontólogo</p> </div>' +
                '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>';
                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
            })
            .catch(error => {
                let errorAlert = '<div class="alert alert-warning alert-dismissible fade show" role="alert">' +
                '<div> <p>No se pudo guardar el odontólogo. </p></div>' +
                '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>';
                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
            })
        });
        const resetUploadForm = () => {
            document.querySelector('#matricula').value = "";
            document.querySelector('#nombre').value = "";
            document.querySelector('#apellido').value = "";
        }
}

