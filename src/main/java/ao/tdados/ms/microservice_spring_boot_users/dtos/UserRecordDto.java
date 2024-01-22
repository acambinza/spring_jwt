package ao.tdados.ms.microservice_spring_boot_users.dtos;

import ao.tdados.ms.microservice_spring_boot_users.enums.UserRoles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRecordDto(@NotBlank String name,
                            @NotBlank @Email String email,
                            @NotBlank String password,
                            UserRoles role) {

}
