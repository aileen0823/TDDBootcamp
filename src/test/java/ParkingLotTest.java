import lesson2.Car;
import lesson2.ParkingBoy;
import lesson2.ParkingLot;
import lesson2.Ticket;
import lesson2.exception.DuplicateCarNoException;
import lesson2.exception.ParkingLotFullException;
import lesson2.exception.NoCarNoException;
import lesson2.exception.TicketNotMatchException;
import lesson2.strategy.HighestVacancyRateParkingStrategy;
import lesson2.strategy.MaximumEmptyParkingStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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


    @Test
    public void should_car_parking_in_first_parking_lot_when_park_car_given_two_ordered_parking_lot_and_first_parking_lot_is_available() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car("A12345");
        Ticket ticket = parkingBoy.park(car);
        Assert.assertNotNull(firstParkingLot.pick(ticket));
    }

    @Test
    public void should_car_parking_in_second_parking_lot_when_park_car_given_three_ordered_parking_lot_and_first_parking_lot_is_not_available() {
        ParkingLot firstParkingLot = new ParkingLot(0);
        ParkingLot secondParkingLot = new ParkingLot(1);
        ParkingLot thirdParkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        parkingLots.add(thirdParkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car("A12345");
        Ticket ticket = parkingBoy.park(car);
        Assert.assertNotNull(secondParkingLot.pick(ticket));
    }

    @Test
    public void should_car_parking_in_second_parking_lot_when_park_car_given_two_ordered_parking_lot_and_first_parking_lot_is_full() {
        ParkingLot firstParkingLot = new ParkingLot(0);
        ParkingLot secondParkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car("A12345");
        Ticket ticket = parkingBoy.park(car);

        Assert.assertNotNull(secondParkingLot.pick(ticket));
    }

    @Test
    public void should_return_car_when_pick_car_given_car_was_parked_in_first_parking_lot() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car("A12345");
        Ticket ticket = parkingBoy.park(car);
        Assert.assertEquals(firstParkingLot.pick(ticket), car);
    }

    @Test
    public void should_return_car_when_pick_car_given_car_was_parked_in_second_parking_lot() {
        ParkingLot firstParkingLot = new ParkingLot(0);
        ParkingLot secondParkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car("A12345");
        Ticket ticket = parkingBoy.park(car);

        Assert.assertEquals(secondParkingLot.pick(ticket), car);
    }


    @Test
    public void should_car_parking_in_most_empty_parking_lot_when_park_car_given_two_parking_lot_and_second_parking_lot_is_most_empty() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, new MaximumEmptyParkingStrategy());
        Car car = new Car("A12345");
        Ticket ticket = parkingBoy.park(car);
        Assert.assertNotNull(secondParkingLot.pick(ticket));
    }


    @Test
    public void should_car_parking_in_most_empty_parking_lot_when_park_car_given_two_parking_lot_and_second_parking_lot_is_highest_vacancy_rate() {
        ParkingLot firstParkingLot = new ParkingLot(4);
        ParkingLot secondParkingLot = new ParkingLot(2);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, new HighestVacancyRateParkingStrategy());
        parkingBoy.park(new Car("A12345"));
        Ticket ticket = parkingBoy.park(new Car("A22345"));
        Assert.assertNotNull(secondParkingLot.pick(ticket));
    }



}
