package fr.seblaporte;

import io.vertx.core.Launcher;

public class Main {

    public static void main(final String[] args) {
        Launcher.executeCommand("run", LauncherVerticle.class.getName());
    }

}
