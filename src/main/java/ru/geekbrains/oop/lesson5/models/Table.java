package ru.geekbrains.oop.lesson5.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class Table {

    private static int counter;
    private int no;

    private List<Reservation> reservations = new ArrayList<>();

    public int getNo() {
        return no;
    }

    public Collection<Reservation> getReservations() {
        return reservations;
    }
    public void removeReservation(Reservation reservation){
        reservations.remove(reservation);
//        int resIndex = findReservationByID(resID);
//        if(resIndex >= 0 && resIndex < reservations.size()) {
//            reservations.remove(resIndex);
//        }
    }

    public Reservation checkForTableByReservation(int resId){
        for (Reservation reservation: reservations){
            if(reservation.getId() == resId) return reservation;
        }
        return null;
    }

    private int findReservationByID(int resID){
        for (Reservation reservation: reservations ) {
            if(reservation.getId() == resID){
                return reservations.indexOf(reservation);
            }
        }
        return -1;
    }

    public Table(){
        no = ++counter;
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "Столик #%d", no);
    }

}