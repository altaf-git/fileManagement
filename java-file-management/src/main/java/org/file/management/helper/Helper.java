package org.file.management.helper;

import org.file.management.dao.CSVData;
import org.file.management.dao.DataRepository;
import org.file.management.model.UploadFileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class Helper {
    @Autowired
    private DataRepository repository;

    public CSVData uploadFile(final UploadFileRequest request) {
        CSVData data = new CSVData(request.getCode(), request.getSource(), request.getCodeListCode(), request.getDisplayValue(), request.getLongDescription(), request.getFromDate(), request.getToDate(), request.getSortingPriority());
        final CSVData response = repository.save(data);
        return response;
    }

    public List<CSVData> fetchAll() {
        final List<CSVData> responses = repository.findAll();
        return responses;
    }

    public CSVData fetchByCode(final String code) {
        final CSVData response = repository.findByCode(code);
        return response;
    }

    public String deleteAll() {
        repository.deleteAll();
        return "Deleted Successfully";
    }


}
