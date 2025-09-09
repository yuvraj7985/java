interface Booking {
    void book(double amount, String id);
    void schedule(String time) throws Exception;
    void cancel() throws Exception;
}

abstract class Transport implements Booking {
    protected double fare;
    protected String bookingId;
    protected boolean booked = false;
    protected boolean cancelled = false;

    public void book(double amount, String id) {
        this.fare = amount;
        this.bookingId = id;
        booked = true;
        System.out.println("Booking done with id: " + id + " Fare is Rs " + amount);
    }

    public void schedule(String time) throws Exception {
        throw new Exception("schedule not allow for this transport");
    }

    public abstract void cancel() throws Exception;
}

class Cab extends Transport {
    private String rideTime;

    @Override
    public void schedule(String time) {
        this.rideTime = time;
        System.out.println("Cab is schduled at " + time);
    }

    @Override
    public void cancel() {
        if (booked && !cancelled) {
            System.out.println("Cab " + bookingId + " cancelled. Refund Rs 300");
            cancelled = true;
        } else {
            System.out.println("Cab not active to cancel");
        }
    }
}

class Train extends Transport {
    @Override
    public void cancel() {
        if (booked && !cancelled) {
            System.out.println("Train ticket " + bookingId + " canclled. Refund Rs 1000");
            cancelled = true;
        } else {
            System.out.println("No train booking found");
        }
    }
}

class Bus extends Transport {
    @Override
    public void cancel() throws Exception {
        throw new Exception("Bus cancel not possible");
    }
}

public class TransportSystem {
    public static void main(String[] args) {
        try {
            Cab c = new Cab();
            c.book(500, "cab101");
            c.schedule("10am");
            c.cancel();
            System.out.println();

            Train t = new Train();
            t.book(1200, "train505");
            t.cancel();
            System.out.println();

            Bus b = new Bus();
            b.book(600, "bus303");
            b.cancel();

        } catch (Exception e) {
            System.out.println("Errror happen: " + e.getMessage());
        }
    }
}
