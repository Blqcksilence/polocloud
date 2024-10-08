package dev.httpmarco.polocloud.launcher.dependency;

import dev.httpmarco.polocloud.launcher.PoloCloudLauncher;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Path;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.jar.JarFile;

@UtilityClass
public class DependencyDownloader {

    private static final Path DOWNLOAD_DIR = Path.of("local/dependencies");

    @SneakyThrows
    public void download(@NotNull Dependency dependency) {
        DOWNLOAD_DIR.toFile().mkdirs();
        var file = DOWNLOAD_DIR.resolve(dependency + ".jar").toFile();

        if (dependency.withSubDependencies()) {
            dependency.loadSubDependencies();
            for (var subDependency : dependency.subDependencies()) {
                download(subDependency);
            }
        }

        if (!file.exists()) {
            DependencyHelper.download(dependency.downloadUrl(), file);
        }
        PoloCloudLauncher.CLASS_LOADER.addURL(file.toURI().toURL());
    }


    public static void download(Dependency... dependencies) {
        downloadDependenciesWithProgress(List.of(dependencies));
    }

    private void downloadDependenciesWithProgress(@NotNull List<Dependency> dependencies) {
        int totalDependencies = dependencies.size();
        for (int i = 0; i < totalDependencies; i++) {
            var dependency = dependencies.get(i);
            logProgress(totalDependencies, i + 1, dependency);
            download(dependency);
        }

        clearTerminal();
    }

    private void logProgress(int total, int current, Dependency dependency) {
        if (findDependency(dependency).exists()) {
            return;
        }
        System.out.printf("Downloading Dependency - %s %d %d \n", dependency.artifactId(), current, total);
    }

    private void clearTerminal() {
        defaultSys("\r", " ".repeat(80), "\r");
    }

    private void defaultSys(String @NotNull ... messages) {
        for (var message : messages) {
            System.out.print(message);
        }
    }

    private @NotNull File findDependency(Dependency dependency) {
        return DOWNLOAD_DIR.resolve(dependency + ".jar").toFile();
    }
}
