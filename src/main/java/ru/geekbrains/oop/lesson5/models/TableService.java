package ru.geekbrains.oop.lesson5.models;

import ru.geekbrains.oop.lesson5.presenters.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class TableService implements Model {

    private List<Table> tables;


    @Override
    public List<Table> loadTables() {
        if (tables == null) {
            tables = new ArrayList<>();

            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
        }

        return tables;
    }

    @Override
    public ReservationInfo reservationTable(Date reservationDate, int tableNo, String name) {
        for (Table table : tables) {
            if (table.getNo() == tableNo) {
                Reservation reservation = new Reservation(table, reservationDate, name);
                table.getReservations().add(reservation);
                return new ReservationInfo(table.getNo(), reservation.getId(), reservation.getName());
            }
        }
        throw new RuntimeException("Некорректный номер столика");
    }

    @Override
    public ReservationInfo changeReservationTable(int oldReservation, Date reservationDate, int tableNo, String name) {

        Reservation reservation = findTableByReservation(oldReservation);

        if(reservation != null){
            for (Table table: tables) {
                if(table.getNo() == tableNo){
                    reservation.getTable().removeReservation(reservation);
                    Reservation newReservation = new Reservation(table, reservationDate, name);
                    table.getReservations().add(newReservation);
                    return new ReservationInfo(table.getNo(), newReservation.getId(), newReservation.getName());
                }
            }
        }

        throw new RuntimeException("Не удалось изменить бронь.");
    }

    private Reservation findTableByReservation(int resID){
        for (Table table: tables){
            Reservation reservation = table.checkForTableByReservation(resID);
            if(reservation != null)
                return reservation;
        }
        return null;
    }

}