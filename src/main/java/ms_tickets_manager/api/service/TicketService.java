package ms_tickets_manager.api.service;

import ms_tickets_manager.api.client.IEventClient;
import ms_tickets_manager.api.modals.CreateTicketResponse;
import ms_tickets_manager.api.modals.ResponseTicket;
import ms_tickets_manager.api.modals.Ticket;
import ms_tickets_manager.api.record.CheckEvent;
import ms_tickets_manager.api.record.DataTicket;
import ms_tickets_manager.api.repository.TicketRepository;
import ms_tickets_manager.api.utils.ValueConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketService implements ITicketService {

    @Autowired
    private TicketRepository repository;

    @Autowired
    private IEventClient iEventClient;

    @Autowired
    private ValueConversion valueConversion;

    @Transactional
    public CreateTicketResponse CreateTicket(DataTicket data){
        var event = iEventClient.getEventClient(data.eventId());
        if(event.id() == null) {
            return new CreateTicketResponse();
        }

        var ticket = Ticket.builder()
                                    .cpf(data.cpf())
                                    .customerName(data.customerName())
                                    .customerMail(data.customerMail())
                                    .eventId(data.eventId())
                                    .brlTotalAmount(valueConversion.DataValueConversionBigDecimal(data.BRLamount()))
                                    .usdTotalAmount(valueConversion.DataValueConversionBigDecimal(data.USDamount()))
                                    .status(data.status())
                                    .build();


        var result = repository.save(ticket);
        return CreateTicketResponse.builder()
                                            .id(result.getId())
                                            .cpf(result.getCpf())
                                            .customerName(result.getCustomerName())
                                            .customerMail(result.getCustomerMail())
                                            .event(event)
                                            .BRLTotalAmount(valueConversion.DataValueConversionString(result.getBrlTotalAmount()))
                                            .USDTotalAmount(valueConversion.DataValueConversionString(result.getUsdTotalAmount()))
                                            .status(result.getStatus())
                                            .build();
    }

    public ResponseTicket getTicketById(Long id){
        Ticket ticket = repository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));
        var event = iEventClient.getEventClient(ticket.getId());
        if(event.id() == null){
            throw new RuntimeException("Event not found");
        }
        return  ResponseTicket.builder()
                .id(ticket.getId())
                .cpf(ticket.getCpf())
                .customerName(ticket.getCustomerName())
                .customerMail(ticket.getCustomerMail())
                .event(event)
                .BRLTotalAmount(ticket.getBrlTotalAmount())
                .USDTotalAmount(ticket.getUsdTotalAmount())
                .status(ticket.getStatus())
                .build();

    }

    public List<ResponseTicket> getTicketsByCpf(String cpf) {
        List<Ticket> tickets = repository.findByCpf(cpf);
        return tickets.stream()
                .map(ticket -> {
                    var event = iEventClient.getEventClient(ticket.getEventId());
                    if (event.id() == null) {
                        throw new RuntimeException("Event not found");
                    }
                    return ResponseTicket.builder()
                            .id(ticket.getId())
                            .cpf(ticket.getCpf())
                            .customerName(ticket.getCustomerName())
                            .customerMail(ticket.getCustomerMail())
                            .event(event)
                            .BRLTotalAmount(ticket.getBrlTotalAmount())
                            .USDTotalAmount(ticket.getUsdTotalAmount())
                            .status(ticket.getStatus())
                            .build();
                }).toList();
    }

    public CheckEvent getTicketsByEventId(Long eventId) {
        List<Ticket> tickets = repository.findByEventId(eventId);

        return CheckEvent.builder()
                         .eventId(eventId.toString())
                         .hasTickets(tickets.stream().anyMatch(ticket -> !ticket.getStatus().equals("Canceled")))
                         .build();
    }

    @Transactional
    public String updateTicketById(Long id, DataTicket dataTicket){
        Ticket ticket = repository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));
        var ticketUpdate = Ticket.builder()
                .cpf(dataTicket.cpf().equals(ticket.getCpf()) ? ticket.getCpf() : dataTicket.cpf())
                .customerName(dataTicket.customerName().equals(ticket.getCustomerName()) ? ticket.getCustomerName() : dataTicket.customerName())
                .customerMail(dataTicket.customerMail().equals(ticket.getCustomerMail()) ? ticket.getCustomerMail() : dataTicket.customerMail())
                .customerMail(dataTicket.customerMail().equals(ticket.getCustomerMail()) ? ticket.getCustomerMail() : dataTicket.customerMail())
                .eventId(dataTicket.eventId().equals(ticket.getEventId()) ? ticket.getEventId() : dataTicket.eventId())
                .brlTotalAmount(valueConversion.DataValueConversionBigDecimal(dataTicket.BRLamount()).equals(ticket.getBrlTotalAmount()) ? ticket.getBrlTotalAmount() : valueConversion.DataValueConversionBigDecimal(dataTicket.BRLamount()))
                .usdTotalAmount(valueConversion.DataValueConversionBigDecimal(dataTicket.USDamount()).equals(ticket.getUsdTotalAmount()) ? ticket.getUsdTotalAmount() : valueConversion.DataValueConversionBigDecimal(dataTicket.USDamount()))
                .status(dataTicket.status().equals(ticket.getStatus()) ? ticket.getStatus() : dataTicket.status())
                .build();
        repository.save(ticketUpdate);
        return "Ticket updated";
    }

    @Transactional
    public String deleteTicketById(Long id){
        Ticket ticket = repository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.setStatus("Canceled");
        repository.save(ticket);
        return "Ticket Canceled";
    }
}
