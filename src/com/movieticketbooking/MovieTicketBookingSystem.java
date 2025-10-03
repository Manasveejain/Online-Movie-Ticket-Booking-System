package com.movieticketbooking;
import  java.util.*;
import java.util.concurrent.*;
import java.time.LocalDateTime;

public class MovieTicketBookingSystem {
    private  List<Movie> movies = new CopyOnWriteArrayList<>();
    private Set<Theater> theaters = new CopyOnWriteArraySet<>();
    private  Map<Theater, List<Showtime>> theaterShowtimes = new ConcurrentHashMap<>();
    private List<Booking> bookings = new CopyOnWriteArrayList<>();

    //add movie to system
    public void addMovie(Movie movie) {
        movies.add(movie);
    }
    // Add a theater to system
    public void addTheater(Theater theater){
        theaters.add(theater);
        theaterShowtimes.put(theater , new ArrayList<>());
    }
    // add a showtime to theater

    public void  addShowtimeToTheater(Theater theater , Showtime showtime){
        theaterShowtimes.get(theater).add(showtime);
    }
    //Book a ticket
    public void bookTicket(Movie movie , Showtime showtime, String customerName){
        if(showtime.isAvailable()){
            showtime.book(); // Marl showtime as unavailable
            bookings.add(new Booking(movie, showtime, customerName));
            System.out.println("Booking Successful for " +customerName);
        }
        else {
            System.out.println("Sorry , this showtime is unavailable.");
        }
    }
    //Search for movies by name
    public List<Movie> searchMoviesByName(String name) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            if(movie.getName().contains(name)) {
                result.add(movie);
            }
        }
        return result;
    }
    //sort movies by name
    public void sortMovies(){
        Collections.sort(movies);
    }

    // search for showtimes by movie and theater
    public List<Showtime> searchShowtimes(Movie movie , Theater theater){
        List<Showtime> result = new ArrayList<>();
        for (Showtime showtime: theaterShowtimes.get(theater)){
            if (showtime.getMovie().equals(movie))
                result.add(showtime);
        }
        return result;
    }

    public static void main(String[] args){
        MovieTicketBookingSystem system = new MovieTicketBookingSystem();

        //Create movies
        Movie movie1 = new  Movie("Inception", "Sci-Fi" , 148);
        Movie movie2 = new  Movie("Interstellar", "Sci-Fi" , 120);
        system.addMovie(movie1);
        system.addMovie(movie2);

        //Create theaters
        Theater theater1 = new Theater("IMAX Theater", "City center");
        Theater theater2 = new Theater("Regal Cinemas", "DownTown");
        system.addTheater(theater1);
        system.addTheater(theater2);

        //create showtime
        Showtime showtime1 = new Showtime(LocalDateTime.of(2025,6,10,19,30), movie1);
        Showtime showtime2 = new Showtime(LocalDateTime.of(2025,6,10,21,30), movie2);
        system.addShowtimeToTheater(theater1 , showtime1);
        system.addShowtimeToTheater(theater2 , showtime2);

        //Booking a ticket
        system.bookTicket(movie1 , showtime1,"Manasvee");

        //Searching and sorting movies
        List<Movie> SearchResults = system.searchMoviesByName("Inception");
        System.out.println("Search Results: "+SearchResults);
        system.sortMovies();
        System.out.println("Sorted Movies: " + system.movies);
    }
}
