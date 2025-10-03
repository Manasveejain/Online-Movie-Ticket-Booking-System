package com.movieticketbooking;
import java.util.Objects;
public class Movie implements Comparable<Movie> {
    private String name;
    private String genre;
    private int duration; // in minutes

    public Movie(String name , String genre , int duration)
    {
        this.name = name;
        this.genre = genre;
        this. duration = duration;
    }
    public  String getName(){
        return name;
    }
    public  String getGenre(){
        return genre;
    }

    public  int getDuration(){
        return duration;
    }

    @Override
    public  int compareTo(Movie o){
        return this.name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o){
        if( this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return name.equals(movie.name);
    }

    @Override
    public  int hashCode()
    {
        return Objects.hash(name);
    }

    @Override
    public String toString()
    {
        return "Movie{Name='" + name + "', Genre='" +genre +"', Duration=" + duration + "}" ;
    }
}
