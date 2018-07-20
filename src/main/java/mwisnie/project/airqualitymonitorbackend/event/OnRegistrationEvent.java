package mwisnie.project.airqualitymonitorbackend.event;

import lombok.Getter;
import mwisnie.project.airqualitymonitorbackend.entity.User;
import org.springframework.context.ApplicationEvent;

@Getter
public class OnRegistrationEvent extends ApplicationEvent {

    private User user;

    public OnRegistrationEvent(User user) {
        super(user);
        this.user = user;
    }

}
