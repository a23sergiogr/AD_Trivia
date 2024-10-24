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

    public boolean isRespuesta() {
        return respuesta;
    }

    public PreguntaBoolean setRespuesta(boolean respuesta) {
        this.respuesta = respuesta;
        return this;
    }

    @Override
    public boolean test(Boolean aBoolean) {
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\t").append(respuesta).append("\n");
        return sb.toString();
    }
}
