package org.example;

public enum TipoPregunta {
    MULTIPLE, BOOLEAN;

    private String tipoPregunta;

    TipoPregunta() {
        this.tipoPregunta = this.name().toLowerCase();
    }

    public String getTipoPregunta() {
        return tipoPregunta;
    }
}
