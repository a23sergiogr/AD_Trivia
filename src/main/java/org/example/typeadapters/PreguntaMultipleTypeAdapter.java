package org.example.typeadapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.example.*;

import java.io.IOException;
import java.util.List;

public class PreguntaMultipleTypeAdapter extends TypeAdapter<PreguntaMultiple> {
    @Override
    public void write(JsonWriter jsonWriter, PreguntaMultiple pregunta) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("type").value(pregunta.getTipoPregunta().getTipoPregunta());
        jsonWriter.name("difficulty").value(pregunta.getDificultad().getDificultad());
        jsonWriter.name("category").value(pregunta.getCategoria().getNombre());
        jsonWriter.name("question").value(pregunta.getPregunta());
        List<Opcion> opcionList = pregunta.getOpcionList();
        for (Opcion opcion : opcionList) {
            if (opcion.isCorrecta()) {
                jsonWriter.name("correct_answer").value(opcion.getEnunciado());
            }
        }
        jsonWriter.name("incorrect_answers");
        jsonWriter.beginArray();
        for (Opcion opcion : opcionList) {
            if (!opcion.isCorrecta()) {
                jsonWriter.value(opcion.getEnunciado());
            }
        }
        jsonWriter.endArray();
        jsonWriter.endObject();
    }

    @Override
    public PreguntaMultiple read(JsonReader jsonReader) throws IOException {
        PreguntaMultiple pregunta = new PreguntaMultiple();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            switch (name) {
                case "type" -> pregunta.setTipoPregunta(TipoPregunta.valueOf(jsonReader.nextString().toUpperCase()));
                case "difficulty" -> pregunta.setDificultad(Dificultad.valueOf(jsonReader.nextString().toUpperCase()));
                case "category" -> pregunta.setCategoria(new Categoria(jsonReader.nextString()));
                case "question" -> pregunta.setPregunta(jsonReader.nextString());
                case "correct_answer" -> pregunta.addOpcion(new Opcion(jsonReader.nextString(), true));
                case "incorrect_answers" -> {
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        pregunta.addOpcion(new Opcion(jsonReader.nextString()));
                    }
                    jsonReader.endArray();
                }
                default -> {}
            }
        }
        jsonReader.endObject();
        return pregunta;
    }
}
