package mwisnie.project.airqualitymonitorbackend.service.activation;

import lombok.RequiredArgsConstructor;
import mwisnie.project.airqualitymonitorbackend.entity.User;
import mwisnie.project.airqualitymonitorbackend.entity.UserActivationToken;
import mwisnie.project.airqualitymonitorbackend.repository.UserActivationTokenRepository;
import mwisnie.project.airqualitymonitorbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserActivationTokenServiceImpl implements UserActivationTokenService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserActivationTokenRepository tokenRepository;

    @Override
    public void createUserActivationToken(String userId, String token) {
        UserActivationToken userActivationToken = new UserActivationToken(userId, token);
        tokenRepository.save(userActivationToken);
    }

    @Override
    public UserActivationToken getAccountActivationTokenByToken(String token) {
        return tokenRepository.getUserActivationTokenByToken(token).orElse(null);
    }

    @Override
    public User getUserByToken(UserActivationToken activationToken) {
        return userRepository.findById(activationToken.getUserId()).orElse(null);
    }

}
