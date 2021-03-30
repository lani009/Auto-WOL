package org.lani.auto.wol;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import org.lani.auto.wol.wol.WolGenerator;

public class App {
    public static void main(String[] args) throws JsonIOException, JsonSyntaxException, FileNotFoundException, InterruptedException {
        JsonElement element = JsonParser.parseReader(new FileReader("./wol_file.json"));
        JsonArray dataArray = element.getAsJsonObject().get("data").getAsJsonArray();
        
        Thread.sleep(60L*3*1000);
        for (JsonElement jsonElement : dataArray) {
            var obj = jsonElement.getAsJsonObject();
            WolGenerator generator = new WolGenerator(obj.get("ip").getAsString(), obj.get("mac").getAsString(), obj.get("port").getAsInt());
            generator.send();
        }
    }
}
