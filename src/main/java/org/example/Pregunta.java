package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pregunta {
    private TipoPregunta tipoPregunta;
    private Dificultad dificultad;
    private Categoria categoria;
    private String pregunta;
    private String correcta;
    private List<String> incorrectas;

    public Pregunta() {
        incorrectas = new ArrayList<>();
    }

    public Pregunta(TipoPregunta tipoPregunta, Dificultad dificultad, Categoria categoria, String pregunta, String correcta, List<String> incorrectas) {
        incorrectas = new ArrayList<>();
        this.tipoPregunta = tipoPregunta;
        this.dificultad = dificultad;
        this.categoria = categoria;
        this.pregunta = pregunta;
        this.correcta = correcta;
        this.incorrectas = incorrectas;
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

    public String getPregunta() {
        return pregunta;
    }

    public Pregunta setPregunta(String pregunta) {
        this.pregunta = pregunta;
        return this;
    }

    public String getCorrecta() {
        return correcta;
    }

    public Pregunta setCorrecta(String correcta) {
        this.correcta = correcta;
        return this;
    }

    public List<String> getIncorrectas() {
        return incorrectas;
    }

    public Pregunta setIncorrectas(List<String> incorrectas) {
        this.incorrectas = incorrectas;
        return this;
    }

    public void addIncorecta(String incorrecta){
        incorrectas.add(incorrecta);
    }

    @Override
    public String toString() {
        return "Pregunta{" +
                "tipoPregunta=" + tipoPregunta +
                ", dificultad=" + dificultad +
                ", categoria=" + categoria +
                ", pregunta='" + pregunta + '\'' +
                ", correcta='" + correcta + '\'' +
                ", incorrectas=" + incorrectas +
                '}';
    }
}
