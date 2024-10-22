package org.example;

import com.google.gson.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    private static JsonObject getJsonObject(Pregunta pregunta) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", pregunta.getTipoPregunta().getTipoPregunta());
        jsonObject.addProperty("difficulty", pregunta.getDificultad().getDificultad());
        jsonObject.addProperty("category", pregunta.getCategoria().getNombre());
        jsonObject.addProperty("question", pregunta.getPregunta());
        return jsonObject;
    }

    private static JsonArray getJsonArray(List<Opcion> list, boolean correcta) {
        JsonArray jsonArray = new JsonArray();
        for (Opcion opcion : list) {
            if (opcion.isCorrecta() == correcta) {
                JsonElement jsonElement = new JsonPrimitive(opcion.getEnunciado());
                jsonArray.add(jsonElement);
            }
        }
        return jsonArray;
    }

    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(PreguntaBoolean.class, (JsonSerializer<PreguntaBoolean>) (preguntaBoolean, type, jsonSerializationContext) -> {
                    JsonObject jsonObject = getJsonObject(preguntaBoolean);
                    jsonObject.addProperty("correct_answer", preguntaBoolean.isRespuesta());
                    JsonArray jsonElements = new JsonArray();
                    JsonElement jsonElement = new JsonPrimitive(!preguntaBoolean.isRespuesta());
                    jsonElements.add(jsonElement);
                    jsonObject.add("incorrect_answers", jsonElements);
                    return jsonObject;
                })
                .registerTypeAdapter(PreguntaMultiple.class, (JsonSerializer<PreguntaMultiple>) (preguntaMultiple, type, jsonSerializationContext) -> {
                    JsonObject jsonObject = getJsonObject(preguntaMultiple);
                    List<Opcion> list = preguntaMultiple.getOpcionList();
                    if (preguntaMultiple.getNumCorrectas() == 1) {
                        for (Opcion opcion : list)
                            if (opcion.isCorrecta())
                                jsonObject.addProperty("correct_answer", opcion.getEnunciado());
                    } else {
                        JsonArray jsonElements = getJsonArray(list, true);
                        jsonObject.add("correct_answer", jsonElements);
                    }

                    if (list.size() - preguntaMultiple.getNumCorrectas() == 1) {
                        for (Opcion opcion : list)
                            if (!opcion.isCorrecta())
                                jsonObject.addProperty("incorrect_answers", opcion.getEnunciado());

                    } else {
                        JsonArray jsonElements = getJsonArray(list, false);
                        jsonObject.add("incorrect_answers", jsonElements);
                    }
                    return jsonObject;
                })
                .registerTypeAdapter(PreguntaBoolean.class, (JsonDeserializer<PreguntaBoolean>) (jsonElement, type, jsonDeserializationContext) -> {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    PreguntaBoolean preguntaBoolean = new PreguntaBoolean(jsonObject.get("question").getAsString(), jsonObject.get("correct_answer").getAsBoolean());
                    preguntaBoolean.setCategoria(new Categoria(jsonObject.get("category").getAsString()))
                            .setTipoPregunta(TipoPregunta.valueOf(jsonObject.get("type").getAsString().toUpperCase()))
                            .setDificultad(Dificultad.valueOf(jsonObject.get("difficulty").getAsString().toUpperCase()));
                    return preguntaBoolean;
                })
                .registerTypeAdapter(PreguntaMultiple.class, (JsonDeserializer<PreguntaMultiple>) (jsonElement, type, jsonDeserializationContext) -> {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    PreguntaMultiple preguntaMultiple = new PreguntaMultiple(jsonObject.get("question").getAsString());
                    preguntaMultiple.addOpcion(new Opcion(jsonObject.get("correct_answer").getAsString(), true))
                            .setCategoria(new Categoria(jsonObject.get("category").getAsString()))
                            .setTipoPregunta(TipoPregunta.valueOf(jsonObject.get("type").getAsString().toUpperCase()))
                            .setDificultad(Dificultad.valueOf(jsonObject.get("difficulty").getAsString().toUpperCase()));

                    JsonArray jsonArray = jsonObject.get("incorrect_answers").getAsJsonArray();
                    for (JsonElement element : jsonArray){
                        preguntaMultiple.addOpcion(new Opcion(element.getAsString()));
                    }

                    return preguntaMultiple;
                })
                .serializeNulls()
                .create();

        AppTrivial appTrivial = new AppTrivial();
        appTrivial.addPregunta(new PreguntaMultiple("Cual es la capital de españa?")
                .addOpcion(new Opcion("Sevilla"))
                .addOpcion(new Opcion("Valencia"))
                .addOpcion(new Opcion("Barcelona"))
                .addOpcion(new Opcion("Madrid", true))
                .setTipoPregunta(TipoPregunta.MULTIPLE)
                .setDificultad(Dificultad.EASY)
                .setCategoria(new Categoria("geografía")));

        Pregunta preguntaBoolean = new PreguntaBoolean("¿Java es un lenguaje de programación orientado a objetos?", true);
        preguntaBoolean.setCategoria(new Categoria("Programación"))
                .setTipoPregunta(TipoPregunta.BOOLEAN)
                .setDificultad(Dificultad.EASY);

        PreguntaMultiple preguntaMultiple = new PreguntaMultiple("¿Cuál de los siguientes lenguajes de programación no es orientado a objetos puro?");
        preguntaMultiple.addOpcion(new Opcion("Java"))
                .addOpcion(new Opcion("C", true))
                .addOpcion(new Opcion("Python"))
                .addOpcion(new Opcion("Modula-2"))
                .setCategoria(new Categoria("Programación"))
                .setTipoPregunta(TipoPregunta.MULTIPLE)
                .setDificultad(Dificultad.EASY);

        appTrivial.addPregunta(preguntaMultiple);
        appTrivial.addPregunta(preguntaBoolean);

//        System.out.println("appTrivial = " + appTrivial);

//        String json = gson.toJson(appTrivial);
//        System.out.println(json);
//        System.out.println(gson.fromJson(json, AppTrivial.class));

//        String json = gson.toJson(preguntaBoolean);
//        System.out.println(json);
//        System.out.println(gson.fromJson(json, PreguntaBoolean.class));

        String json = gson.toJson(preguntaMultiple);
        System.out.println(json);
        String prueba = "{\n" +
                "      \"type\": \"multiple\",\n" +
                "      \"difficulty\": \"medium\",\n" +
                "      \"category\": \"Entertainment: Music\",\n" +
                "      \"question\": \"Which member of the British pop group &quot;The Spice Girls&quot; was known as Ginger Spice?\",\n" +
                "      \"correct_answer\": \"Geri Halliwell\",\n" +
                "      \"incorrect_answers\": [\n" +
                "        \"Melanie Brown\",\n" +
                "        \"Emma Bunton\",\n" +
                "        \"Victoria Beckham\"\n" +
                "      ]\n" +
                "    }";
        System.out.println(gson.fromJson(prueba, PreguntaMultiple.class));

//        Path path = Paths.get("appTrivial.json");
//        try(var bw = Files.newBufferedWriter(path)){
//            gson.toJson(appTrivial,bw);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        AppTrivial appTrivial1;
//        try(var br = Files.newBufferedReader(path)){
//            appTrivial1 = gson.fromJson(br, AppTrivial.class);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(appTrivial1);

//        try(var fw = new FileWriter("pregunta.json")){
//            gson.toJson(pregunta,fw);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
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