package com.example.robinjava;

public class Person {
    int PersonId;
    String Navn;
    String Adresse;
    int Nationalitet; //Kommer det her til at være problematisk? Det er jo ID'et, vi henter, som er en int.
    boolean Favourite;
    String Tlf;
    int Interesse1;
    int Interesse2;
    int Interesse3;


    public Person() {}

    public Person(int id, String navn, String adresse, int nationalitet,  boolean favourite, String tlf, int interesse1, int interesse2, int interesse3) {
        super();
        this.PersonId = id;
        this.Navn = navn;
        this.Adresse = adresse;
        this.Nationalitet = nationalitet;
        this.Favourite = favourite;
        this.Tlf = tlf;
        this.Interesse1 =interesse1;
        this.Interesse2 = interesse2;
        this.Interesse3 = interesse3;

    }

    public String getNavn() {
        return Navn;
    }

    public void setNavn(String navn) {
        Navn = navn;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public int getNationalitet() {
        return Nationalitet;
    }

    public void setNationalitet(int nationalitet) {
        Nationalitet = nationalitet;
    }

    public boolean isFavourite() {
        return Favourite;
    }

    public void setFavourite(boolean favourite) {
        Favourite = favourite;
    }

    public String getTlf() {
        return Tlf;
    }

    public void setTlf(String tlf) {
        Tlf = tlf;
    }

    public int getInteresse1() {
        return Interesse1;
    }

    public void setInteresse1(int interesse1) {
        Interesse1 = interesse1;
    }

    public int getInteresse2() {
        return Interesse2;
    }

    public void setInteresse2(int interesse2) {
        Interesse2 = interesse2;
    }

    public int getInteresse3() {
        return Interesse3;
    }

    public void setInteresse3(int interesse3) {
        Interesse3 = interesse3;
    }


}
