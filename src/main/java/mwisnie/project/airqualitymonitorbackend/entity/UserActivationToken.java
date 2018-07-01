package mwisnie.project.airqualitymonitorbackend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
public class UserActivationToken {

    @Id
    private String id;

    private String userId;

    private String token;

    public UserActivationToken(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

}
