package ru.geekbrains.oop.lesson5.presenters;

import ru.geekbrains.oop.lesson5.models.ReservationInfo;
import ru.geekbrains.oop.lesson5.models.TableService;
import ru.geekbrains.oop.lesson5.views.BookingView;

import java.util.Date;

public class BookingPresenter implements ViewObserver {

    private Model model;
    private View view;

    public BookingPresenter(Model model, View view){
        this.model = model;
        this.view = view;
        view.registerObserver(this);
    }

    public void updateTablesView(){
        view.showTables(model.loadTables());
    }

    private void sendReservationUpdateResult(ReservationInfo result) {
        view.showReservationUpdateResult(result);
    }

    private void updateReservationTableView(ReservationInfo reservationInfo){
        view.showReservationTableResult(reservationInfo);
    }

    @Override
    public void onReservationTable(Date orderDate, int tableNo, String name) {
        try {
            ReservationInfo reservationNo = model.reservationTable(orderDate, tableNo, name);
            updateReservationTableView(reservationNo);
        }
        catch (Exception e){
            updateReservationTableView(null);
        }

    }

    @Override
    public void changeReservationTable(int oldReservation, Date reservationDate, int tableNo, String name) {
        try {
            ReservationInfo reservationInfo = model.changeReservationTable(oldReservation, reservationDate, tableNo, name);
            sendReservationUpdateResult(reservationInfo);
        } catch (Exception e){
            sendReservationUpdateResult(null);
        }
    }
}

