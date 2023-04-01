package org.example.primerparcial;

import java.io.File;

public interface JSONParser<T> {
     void parse(File String);

     void parse (T source)throws Exception;

}