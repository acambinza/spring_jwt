package ao.tdados.ms.microservice_spring_boot_users.producers;

import ao.tdados.ms.microservice_spring_boot_users.dtos.EmailDto;
import ao.tdados.ms.microservice_spring_boot_users.models.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value= "${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(UserModel userModel){
        var emailDto = new EmailDto();
        emailDto.setUserId(userModel.getUserId());
        emailDto.setEmailTo(userModel.getEmail());
        emailDto.setSubject("Cadastro Realizado com sucesso");
        emailDto.setText("Seja Bem-Vindo, "+ userModel.getName());

        rabbitTemplate.convertAndSend("",routingKey, emailDto);

    }


}
