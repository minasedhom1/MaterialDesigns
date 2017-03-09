package com.example.lenovo.materialdesigns;

/**
 * Created by mido on 3/8/2017.
 */
public class App {
    private static App ourInstance = new App();

    public static App getInstance() {
        return ourInstance;
    }

    private App() {
    }
}
