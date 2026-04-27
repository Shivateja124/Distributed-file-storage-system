package com.shiva.DDFSS.controller;
import com.shiva.DDFSS.dto.FileResponse;
import com.shiva.DDFSS.model.FileData;
import com.shiva.DDFSS.service.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//These packages are for file Downloading
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;


import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileService service;

    public FileController(FileService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public String uploadFile( @RequestParam("file") MultipartFile file,
                              @RequestParam("username") String username) {
        return service.uploadFile(file, username);
    }



    @GetMapping("/user/{username}")
    public List<FileResponse> getUserFiles(@PathVariable String username){
        return service.getUserFiles(username);
    }


    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable int id, @RequestParam String username) {

        FileData fileData = service.getFileById(id,username);

        Resource resource = service.loadFileAsResource(fileData.getUniqueFileName());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileData.getOriginalFileName() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
                .body(resource);
    }
}
