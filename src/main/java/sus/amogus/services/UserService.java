package sus.amogus.services;

import sus.amogus.models.UserDTO;

import javax.security.auth.login.CredentialException;

public interface UserService {
    String loginUser(UserDTO userDTO) throws CredentialException;
    void registerUser(UserDTO userDTO);
}
