package sus.amogus.services;

import sus.amogus.models.UserDTO;

import javax.security.auth.login.CredentialException;

public interface UserService {
    /**
     *  Аутентифицирует пользователя и возвращает JWT токен.
     *  @param userDTO данные пользователя для аутентификации.
     *  @return String JWT токен.
     *  @throws CredentialException если аутентификация не удалась.
     */
    String loginUser(UserDTO userDTO) throws CredentialException;
    /**
     *  Регистрирует нового пользователя.
     *  @param userDTO данные пользователя для регистрации.
     */
    void registerUser(UserDTO userDTO);
}