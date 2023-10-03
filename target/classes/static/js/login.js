window.addEventListener("load", ()=>{
    const formularioIngreso = document.getElementById('form-login');

    formularioIngreso.addEventListener("submit",(e)=>{
        e.preventDefault();
        
        //Necesito un FindByName()
        const nombreUsuario = document.getElementById('user');
        const contrasenia = document.getElementById('password');

        //El buscar es GET, pasa ID por endpoint
    })
})