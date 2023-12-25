package sendmail.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sendmail.tokens.Token;
import sendmail.tokens.TokenRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;

    @Autowired
    private TokenRepository tokenRepository;

    public void register(User user) {
        userRepository.save(user);
        Token token = new Token(user);
        tokenRepository.save(token);
        emailService.sendHtmlEmailWithEmbeddedFiles(user.getName(), user.getEmail(), token.getToken());
    }

    @Transactional
    public User verifyEmail(String token) {
        Token t = tokenRepository.findByToken(token);
        User user =  t.getUser();
        user.setEmailVerify(true);
        return userRepository.save(user);
    }
}
