package gugorrex.model.data;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import gugorrex.util.ExceptionHandler;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class BirthdayList {
    private ArrayList<Birthday> birthdays = new ArrayList<>();

    public ArrayList<Birthday> getBirthdays() {
        return birthdays;
    }

    public boolean addBirthday(Birthday birthday) {
        return birthdays.add(birthday);
    }

    public boolean removeBirthday(Birthday birthday) {
        return birthdays.remove(birthday);
    }

    public String toJson() {
        return BirthdayGsonProxy.getGson().toJson(this);
    }

    public String toPrettyJson() {
        return BirthdayGsonProxy.getPrettyGson().toJson(this);
    }

    public void save(String filename) {
        String path = System.getProperty("user.dir");
        File file = new File(path + "/" + filename);

        if (!file.exists()) {
            try {
                File directory = new File(file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.createNewFile();
            } catch (IOException e) {
                ExceptionHandler.exception(e);
            }
        }

        try {
            FileWriter fileWriter;
            fileWriter = new FileWriter(file.getAbsoluteFile(), false);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(toPrettyJson());
            bufferedWriter.close();
        } catch (IOException e) {
            ExceptionHandler.exception(e);
        }
    }

    public void load(String filename) {
        String path = System.getProperty("user.dir");
        File file = new File(path + "/" + filename);

        // if (!file.exists())

        if (file.exists()) {
            InputStreamReader inputStreamReader;
            try {
                inputStreamReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
                JsonReader myReader = new JsonReader(inputStreamReader);
                Gson gson = BirthdayGsonProxy.getPrettyGson();
                BirthdayList bdl = gson.fromJson(myReader, BirthdayList.class);
                birthdays = bdl.getBirthdays();
            } catch (FileNotFoundException e) {
                ExceptionHandler.exception(e);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BirthdayList that = (BirthdayList) o;

        return birthdays.equals(that.birthdays);
    }

    @Override
    public int hashCode() {
        return birthdays.hashCode();
    }
}
