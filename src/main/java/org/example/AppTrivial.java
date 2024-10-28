package org.example;

import java.util.ArrayList;
import java.util.List;

public class AppTrivial {
    private List<Pregunta> preguntas;

    public AppTrivial() {
        preguntas = new ArrayList<>();
    }

    public void addPregunta(Pregunta pregunta){
        if (preguntas.isEmpty())
            preguntas.add(pregunta.setIdPregunta(0));
        else
            preguntas.add(pregunta.setIdPregunta(preguntas.getLast().getIdPregunta() + 1));
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public AppTrivial setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (Pregunta pregunta : preguntas)
            sb.append(pregunta);
        return sb.toString();
    }
}
