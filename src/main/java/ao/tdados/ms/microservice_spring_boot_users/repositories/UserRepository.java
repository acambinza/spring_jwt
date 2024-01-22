package ao.tdados.ms.microservice_spring_boot_users.repositories;

import ao.tdados.ms.microservice_spring_boot_users.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    UserDetails findByEmail(String email);
}
