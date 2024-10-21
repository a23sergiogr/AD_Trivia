package org.example;

public final class Opcion {
    private String enunciado;
    private boolean correcta;

    public Opcion() {
    }

    public Opcion(String enunciado) {
        this.enunciado = enunciado;
        correcta = false;
    }

    public Opcion(String enunciado, boolean correcta) {
        this.enunciado = enunciado;
        this.correcta = correcta;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public boolean isCorrecta() {
        return correcta;
    }

    public void setCorrecta(boolean correcta) {
        this.correcta = correcta;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(enunciado).append("\n");
        if(isCorrecta())
            sb.append("\t[*]");
        return sb.toString();
    }
}
