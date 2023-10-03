const btnOdontologos = document.getElementById('btn-odotologos');
const btnPacientes = document.getElementById('btn-pacientes');
const btnTurnos = document.getElementById('btn-turnos');

btnOdontologos.addEventListener("click", ()=>{
    window.location.replace('/odontologos.html');
});

btnPacientes.addEventListener("click", ()=>{
    window.location.replace('/pacientes.html');
});

btnTurnos.addEventListener("click", ()=>{
    window.location.replace('/turnos.html');
});



