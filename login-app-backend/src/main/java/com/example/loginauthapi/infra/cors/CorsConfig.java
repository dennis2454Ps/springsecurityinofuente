package com.example.loginauthapi.infra.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/*La cereza del pastel:

 Esta clase está configurada como una clase de configuración de Spring.




@Configuration
se utiliza comunmente para definir beans, en nuestro caso
la usamos para la definicion de nuestra configuracion  de spring security
utilizando el método addCorsMappings
para configurar el cors
*/
@Configuration
// Método para configurar el mapeo de CORS (Cross-Origin Resource Sharing).
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
// Permite el acceso CORS a todas las rutas de la aplicación.
        registry.addMapping("/**")
    /*Posteriormente permitemos que la siguiente ruta con el cuerpo
    "http://localhost:4200" pueda acceder a todas las rutas, colocar una ruta
    diferente se nos denegaria el acceso */
                .allowedOrigins("http://localhost:4200")
    //la ruta anterior puede ejecutar todos nuestros metodos
                .allowedMethods("GET", "POST", "DELETE", "PUT");
    }
}
