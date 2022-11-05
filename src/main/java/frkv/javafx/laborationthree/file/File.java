package frkv.javafx.laborationthree.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import frkv.javafx.laborationthree.model.Shape;
import javafx.collections.ObservableList;

public class File {

    public static void createFile(Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            System.out.println(e.getClass());
        }
    }

    public static void saveFile(Path path, ObservableList<Shape> shapes) {
        if (!Files.exists(path))
            createFile(path);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<svg width=\"800\" height=\"600\" xmlns='http://www.w3.org/2000/svg'>\n");
        for (Shape shape:shapes) {
            stringBuilder.append("    ").append(shape.svg()).append("\n");
        }
        stringBuilder.append("</svg>\n");

        try {
            Files.writeString(path,stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}