package gugorrex.model.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BirthdayGsonProxy {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static GsonBuilder gson;

    private static GsonBuilder getGsonBuilder() {
        if (gson == null) {
            gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter(formatter));
        }
        return gson;
    }

    public static Gson getGson() {
        return getGsonBuilder().create();
    }

    public static Gson getPrettyGson() {
        return getGson().newBuilder().setPrettyPrinting().create();
    }
}
