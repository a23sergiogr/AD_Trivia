package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Pregunta pregunta = new Pregunta()
                .setTipoPregunta(TipoPregunta.MULTIPLE)
                .setDificultad(Dificultad.EASY  )
                .setCategoria(new Categoria("geografía"))
                .setPregunta("Cual es la capital de españa:")
                .setCorrecta("Madrid")
                .setIncorrectas(List.of("Sevilla","Valencia","Barcelona"));



        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .create();

//        try(var fw = new FileWriter("pregunta.json")){
//            gson.toJson(pregunta,fw);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        try(var bw = Files.newBufferedWriter(Paths.get("pregunta2.json"))){
            gson.toJson(pregunta,bw);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (var writer = new JsonWriter(new FileWriter("preguntaJW.json"))){
            writer.beginObject();
            writer.name("type").value(pregunta.getTipoPregunta().getTipoPregunta());
            writer.name("difficulty").value(pregunta.getDificultad().getDificultad());
            writer.name("category").value(pregunta.getCategoria().getNombre());
            writer.name("question").value(pregunta.getPregunta());
            writer.name("correct_answer").value(pregunta.getCorrecta());
            writer.name("incorrect_answers");
            writer.beginArray();
            for (String str : pregunta.getIncorrectas()) {
                writer.value(str);
            }
            writer.endArray();
            writer.endObject();
        } catch (IOException e) {
        throw new RuntimeException(e);
        }



        Pregunta pregunta1;
        try(var br = Files.newBufferedReader(Paths.get("pregunta.json"))){
            pregunta1 = gson.fromJson(br, Pregunta.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(pregunta1);

        Pregunta pregunta2 = new Pregunta();
        try(var reader = new JsonReader(new FileReader("preguntaJW.json"))){
            reader.beginObject();
            while (reader.hasNext()){
                String name = reader.nextName();
                if (name.equals("type"))
                    pregunta2.setTipoPregunta(TipoPregunta.valueOf(reader.nextString().toUpperCase()));
                if (name.equals("difficulty"))
                    pregunta2.setDificultad(Dificultad.valueOf(reader.nextString().toUpperCase()));
                if (name.equals("category"))
                    pregunta2.setCategoria(new Categoria(reader.nextString()));
                if (name.equals("question"))
                    pregunta2.setPregunta(reader.nextString());
                if (name.equals("correct_answer"))
                    pregunta2.setCorrecta(reader.nextString());
                if (name.equals("incorrect_answers")){
                    reader.beginArray();
                    while (reader.hasNext())
                        pregunta2.addIncorecta(reader.nextString());
                    reader.endArray();
                }
            }
            reader.endObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(pregunta2);
    }
}