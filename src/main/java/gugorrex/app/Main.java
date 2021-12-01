package gugorrex.app;

import gugorrex.util.MainUncaughtExceptionHandler;

public class Main {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new MainUncaughtExceptionHandler());
        App.main(args);
    }
}
