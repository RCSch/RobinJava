package com.example.robinjava;

public class Interesse {

    int InteresseId;
    String Interesse;

    public Interesse() {}

    public Interesse(int id, String interesse) {
        super();
        this.InteresseId = id;
        this.Interesse = interesse;
    }

    public int getInteresseId() {
        return InteresseId;
    }

    public void setInteresseId(int interesseId) {
        InteresseId = interesseId;
    }

    public String getInteresse() {
        return Interesse;
    }

    public void setInteresse(String interesse) {
        Interesse = interesse;
    }

}
