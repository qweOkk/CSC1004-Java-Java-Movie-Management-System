package org.example.utils;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class UIAdjistUtils {
    public static void adjustImageBorder(ImageView image, Rectangle border) {

        Double height = image.getFitHeight();
        Double width = image.getFitWidth();
        border.setWidth(width + 15);
        border.setHeight(height + 15);
    }
}
