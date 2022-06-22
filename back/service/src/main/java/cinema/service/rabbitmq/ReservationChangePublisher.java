package cinema.service.rabbitmq;

import cinema.service.converters.ReservedSeatDtoToReservationChangeSeat;
import cinema.service.models.dtos.ReservationDataDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Slf4j
public class ReservationChangePublisher {
  private ReservationChangePublisher() {}

  private static final String QUEUE_NAME = "cinema";

  public static void publish(ReservationDataDto reservationDataDto, boolean isReservation)
      throws IOException, TimeoutException {
    ConnectionFactory connectionFactory = new ConnectionFactory();
    connectionFactory.setHost("localhost");
    try (Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel()) {

      ReservationChange reservationChange =
          extractReservationChangeFrom(reservationDataDto, isReservation);


      String jsonReservationChange = toJson(reservationChange);

      log.info("Sent message to RabbitMQ. Content: " + jsonReservationChange);

      channel.queueDeclare(QUEUE_NAME, false, false, false, null);
      channel.basicPublish("", QUEUE_NAME, null, jsonReservationChange.getBytes());
    }
  }

  private static String toJson(ReservationChange reservationChange) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.writeValueAsString(reservationChange);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      log.error("ERROR! Unable to serialize reservation change. Empty JSON object has been returned");
      return "{}";
    }
  }

  private static ReservationChange extractReservationChangeFrom(
      ReservationDataDto reservationDataDto, boolean isReservation) {
    return new ReservationChange(
        reservationDataDto.getScreeningId(),
        reservationDataDto.getReservedSeatDtoList().stream()
            .map(ReservedSeatDtoToReservationChangeSeat::convert)
            .toList(),
        isReservation);
  }
}
