package mwisnie.project.airqualitymonitorbackend.service.email;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@Validated
@Configuration
@ConfigurationProperties(prefix = "email")
public class EmailConfig {

    @NotBlank
    private String activationSubject;

    @NotBlank
    private String activationText;

    @NotBlank
    private String alertSubject;

    @NotBlank
    private String alertText;

}
