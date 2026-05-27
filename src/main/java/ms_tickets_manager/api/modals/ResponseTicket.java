package ms_tickets_manager.api.modals;

import lombok.*;
import ms_tickets_manager.api.record.DataEvent;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseTicket {
    private Long id;
    private String cpf;
    private String customerName;
    private String customerMail;
    private DataEvent event;
    private BigDecimal BRLTotalAmount;
    private BigDecimal USDTotalAmount;
    private String status;
}