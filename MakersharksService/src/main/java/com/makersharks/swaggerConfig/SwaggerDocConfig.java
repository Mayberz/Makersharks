
package com.makersharks.swaggerConfig;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(info = @Info( 
		  title =  "MakerSharks Service",
		  contact = @Contact( name = "Maker Sharks",
		  email = "makersharks@gmail.com"),
		  license = @License(name =  "BT-7274"),
          version = "V1"),
          security = @SecurityRequirement(name = "Authentication"))
@SecurityScheme(name = "Authentication",in = SecuritySchemeIn.HEADER,
                 type = SecuritySchemeType.HTTP,
                  bearerFormat = "JWT",scheme = "bearer")
public class SwaggerDocConfig {
	


}
