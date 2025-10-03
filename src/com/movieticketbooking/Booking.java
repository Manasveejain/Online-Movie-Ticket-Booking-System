package com.movieticketbooking;

public class Booking {
    private Movie movie;
    private  Showtime showtime;
    private  String  customerName;

    public Booking(Movie movie, Showtime showtime , String  customerName){
        this.movie=  movie;
        this.showtime = showtime;
        this.customerName = customerName;
    }

    public Movie getMovie() {
        return movie;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public String getCustomerName() {
        return customerName;
    }
}
