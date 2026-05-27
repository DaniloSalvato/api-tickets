package ms_tickets_manager.api.record;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record DataEvent(Long id, String eventName, LocalDateTime eventDate, String postalCode, String street,String district,String city, String state) {
}
