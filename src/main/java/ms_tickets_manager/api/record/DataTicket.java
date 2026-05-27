package ms_tickets_manager.api.record;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.br.CPF;

@Builder
public record DataTicket(
        @CPF(message = "CPF cannot be null")
        String cpf,

        @NotBlank(message = "Name cannot be null")
        String customerName,

        @Email(message = "Email cannot be null")
        String customerMail,

        @NotNull(message = "Event id cannot be null")
        Long eventId,

        @NotBlank(message = "Amount cannot be null")
        String BRLamount,

        @NotBlank(message = "Amount cannot be null")
        String USDamount,

        @NotBlank(message = "Status cannot be null")
        String status){
}

