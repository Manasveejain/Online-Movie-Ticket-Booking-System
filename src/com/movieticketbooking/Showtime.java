package com.movieticketbooking;
import java.time.LocalDateTime;
import java.util.Objects;
public class Showtime {
    private LocalDateTime time;
    private Movie movie;
    private boolean isAvailable;

    public Showtime(LocalDateTime time, Movie movie){
        this.time = time;
        this.movie = movie;
        this.isAvailable = true;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Movie getMovie() {
        return movie;
    }
    public  boolean isAvailable() {
        return isAvailable;
    }

    public void  book(){
        if (isAvailable){
            isAvailable= false;
        }
    }
    @Override
     public boolean equals(Object o){
        if (this == o) return  true;
        if(o== null || getClass() != o.getClass()) return  false;
        Showtime showtime = (Showtime) o;
        return time.equals(showtime.time) && movie.equals(showtime.movie);
     }

     @Override
    public  int hashCode(){
        return Objects.hash(time, movie);
     }
}

