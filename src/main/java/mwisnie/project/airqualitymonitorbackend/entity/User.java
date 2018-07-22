package mwisnie.project.airqualitymonitorbackend.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Getter
@Setter
@Document
public class User {

    @Id
    private String id;

    @NotBlank
    @Indexed(unique = true)
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

    private List<String> stationIds;

    private boolean active;

    private boolean alertOn;

    public User() {
        this.active = false;
    }

}
