package com.example.mycalculator;

public class RectangleRepository {
    private static RectangleRepository instance;

    private int width;
    private int height;

    private RectangleRepository() {
        // празен конструктор
    }

    public static RectangleRepository getInstance() {
        if (instance == null) {
            instance = new RectangleRepository();
        }
        return instance;
    }

    public void setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
