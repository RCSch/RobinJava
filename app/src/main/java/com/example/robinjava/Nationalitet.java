package com.example.robinjava;

public class Nationalitet
{

    int NationalitetId;
    String Nationalitet;

    public Nationalitet() {}

    public Nationalitet(int id, String nationalitet) {
        super();
        this.NationalitetId = id;
        this.Nationalitet = nationalitet;
    }

    public int getNationalitetId() {
        return NationalitetId;
    }

    public void setNationalitetId(int nationalitetId) {
        NationalitetId = nationalitetId;
    }

    public String getNationalitet() {
        return Nationalitet;
    }

    public void setNationalitet(String nationalitet) {
        Nationalitet = nationalitet;
    }

}
