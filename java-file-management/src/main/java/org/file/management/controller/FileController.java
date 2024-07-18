package org.file.management.controller;

import org.file.management.dao.CSVData;
import org.file.management.model.UploadFileRequest;
import org.file.management.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manage")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<CSVData> uploadFile(@RequestBody UploadFileRequest request) {
        CSVData response = fileService.uploadFile(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<CSVData>> fetchAll() {
        List<CSVData> response = fileService.fetchAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/fetchByCode/{code}")
    public ResponseEntity<CSVData> fetchByCode(@PathVariable String code) {
        CSVData response = fileService.fetchByCode(code);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll() {
        String response = fileService.deleteAll();
        return ResponseEntity.ok(response);
    }
}
