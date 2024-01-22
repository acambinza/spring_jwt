package ao.tdados.ms.microservice_spring_boot_users.controllers;

import ao.tdados.ms.microservice_spring_boot_users.dtos.UserRecordDto;
import ao.tdados.ms.microservice_spring_boot_users.repositories.UserRepository;
import ao.tdados.ms.microservice_spring_boot_users.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ao.tdados.ms.microservice_spring_boot_users.models.UserModel;

@RestController
@RequestMapping("users")
public class UserController {

    final UserService userService;

    public UserController(UserService UserService){
        this.userService = UserService;
    }

    @PostMapping("/")
    public ResponseEntity saveUser(@RequestBody @Valid UserRecordDto userRecordDto) {
        System.out.println("here");
        if(this.userService.findByEmail(userRecordDto.email()) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email já existe!");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(userRecordDto.password());

        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto, userModel);
        userModel.setPassword(encryptedPassword);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.save(userModel));
    }
}
