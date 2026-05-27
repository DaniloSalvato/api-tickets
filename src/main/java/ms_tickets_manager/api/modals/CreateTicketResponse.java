package ms_tickets_manager.api.modals;

import lombok.*;
import ms_tickets_manager.api.record.DataEvent;
import ms_tickets_manager.api.record.DataTicket;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTicketResponse {
    private Long id;
    private String cpf;
    private String customerName;
    private String customerMail;
    private DataEvent event;
    private String BRLTotalAmount;
    private String USDTotalAmount;
    private String status;
}
