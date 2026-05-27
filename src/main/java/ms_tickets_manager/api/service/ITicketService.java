package ms_tickets_manager.api.service;

import ms_tickets_manager.api.modals.CreateTicketResponse;
import ms_tickets_manager.api.modals.ResponseTicket;
import ms_tickets_manager.api.record.CheckEvent;
import ms_tickets_manager.api.record.DataTicket;

import java.util.List;

public interface ITicketService {

    CreateTicketResponse CreateTicket(DataTicket data);
    ResponseTicket getTicketById(Long id);
    List<ResponseTicket> getTicketsByCpf(String cpf);
    CheckEvent getTicketsByEventId(Long eventId);
    String updateTicketById(Long id, DataTicket dataTicket);
    String deleteTicketById(Long id);
}
