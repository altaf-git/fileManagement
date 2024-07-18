package org.file.management.service;

import org.file.management.dao.CSVData;
import org.file.management.helper.Helper;
import org.file.management.model.UploadFileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    @Autowired
    private Helper helper;

    public CSVData uploadFile(UploadFileRequest request) {
        try {
            CSVData response = helper.uploadFile(request);
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Failing to upload file: " + e.getMessage());
        }
    }

    public List<CSVData> fetchAll() {
        try {
            List<CSVData> responses = helper.fetchAll();
            return responses;
        } catch (Exception e) {
            throw new RuntimeException("Failing to fetch all the data's: " + e.getMessage());
        }
    }

    public CSVData fetchByCode(final String code) {
        try {
            final CSVData response = helper.fetchByCode(code);
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Failing to fetchByCode: " + e.getMessage());
        }
    }

    public String deleteAll() {
        try {
            final String response = helper.deleteAll();
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Failing to deleteAll: " + e.getMessage());
        }
    }


}
