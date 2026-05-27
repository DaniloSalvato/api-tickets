package ms_tickets_manager.api.repository;

import ms_tickets_manager.api.modals.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByCpf(String cpf);
    List<Ticket> findByEventId(Long eventId);
}
