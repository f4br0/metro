

package co.metro.repository;

import co.metro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByUsuarioAndClave(String usuario, String clave);
}
