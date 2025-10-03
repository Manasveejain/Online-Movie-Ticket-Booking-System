package com.movieticketbooking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MovieTicketBookingSystem system = new MovieTicketBookingSystem();

        // --- Add sample movies ---
        Movie m1 = new Movie("Inception", "Sci-Fi", 148);
        Movie m2 = new Movie("Interstellar", "Sci-Fi", 169);
        system.addMovie(m1);
        system.addMovie(m2);

        // --- Add sample theaters ---
        Theater t1 = new Theater("PVR", "Delhi");
        Theater t2 = new Theater("INOX", "Mumbai");
        system.addTheater(t1);
        system.addTheater(t2);

        // --- Add sample showtimes ---
        Showtime s1 = new Showtime( LocalDateTime.of(2025, 10, 5, 18, 30),m1);
        Showtime s2 = new Showtime( LocalDateTime.of(2025, 10, 6, 20, 0), m2);
        system.addShowtimeToTheater(t1, s1);
        system.addShowtimeToTheater(t2, s2);

        // --- Simple menu system ---
        while (true) {
            System.out.println("\n--- Movie Ticket Booking System ---");
            System.out.println("1. Search Movie");
            System.out.println("2. Book Ticket");
            System.out.println("3. View Showtimes");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter movie name to search: ");
                    String name = sc.nextLine();
                    List<Movie> found = system.searchMoviesByName(name);
                    if (found.isEmpty()) {
                        System.out.println("No movies found.");
                    } else {
                        System.out.println("Movies found:");
                        for (Movie m : found) {
                            System.out.println(m.getName() + " (" + m.getGenre() + ")");
                        }
                    }
                    break;

                case 2:
                    System.out.print("Enter your name: ");
                    String customerName = sc.nextLine();
                    System.out.println("Available movies:");
                    for (Movie m : system.searchMoviesByName("")) {
                        System.out.println("- " + m.getName());
                    }
                    System.out.print("Enter movie name to book: ");
                    String bookMovieName = sc.nextLine();
                    List<Movie> movies = system.searchMoviesByName(bookMovieName);
                    if (movies.isEmpty()) {
                        System.out.println("Movie not found.");
                        break;
                    }
                    Movie selectedMovie = movies.get(0);
                    System.out.println("Available showtimes:");
                    for (Theater th : system.searchShowtimes(selectedMovie, t1).isEmpty() ? system.theaterShowtimes.keySet() : system.theaterShowtimes.keySet()) {
                        List<Showtime> showtimes = system.searchShowtimes(selectedMovie, th);
                        for (Showtime st : showtimes) {
                            System.out.println(th.getName() + " - " + st.getTime());
                        }
                    }
                    System.out.print("Enter theater name: ");
                    String theaterName = sc.nextLine();
                    Theater selectedTheater = null;
                    for (Theater th : system.theaterShowtimes.keySet()) {
                        if (th.getName().equalsIgnoreCase(theaterName)) {
                            selectedTheater = th;
                            break;
                        }
                    }
                    if (selectedTheater == null) {
                        System.out.println("Theater not found.");
                        break;
                    }
                    List<Showtime> availableShowtimes = system.searchShowtimes(selectedMovie, selectedTheater);
                    if (availableShowtimes.isEmpty()) {
                        System.out.println("No showtimes available.");
                        break;
                    }
                    Showtime chosenShowtime = availableShowtimes.getFirst(); // first available
                    system.bookTicket(selectedMovie, chosenShowtime, customerName);
                    break;

                case 3:
                    System.out.println("All Showtime:");
                    for (Theater th : system.theaterShowtimes.keySet()) {
                        System.out.println(th.getName() + " (" + th.getLocation() + ")");
                        List<Showtime> sts = system.theaterShowtimes.get(th);
                        for (Showtime st : sts) {
                            System.out.println("   " + st.getMovie().getName() + " - " + st.getTime());
                        }
                    }
                    break;

                case 4:
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
