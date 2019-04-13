import lesson2.Car;
import lesson2.ParkingLot;
import lesson2.Ticket;
import lesson2.exception.DuplicateCarNoException;
import lesson2.exception.ParkingLotFullException;
import lesson2.exception.NoCarNoException;
import lesson2.exception.TicketNotMatchException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class ParkingLotTest {

    @Test
    public void should_issue_correct_ticket_when_parking_given_parking_lot_available() {
        ParkingLot parkingLot = new ParkingLot(1);
        Ticket ticket = parkingLot.park(new Car("A12345"));
        Assert.assertNotNull(ticket);
    }

    @Test
    public void should_not_issue_ticket_when_parking_given_car_with_no_car_no() {
        ParkingLot parkingLot = new ParkingLot(1);
        assertThrows(NoCarNoException.class, ()->{
            parkingLot.park(new Car(""));
        });
    }


    @Test
    public void should_not_issue_ticket_when_parking_given_parking_lot_full() {
        ParkingLot parkingLot = new ParkingLot(1);
        String carNo = "A12345";
        parkingLot.park(new Car(carNo));
        assertThrows(ParkingLotFullException.class, () -> {
            parkingLot.park(new Car(carNo));
        });
    }

    @Test
    public void should_not_issue_ticket_when_parking_given_car_with_duplicate_car_no() {
        ParkingLot parkingLot = new ParkingLot(2);
        String carNo = "A12345";
        parkingLot.park(new Car(carNo));

        assertThrows(DuplicateCarNoException.class, () -> {
            parkingLot.park(new Car(carNo));
        });
    }


    @Test
    public void should_return_correct_car_when_pick_car_given_correct_ticket() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("A12345");
        Ticket ticket = parkingLot.park(car);

        Car pickCar = parkingLot.pick(ticket);
        Assert.assertEquals(car, pickCar);
    }


    @Test
    public void should_not_return_correct_car_when_pick_car_given_another_car_ticket() {
        ParkingLot parkingLot = new ParkingLot(2);

        Car carA = new Car("A");
        parkingLot.park(carA);
        Ticket ticketB = parkingLot.park(new Car("B"));

       Assert.assertNotEquals(parkingLot.pick(ticketB), carA);
    }

    @Test
    public void should_not_return_car_when_pick_car_given_wrong_ticket() {
        ParkingLot parkingLot = new ParkingLot(1);
        Ticket ticket = new Ticket();
        assertThrows(TicketNotMatchException.class, () -> {
            parkingLot.pick(ticket);
        });
    }


    @Test
    public void should_not_return_car_when_pick_car_given_used_ticket() {
        ParkingLot parkingLot = new ParkingLot(1);
        Ticket ticket = parkingLot.park(new Car("A12345"));
        parkingLot.pick(ticket);

        assertThrows(TicketNotMatchException.class, () -> {
            parkingLot.pick(ticket);
        });
    }
}
