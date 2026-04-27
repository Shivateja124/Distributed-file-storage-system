package com.shiva.DDFSS.service;
import com.shiva.DDFSS.dto.FileResponse;
import com.shiva.DDFSS.model.FileData;
import com.shiva.DDFSS.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

//These packages are for file Downloading
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    private final FileRepository repo;

    // ✅ Always resolves to your project root
    private final String uploadDir =
            System.getProperty("user.dir") + File.separator + "uploads";

    public FileService(FileRepository repo) {
        this.repo = repo;
    }

    public String uploadFile(MultipartFile file, String username) {

        try {
            if (file.isEmpty()) {
                return "File is empty";
            }

            // ✅ Ensure directory exists
            File folder = new File(uploadDir);
            if (!folder.exists()) {
                boolean created = folder.mkdirs();
                System.out.println("Upload folder created: " + created);
            }

            // ✅ Clean filename
            String originalName = file.getOriginalFilename();
            String cleanName = originalName.replaceAll("[^a-zA-Z0-9._-]", "_");

            // ✅ Unique filename
            String uniqueName = UUID.randomUUID() + "_" + cleanName;
            // ✅ Full absolute path
            File destination = new File(folder, uniqueName);

            // ✅ Save file
            file.transferTo(destination);

            // ✅ Save metadata
            FileData fileData = new FileData(
                    originalName,
                    uniqueName,
                    destination.getAbsolutePath(),
                    username
            );
            repo.saveFile(fileData);

            return "File uploaded successfully";

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: " + e.getMessage();
        }
    }

    public List<FileResponse> getUserFiles(String username) {
        List<FileData> files = repo.getFilesByUsername(username);

        return files.stream()
                .map(file -> new FileResponse(file.getId(), file.getOriginalFileName()))
                .toList();
    }


    public FileData getFileById(int id,String username) {
        FileData fileData = repo.findById(id);
        if (fileData == null) {
            throw new RuntimeException("File not found");
        }
        //security Check
        System.out.println("DB Username: [" + fileData.getUsername() + "]");
        System.out.println("Request Username: [" + username + "]");
        if (!fileData.getUsername().trim().equalsIgnoreCase(username.trim())) {
            throw new RuntimeException("Unauthorized access");
        }

        return fileData;
    }

    public Resource loadFileAsResource(String uniqueFileName) {
        try {
            Path filePath = Paths.get("uploads").resolve(uniqueFileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("File not found in storage");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error while loading file: " + e.getMessage());
        }
    }

}