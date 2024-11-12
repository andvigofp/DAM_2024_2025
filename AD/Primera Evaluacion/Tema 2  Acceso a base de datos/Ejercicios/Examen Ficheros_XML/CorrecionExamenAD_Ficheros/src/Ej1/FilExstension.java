package Ej1;

import java.io.File;
import java.io.FilenameFilter;

public class FilExstension implements FilenameFilter {
    private String extension;

    public FilExstension(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.toLowerCase().contains(extension);
    }
}
