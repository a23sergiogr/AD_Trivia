package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pregunta implements Serializable, Comparable<Pregunta> {
    private long idPregunta;
    private TipoPregunta tipoPregunta;
    private Dificultad dificultad;
    private Categoria categoria;
    private String pregunta;

    public Pregunta() {
    }

    public Pregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public long getIdPregunta() {
        return idPregunta;
    }

    public Pregunta setIdPregunta(long idPregunta) {
        this.idPregunta = idPregunta;
        return this;
    }

    public String getPregunta() {
        return pregunta;
    }

    public Pregunta setPregunta(String pregunta) {
        this.pregunta = pregunta;
        return this;
    }

    public TipoPregunta getTipoPregunta() {
        return tipoPregunta;
    }

    public Pregunta setTipoPregunta(TipoPregunta tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
        return this;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public Pregunta setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
        return this;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Pregunta setCategoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
    }

    @Override
    public int compareTo(Pregunta o) {
        return this.pregunta.compareToIgnoreCase(o.pregunta);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pregunta pregunta1 = (Pregunta) o;
        return Objects.equals(pregunta, pregunta1.pregunta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pregunta);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("idPregunta: ").append(idPregunta).append("\n");
        sb.append("pregunta: ").append(pregunta).append("\n");
        sb.append("tipoPregunta: ").append(tipoPregunta).append("\n");
        sb.append("dificultad: ").append(dificultad).append("\n");
        sb.append("categoria: ");
        return sb.toString();
    }
}
