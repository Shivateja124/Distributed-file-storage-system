package com.shiva.DDFSS.dto;

public class FileResponse {
    private int id;
    private String originalFileName;

    public FileResponse(int id, String originalFileName) {
        this.id = id;
        this.originalFileName = originalFileName;
    }

    public int getId() {
        return id;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }
}
