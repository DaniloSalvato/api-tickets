package ms_tickets_manager.api.record;

import lombok.Builder;

@Builder
public record CheckEvent(String eventId, Boolean hasTickets) {
}
