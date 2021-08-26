package hr.fer.oop.lab5;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

class FileEditor {
      public static void processFile(Path inputFile, Path outputFile) throws IOException{
          Stream<String> stream = Files.lines(inputFile);
            List<String> res = new ArrayList<>();
            for (String s:(Iterable<String>)stream::iterator) {
                res.add(s.split("// TODO")[0]);
            }
            FileWriter fw = new FileWriter(outputFile.toFile());
            for (String s:res) {
                fw.write(s);
                fw.write("\n");
            }
            fw.close();
      }
}
