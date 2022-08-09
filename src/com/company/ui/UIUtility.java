package com.company.ui;

import java.awt.*;

public class UIUtility {
    public static void setLocation(Window window, int width, int height) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        Double screenWidth = dimension.getWidth();
        Double screenHeight = dimension.getHeight();
        int x = (int) ((screenWidth - width) / 2);
        int h = (int) ((screenHeight - height) / 2);
        window.setLocation(x, h);
        window.setSize(width, height);
    }
}
