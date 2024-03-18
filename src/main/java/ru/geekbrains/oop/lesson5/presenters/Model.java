package ru.geekbrains.oop.lesson5.presenters;

import ru.geekbrains.oop.lesson5.models.ReservationInfo;
import ru.geekbrains.oop.lesson5.models.Table;

import java.util.Collection;
import java.util.Date;

public interface Model {

    Collection<Table> loadTables();

    ReservationInfo reservationTable(Date reservationDate, int tableNo, String name);

    ReservationInfo changeReservationTable(int oldReservation, Date reservationDate, int tableNo, String name);

}
