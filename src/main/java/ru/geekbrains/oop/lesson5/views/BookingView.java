package ru.geekbrains.oop.lesson5.views;

import ru.geekbrains.oop.lesson5.models.ReservationInfo;
import ru.geekbrains.oop.lesson5.models.Table;
import ru.geekbrains.oop.lesson5.presenters.View;
import ru.geekbrains.oop.lesson5.presenters.ViewObserver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class BookingView implements View {


    private Collection<ViewObserver> observers;

    public void showTables(Collection<Table> tables){
        for (Table table: tables) {
            System.out.println(table);
        }
    }

    @Override
    public void registerObserver(ViewObserver observer) {
        if (observers == null)
            observers = new ArrayList<>();
        observers.add(observer);
    }

    @Override
    public void showReservationTableResult(ReservationInfo info) {
        if (info != null){
            System.out.printf("Столик #%d успешно забронирован, %s. Номер брони: #%d\n", info.tableNo(), info.name(), info.reservationId());
        }
        else {
            System.out.println("Произошла ошибка при попытке забронировать столик.\nПовторите операцию позже.");
        }
    }

    @Override
    public void showReservationUpdateResult(ReservationInfo info) {
        if (info != null){
            System.out.printf("Вы успешно изменили бронь, %s. Новый столик: #%d, номер брони: #%d\n", info.name(), info.tableNo(), info.reservationId());
        }
        else {
            System.out.println("Произошла ошибка при попытке изменить бронь.\nПовторите операцию позже.");
        }
    }


    public void reservationTable(Date orderDate, int tableNo, String name){
        if (observers != null)
        {
            for (ViewObserver observer : observers){
                observer.onReservationTable(orderDate, tableNo, name);
            }
        }
    }

    public void changeReservationTable(int oldReservation, Date reservationDate, int tableNo, String name){
        if (observers != null)
        {
            for (ViewObserver observer : observers){
                observer.changeReservationTable(oldReservation, reservationDate, tableNo, name);
            }
        }
    }
}
