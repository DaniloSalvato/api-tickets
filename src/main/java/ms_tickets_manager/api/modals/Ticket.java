package ms_tickets_manager.api.modals;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Table(name="tb_tickets")
@Entity(name="Ticket")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String cpf;

    @NotBlank
    private String customerName;

    @NotBlank
    @Email
    private String customerMail;

    @NotNull
    private Long eventId;

    @NotNull
    private BigDecimal brlTotalAmount;

    @NotNull
    private BigDecimal usdTotalAmount;

    @NotBlank
    private String status;
}
