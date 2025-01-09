package sus.amogus.services.impls;

import org.springframework.stereotype.Component;
import sus.amogus.models.UserDTO;
import sus.amogus.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import sus.amogus.services.UserService;

import javax.security.auth.login.CredentialException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JdbcUserDetailsManager jdbcUserDetailsManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    /**
     *  Аутентифицирует пользователя и возвращает JWT токен.
     *  @param userDTO данные пользователя для аутентификации.
     *  @return String JWT токен.
     *  @throws CredentialException если аутентификация не удалась.
     */
    @Override
    public String loginUser(UserDTO userDTO) throws CredentialException {
        if (!jdbcUserDetailsManager.userExists(userDTO.getUsername())) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        UserDetails userDetails = jdbcUserDetailsManager.loadUserByUsername(userDTO.getUsername());
        if (!passwordEncoder.matches(userDTO.getPassword(), userDetails.getPassword())) {
            throw new CredentialException("Данные пользователя неправильные");
        }
        return jwtService.createToken(userDetails.getUsername(), userDetails.getAuthorities().iterator().next());
    }

    /**
     *  Регистрирует нового пользователя.
     *  @param userDTO данные пользователя для регистрации.
     *  @throws UsernameNotFoundException если пользователь с таким именем уже существует.
     */
    @Override
    public void registerUser(UserDTO userDTO) {
        if (jdbcUserDetailsManager.userExists(userDTO.getUsername())) {
            throw new UsernameNotFoundException("Пользователь с данным именем уже существует");
        }
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        UserDTO user = new UserDTO(userDTO.getUsername(), encodedPassword, List.of(new SimpleGrantedAuthority("ROLE_USER")));
        jdbcUserDetailsManager.createUser(user);
    }
}