package ao.tdados.ms.microservice_spring_boot_users.controllers;

import ao.tdados.ms.microservice_spring_boot_users.dtos.AuthenticationDto;
import ao.tdados.ms.microservice_spring_boot_users.dtos.LoginResponseDto;
import ao.tdados.ms.microservice_spring_boot_users.infra.security.TokenService;
import ao.tdados.ms.microservice_spring_boot_users.models.UserModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto authenticationDTO){
            var userNamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.email(), authenticationDTO.password());
            var auth = this.authenticationManager.authenticate(userNamePassword);
            var token = this.tokenService.generateToken((UserModel) auth.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDto(token));
    }
}
