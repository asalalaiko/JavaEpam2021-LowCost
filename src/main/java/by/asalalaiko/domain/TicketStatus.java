package by.asalalaiko.domain;

import java.util.Arrays;

public enum TicketStatus {
    FREE, BOOKED, PAID, CLOSED;

    public static TicketStatus getByOrdinal(int ordinal) {
        return Arrays.asList(TicketStatus.values()).stream().filter(pr -> ordinal == pr.ordinal()).findFirst().get();
    }
}
