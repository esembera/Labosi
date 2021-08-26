package hr.fer.oop.lab5;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

class Grep {
    List<String> lines;
    
    public Grep(Path file) throws IOException {
        Charset c = StandardCharsets.UTF_8;
        lines = Files.readAllLines(file, c);
    }
    public List<TextLine> findLinesWithWord(String word) {
        ArrayList<TextLine> tl=new ArrayList<>();
        int c = 0;
        for(String line : lines) {
            if(line.contains(word)) {
                tl.add(new TextLine(c+1, line));
            }
            ++c;
        }
        return tl; 
    }
    public List<Integer> findLineNumbersWithWord(String word) {
        ArrayList<Integer> il=new ArrayList<>();
        int c = 0;
        for(String line : lines) {
            if(line.contains(word)) {
                il.add(c+1);
            }
            ++c;
        }
        return il;
    }
}

class TextLine {
    
      private int lineNumber;
      private String text;

      public TextLine(int lineNumber, String text) {
        this.lineNumber = lineNumber;
        this.text = text;
      }
      
      public String toString() {
          if(text == null) {
              return lineNumber + "";
          }
          else {
              return lineNumber + ": " + text;
          }
      }
      
      public String getText() {
          return text;
      }
      
      public int getLineNumber() {
          return lineNumber;
      }
}
