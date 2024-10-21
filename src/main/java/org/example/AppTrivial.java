package org.example;

import java.util.ArrayList;
import java.util.List;

public class AppTrivial {
    List<Pregunta> preguntas;

    public AppTrivial() {
        preguntas = new ArrayList<>();
    }

    public void addPregunta(Pregunta pregunta){
        if (preguntas.isEmpty())
            preguntas.add(pregunta.setIdPregunta(0));
        else
            preguntas.add(pregunta.setIdPregunta(preguntas.getLast().getIdPregunta() + 1));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (Pregunta pregunta : preguntas)
            sb.append(pregunta);
        return sb.toString();
    }
}
