package org.example.utils;

import javafx.stage.FileChooser;
import org.example.Main;

import java.io.*;
//Adjust the image
public class ImageUtils {
    public static void save(File image) {
        File imageFileCopy = new File("src/main/resources/org/example/scene/images/", image.getName());

        InputStream in = null;
        OutputStream out = null;

        try {
            if (!imageFileCopy.exists()) {
                imageFileCopy.createNewFile();
            }
            in = new FileInputStream(image);
            out = new FileOutputStream(imageFileCopy);
            byte[] temp = new byte[1024];
            int length = 0;
            while ((length = in.read(temp)) != -1) {
                out.write(temp, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // close the in & out stream
                in.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static File choose(Main mainApp) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter(
                "JPG files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter jpegFilter = new FileChooser.ExtensionFilter(
                "JPEG files (*.jpeg)", "*.jpeg");
        FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter(
                "PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(jpgFilter);
        fileChooser.getExtensionFilters().add(jpegFilter);
        fileChooser.getExtensionFilters().add(pngFilter);
        File image = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        return image;
    }

    public static void delete(String imageName) {
        File waitToDeletaImage = new File("src/main/resources/org/example/scene/images/", imageName);
        waitToDeletaImage.delete();
        System.out.println("Cover" + imageName + "has been deleted!");
    }
}
