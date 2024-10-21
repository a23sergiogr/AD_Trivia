package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PreguntaMultiple extends Pregunta implements Predicate<Integer> {
    List<Opcion> opcionList;

    public PreguntaMultiple() {
        this.opcionList = new ArrayList<>();
    }

    public PreguntaMultiple(String pregunta) {
        super(pregunta);
        this.opcionList = new ArrayList<>();
    }

    public PreguntaMultiple addOpcion(Opcion opcion){
        opcionList.add(opcion);
        return this;
    }

    public PreguntaMultiple addOpciones(List<Opcion> opcions){
        opcionList.addAll(opcions);
        return this;
    }

    public List<Opcion> getOpcionList() {
        return opcionList;
    }

    public PreguntaMultiple setOpcionList(List<Opcion> opcionList) {
        this.opcionList = opcionList;
        return this;
    }

    public int getNumCorrectas(){
        int n = 0;
        for (Opcion opcion : opcionList)
            if (opcion.isCorrecta())
                n++;
        return n;
    }

    public int getPuntos(List<Integer> marcadas){
        int n = 0;
        for (Integer i : marcadas){
            if (test(i))
                n++;
            n--;
        }
        return n;
    }

    @Override
    public boolean test(Integer integer) {
        return opcionList.get(integer).isCorrecta();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("tipoPregunta: Múltiple").append("\n");
        for (Opcion opcion : opcionList)
            sb.append("\t").append(opcion);
        return sb.toString();
    }
}
