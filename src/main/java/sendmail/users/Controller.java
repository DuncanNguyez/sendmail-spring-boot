package sendmail.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class Controller {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public void register(@RequestBody User user){
        userService.register(user);
    }

    @GetMapping("/verify-email")
    public User verifyEmail(@RequestParam("token") String token){
       return userService.verifyEmail(token);
    }
}
