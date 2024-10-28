package org.example.typeadapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.example.AppTrivial;
import org.example.Pregunta;
import org.example.PreguntaBoolean;
import org.example.PreguntaMultiple;

import java.io.IOException;

public class AppTrivialTypeAdapter extends TypeAdapter<AppTrivial> {
    @Override
    public void write(JsonWriter jsonWriter, AppTrivial appTrivial) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("results");
        jsonWriter.beginArray();
        for (Pregunta pregunta : appTrivial.getPreguntas()){
//            TypeAdapter<> preguntaAdapter;
//            if (pregunta instanceof PreguntaBoolean) {
//                preguntaAdapter = new PreguntaBooleanTypeAdapter();
//            } else if (pregunta instanceof PreguntaMultiple) {
//                preguntaAdapter = new PreguntaMultipleTypeAdapter();
//            } else {
//                throw new IOException("Tipo de pregunta desconocido");
//            }
//
//            preguntaAdapter.write(jsonWriter, pregunta);
        }
        jsonWriter.endArray();
        jsonWriter.endObject();
    }

    @Override
    public AppTrivial read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
