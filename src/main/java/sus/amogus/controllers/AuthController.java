package sus.amogus.controllers;

import sus.amogus.models.UserDTO;
import sus.amogus.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.security.auth.login.CredentialException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    /**
     *  Обрабатывает запрос на аутентификацию пользователя и возвращает JWT.
     *  @param userDTO данные пользователя для аутентификации.
     *  @return ResponseEntity с JWT в случае успешной аутентификации.
     *  @throws CredentialException в случае неверных учетных данных.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) throws CredentialException {
        String jwt = userService.loginUser(userDTO);
        return ResponseEntity.ok(jwt);
    }

    /**
     *  Обрабатывает запрос на регистрацию нового пользователя.
     *  @param userDTO данные пользователя для регистрации.
     *  @return ResponseEntity с сообщением об успешной регистрации.
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        userService.registerUser(userDTO);
        return ResponseEntity.ok("Пользователь зарегистрирован");
    }
}