package org.example;

public enum Dificultad {
    EASY, MEDIUM, HARD;

    private String dificultad;

    Dificultad() {
        this.dificultad = this.name().toLowerCase();
    }

    public String getDificultad(){
        return dificultad;
    }
}
