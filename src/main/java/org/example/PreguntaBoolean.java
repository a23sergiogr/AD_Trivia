package org.example;

import java.util.function.Predicate;

public class PreguntaBoolean extends Pregunta implements Predicate<Boolean> {
    private boolean respuesta;

    public PreguntaBoolean() {
    }

    public PreguntaBoolean(String pregunta) {
        super(pregunta);
    }

    public PreguntaBoolean(String pregunta, boolean respuesta) {
        super(pregunta);
        this.respuesta = respuesta;
    }

    @Override
    public boolean test(Boolean aBoolean) {
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("tipoPregunta: Verdadero/Falso").append("\n");
        sb.append("\t").append(respuesta).append("\n");
        return sb.toString();
    }
}
