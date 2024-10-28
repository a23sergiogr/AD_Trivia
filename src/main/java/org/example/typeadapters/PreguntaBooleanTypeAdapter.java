package org.example.typeadapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.example.Categoria;
import org.example.Dificultad;
import org.example.PreguntaBoolean;
import org.example.TipoPregunta;

import java.io.IOException;

public class PreguntaBooleanTypeAdapter extends TypeAdapter<PreguntaBoolean> {
    @Override
    public void write(JsonWriter jsonWriter, PreguntaBoolean pregunta) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("type").value(pregunta.getTipoPregunta().getTipoPregunta());
        jsonWriter.name("difficulty").value(pregunta.getDificultad().getDificultad());
        jsonWriter.name("category").value(pregunta.getCategoria().getNombre());
        jsonWriter.name("question").value(pregunta.getPregunta());
        jsonWriter.name("correct_answer").value(pregunta.isRespuesta());
        jsonWriter.name("incorrect_answers");
        jsonWriter.beginArray();
        jsonWriter.value(!pregunta.isRespuesta());
        jsonWriter.endArray();
        jsonWriter.endObject();
    }

    @Override
    public PreguntaBoolean read(JsonReader jsonReader) throws IOException {
        PreguntaBoolean pregunta = new PreguntaBoolean();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            switch (name) {
                case "type" -> pregunta.setTipoPregunta(TipoPregunta.valueOf(jsonReader.nextString().toUpperCase()));
                case "difficulty" -> pregunta.setDificultad(Dificultad.valueOf(jsonReader.nextString().toUpperCase()));
                case "category" -> pregunta.setCategoria(new Categoria(jsonReader.nextString()));
                case "question" -> pregunta.setPregunta(jsonReader.nextString());
                case "correct_answer" -> pregunta.setRespuesta(jsonReader.nextBoolean());
                case "incorrect_answers" -> {
                    jsonReader.skipValue();
                }
                default -> {}
            }
        }
        jsonReader.endObject();
        return pregunta;
    }
}
