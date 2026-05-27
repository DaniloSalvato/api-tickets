package ms_tickets_manager.api.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ms_tickets_manager.api.modals.CreateTicketResponse;
import ms_tickets_manager.api.modals.ResponseTicket;
import ms_tickets_manager.api.record.CheckEvent;
import ms_tickets_manager.api.record.DataTicket;
import ms_tickets_manager.api.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ticket")
public class TicketController {

    @Autowired
    private ITicketService iTicketService;

    @PostMapping("/create-ticket")
    public CreateTicketResponse CreateTicket(@Valid @RequestBody DataTicket data){
        return iTicketService.CreateTicket(data);
    }

    @GetMapping("/get-ticket/{id}")
    public ResponseTicket GetTicketById(@Valid @PathVariable Long id){
        return iTicketService.getTicketById(id);
    }

    @GetMapping("/get-ticket-cpf/{cpf}")
    public List<ResponseTicket> GetTicketById(@PathVariable String cpf){
        return iTicketService.getTicketsByCpf(cpf);
    }

    @GetMapping("/check-tickets-by-event/{eventId}")
    public CheckEvent GetTicketsByEvent(@Valid @PathVariable @NotNull Long eventId){
        return iTicketService.getTicketsByEventId(eventId);
    }

    @PutMapping("/update-ticket/{id}")
    public String UpdateTicket(@Valid @PathVariable @RequestBody Long id, DataTicket data){
        return iTicketService.updateTicketById(id, data);
    }

    @DeleteMapping("/cancel-ticket/{id}")
    public String DeleteTickets(@Valid @PathVariable @NotNull Long id){
        return iTicketService.deleteTicketById(id);
    }
}
