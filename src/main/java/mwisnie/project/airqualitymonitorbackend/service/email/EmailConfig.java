package mwisnie.project.airqualitymonitorbackend.service.email;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@Validated
@Configuration
@ConfigurationProperties(prefix = "activation-mail")
public class EmailConfig {

    @NotBlank
    private String subject;

    @NotBlank
    private String text;

}
