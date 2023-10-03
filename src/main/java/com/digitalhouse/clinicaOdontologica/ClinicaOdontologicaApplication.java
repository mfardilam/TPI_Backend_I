package com.digitalhouse.clinicaOdontologica;

import com.digitalhouse.clinicaOdontologica.controller.DomicilioController;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class  ClinicaOdontologicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicaOdontologicaApplication.class, args);
		/*Se dejó en la carpeta 'POSTMAN' la colección para probar las pruebas con
		las credenciales ya guardadas de acuerdo a las que insertaron en la BBDD por defecto,
		para probar en Postman no olvidar descaomentar la sección de SecurityConfiguracion correspondiente
		y comentar la del front
		 */


	}

}
