package ru.blogspot.feomatr.formBean;

import java.util.Arrays;

public class FileUploadBean {

    private byte[] file;

    public void setFile(byte[] file) {
        this.file = Arrays.copyOf(file, file.length);
    }

    public byte[] getFile() {
        return Arrays.copyOf(file, file.length);
    }
}
