package org.example;

import java.util.Objects;

public final class Categoria {
    public static final String DEFAULT = "General knowledge";
    public final String nombre;

    public Categoria() {
        nombre = DEFAULT;
    }

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(nombre, categoria.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Categoria: ");
        sb.append(nombre).append("\n");
        return sb.toString();
    }
}

