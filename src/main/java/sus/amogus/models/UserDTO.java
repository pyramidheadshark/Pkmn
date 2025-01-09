package sus.amogus.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements UserDetails {
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    /**
     *  Возвращает список прав доступа пользователя.
     *  @return List<GrantedAuthority> список прав доступа.
     */
    @Override
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     *  Возвращает пароль пользователя.
     *  @return String пароль пользователя.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     *  Возвращает имя пользователя.
     *  @return String имя пользователя.
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     *  Указывает, не истек ли срок действия учетной записи пользователя.
     *  @return boolean true, если срок действия учетной записи не истек, false в противном случае.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     *  Указывает, не заблокирована ли учетная запись пользователя.
     *  @return boolean true, если учетная запись не заблокирована, false в противном случае.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     *  Указывает, не истек ли срок действия учетных данных пользователя.
     *  @return boolean true, если срок действия учетных данных не истек, false в противном случае.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     *  Указывает, включен ли пользователь.
     *  @return boolean true, если пользователь включен, false в противном случае.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}