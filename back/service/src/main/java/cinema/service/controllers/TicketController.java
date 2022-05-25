package cinema.service.controllers;

import cinema.service.models.Ticket;
import cinema.service.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class TicketController {
  private final TicketRepository ticketRepository;

  @GetMapping("/tickets")
  public List<Ticket> getTicketTypes() {
    return ticketRepository.findAll();
  }
}
