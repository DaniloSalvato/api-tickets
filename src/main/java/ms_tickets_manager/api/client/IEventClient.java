package ms_tickets_manager.api.client;

import ms_tickets_manager.api.record.DataEvent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8080/event/get-event/", name = "event")
public interface IEventClient {

    @GetMapping("{id}")
    DataEvent getEventClient(@PathVariable Long id);
}
