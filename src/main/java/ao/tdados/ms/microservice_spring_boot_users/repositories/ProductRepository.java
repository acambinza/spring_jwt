package ao.tdados.ms.microservice_spring_boot_users.repositories;

import ao.tdados.ms.microservice_spring_boot_users.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, UUID> {

}
