package org.example.primerparcial;

import java.util.*;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Dictionary<K,V> implements JSONParser<String> {
    public Dictionary() {
        data = new ArrayList<>();
    }

    private Map<K, V> entries = new HashMap<>();
    @Override
    public void parse(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    K key = (K) parts[0].trim();
                    V value = (V) parts[1].trim();
                    entries.put(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private List<Entry<K, V>> data;

   /* public void parse(String jsonString) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<K, V>>(){}.getType();
        Map<K, V> map = gson.fromJson(jsonString, type);
        for (Map.Entry<K, V> entry : map.entrySet()) {
            data.add(new Entry<>(entry.getKey(), entry.getValue()));
        }
    }*/
   public void parse(String jsonString) {
       Gson gson = new Gson();
       Type type = new TypeToken<Map<K, V>>(){}.getType();
       Map<K, V> map = gson.fromJson(jsonString, type);
       entries.putAll(map);

   }

    public Iterable<Map.Entry<K, V>> elements() {
        return entries.entrySet();
    }


}