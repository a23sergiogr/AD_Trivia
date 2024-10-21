package org.example;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
//                .registerTypeAdapter(TipoPregunta.class, (JsonSerializer<TipoPregunta>) (tipoPregunta, type, jsonSerializationContext) -> new JsonPrimitive(tipoPregunta.getTipoPregunta()))
//                .registerTypeAdapter(Dificultad.class, (JsonSerializer<Dificultad>) (dificultad, type, jsonSerializationContext) -> new JsonPrimitive(dificultad.getDificultad()))
//                .registerTypeAdapter(Categoria.class, (JsonSerializer<Categoria>) (categoria, type, jsonSerializationContext) -> new JsonPrimitive(categoria.getNombre().toLowerCase()))
                .registerTypeAdapter(Pregunta.class, (JsonSerializer<Pregunta>) (pregunta, type, jsonSerializationContext) -> {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("type", pregunta.getTipoPregunta().getTipoPregunta());
                    jsonObject.addProperty("difficulty", pregunta.getDificultad().getDificultad());
                    jsonObject.addProperty("category", pregunta.getCategoria().getNombre());
                    jsonObject.addProperty("question", pregunta.getPregunta());
                    return jsonObject;
                })
                .registerTypeAdapter(PreguntaMultiple.class, (JsonSerializer<PreguntaMultiple>) (preguntaMultiple, type, jsonSerializationContext) -> {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("type", preguntaMultiple.getTipoPregunta().getTipoPregunta());
                    jsonObject.addProperty("difficulty", preguntaMultiple.getDificultad().getDificultad());
                    jsonObject.addProperty("category", preguntaMultiple.getCategoria().getNombre());
                    jsonObject.addProperty("question", preguntaMultiple.getPregunta());
                    List<Opcion> list = preguntaMultiple.getOpcionList();
                    for (Opcion opcion : list)
                        if (opcion.isCorrecta())
                            jsonObject.addProperty("correct_answer", opcion.getEnunciado());


                    JsonArray jsonElements = new JsonArray();
                    for (Opcion opcion : list)
                        if (!opcion.isCorrecta())
                            jsonElements.add(opcion.getEnunciado());

                    jsonObject.addProperty("incorrect_answers", String.valueOf(jsonElements));
                    return jsonObject;
                })
                .serializeNulls()
                .create();

        AppTrivial appTrivial = new AppTrivial();
        appTrivial.addPregunta(new PreguntaMultiple("Cual es la capital de españa? ")
                .addOpcion(new Opcion("Sevilla"))
                .addOpcion(new Opcion("Valencia"))
                .addOpcion(new Opcion("Barcelona"))
                .addOpcion(new Opcion("Madrid",true))
                .setTipoPregunta(TipoPregunta.MULTIPLE)
                .setDificultad(Dificultad.EASY )
                .setCategoria(new Categoria("geografía")));

        Pregunta preguntaBoolean = new PreguntaBoolean("¿Java es un lenguaje de programación orientado a objetos?", true);
        preguntaBoolean.setCategoria(new Categoria("Programación"))
                .setTipoPregunta(TipoPregunta.BOOLEAN)
                .setDificultad(Dificultad.EASY);

        Pregunta preguntaMultiple = new PreguntaMultiple("¿Cuál de los siguientes lenguajes de programación es orientado a objetos puro?");
        ((PreguntaMultiple)preguntaMultiple).addOpcion(new Opcion("Java", true))
                .addOpcion(new Opcion("C", false))
                .addOpcion(new Opcion("Python", true))
                .addOpcion(new Opcion("Modula-2", true))
                .setCategoria(new Categoria("Programación"))
                .setTipoPregunta(TipoPregunta.MULTIPLE)
                .setDificultad(Dificultad.EASY);

        appTrivial.addPregunta(preguntaMultiple);
        appTrivial.addPregunta(preguntaBoolean);

        //System.out.println("appTrivial = " + appTrivial);

        String json = gson.toJson(appTrivial);
        System.out.println(json);




//        try(var fw = new FileWriter("pregunta.json")){
//            gson.toJson(pregunta,fw);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        try(var bw = Files.newBufferedWriter(Paths.get("pregunta2.json"))){
//            gson.toJson(pregunta,bw);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        try (var writer = new JsonWriter(new FileWriter("preguntaJW.json"))){
//            writer.beginObject();
//            writer.name("type").value(pregunta.getTipoPregunta().getTipoPregunta());
//            writer.name("difficulty").value(pregunta.getDificultad().getDificultad());
//            writer.name("category").value(pregunta.getCategoria().getNombre());
//            writer.name("question").value(pregunta.getPregunta());
//            writer.name("correct_answer").value(pregunta.getCorrecta());
//            writer.name("incorrect_answers");
//            writer.beginArray();
//            for (String str : pregunta.getIncorrectas()) {
//                writer.value(str);
//            }
//            writer.endArray();
//            writer.endObject();
//        } catch (IOException e) {
//        throw new RuntimeException(e);
//        }
//
//
//
//        Pregunta pregunta1;
//        try(var br = Files.newBufferedReader(Paths.get("pregunta.json"))){
//            pregunta1 = gson.fromJson(br, Pregunta.class);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(pregunta1);
//
//        Pregunta pregunta2 = new Pregunta();
//        try(var reader = new JsonReader(new FileReader("preguntaJW.json"))){
//            reader.beginObject();
//            while (reader.hasNext()){
//                String name = reader.nextName();
//                if (name.equals("type"))
//                    pregunta2.setTipoPregunta(TipoPregunta.valueOf(reader.nextString().toUpperCase()));
//                if (name.equals("difficulty"))
//                    pregunta2.setDificultad(Dificultad.valueOf(reader.nextString().toUpperCase()));
//                if (name.equals("category"))
//                    pregunta2.setCategoria(new Categoria(reader.nextString()));
//                if (name.equals("question"))
//                    pregunta2.setPregunta(reader.nextString());
//                if (name.equals("correct_answer"))
//                    pregunta2.setCorrecta(reader.nextString());
//                if (name.equals("incorrect_answers")){
//                    reader.beginArray();
//                    while (reader.hasNext())
//                        pregunta2.addIncorecta(reader.nextString());
//                    reader.endArray();
//                }
//            }
//            reader.endObject();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println(pregunta2);
    }
}