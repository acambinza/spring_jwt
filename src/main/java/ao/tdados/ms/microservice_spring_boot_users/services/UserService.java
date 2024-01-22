package ao.tdados.ms.microservice_spring_boot_users.services;

import ao.tdados.ms.microservice_spring_boot_users.models.UserModel;
import ao.tdados.ms.microservice_spring_boot_users.producers.UserProducer;
import ao.tdados.ms.microservice_spring_boot_users.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

   final UserRepository userRepository;
   final UserProducer userProducer;

   public UserService(UserRepository userRepository, UserProducer userProducer){
       this.userRepository = userRepository;
       this.userProducer = userProducer;
   }

    @Transactional
    public UserModel save(UserModel userModel) {
       userModel = this.userRepository.save(userModel);
       // userProducer.publishMessageEmail(userModel);
       return userModel;
    }

    public UserDetails findByEmail(String email){
       return this.userRepository.findByEmail(email);
    }

}
