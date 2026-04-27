package com.shiva.DDFSS.model;
public class FileData {

    private int id;
    private String originalFileName;
    private String uniqueFileName;
    private String filepath;
    private String username;

    // Constructor
    public FileData() {

    }
    public FileData(int id, String originalFileName, String uniqueFileName,
                    String filepath, String username) {
        this.id = id;
        this.originalFileName = originalFileName;
        this.uniqueFileName = uniqueFileName;
        this.filepath = filepath;
        this.username = username;
    }
    public FileData(String originalFileName, String uniqueFileName,
                    String filepath, String username) {
        this.originalFileName = originalFileName;
        this.uniqueFileName = uniqueFileName;
        this.filepath = filepath;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getUniqueFileName() {
        return uniqueFileName;
    }

    public void setUniqueFileName(String uniqueFileName) {
        this.uniqueFileName = uniqueFileName;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getters
}

