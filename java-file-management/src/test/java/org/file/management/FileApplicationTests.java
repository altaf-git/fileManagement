package org.file.management;

import org.file.management.dao.CSVData;
import org.file.management.helper.Helper;
import org.file.management.model.UploadFileRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.DELETE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FileApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private Helper helper;


    @Test
    void testAddData() {
        String url = "http://localhost:" + port + "/manage/upload";
        UploadFileRequest request = new UploadFileRequest("ZIB", "ZIB001", "271636001", "Polsslag regelmatig", "The long description is necessary", "01-01-2019", "", 1);
        HttpEntity<UploadFileRequest> entity = new HttpEntity<>(request);
        ResponseEntity<CSVData> response = restTemplate.postForEntity(url, entity, CSVData.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        Assertions.assertEquals(response.getBody().code, "271636001");
    }

    @Test
    void testfetchAll() {
        String url = "http://localhost:" + port + "/manage/fetchAll";
        UploadFileRequest request = new UploadFileRequest("ZIB", "ZIB001", "271636001", "Polsslag regelmatig", "The long description is necessary", "01-01-2019", "", 1);
        helper.uploadFile(request);
        ResponseEntity<List<CSVData>> response = restTemplate.exchange(url, org.springframework.http.HttpMethod.GET, null, new ParameterizedTypeReference<List<CSVData>>() {
        });
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        Assertions.assertEquals(response.getBody().get(0).code, "271636001");
    }

    @Test
    void testfetchByCode() {
        String url = "http://localhost:" + port + "/manage/fetchByCode/271636002";
        UploadFileRequest request = new UploadFileRequest("ZIB", "ZIB001", "271636002", "Polsslag regelmatig", "The long description is necessary", "01-01-2019", "", 1);
        helper.uploadFile(request);
        ResponseEntity<CSVData> response = restTemplate.exchange(url, org.springframework.http.HttpMethod.GET, null, new ParameterizedTypeReference<CSVData>() {}); // Using ParameterizedTypeReference for deserialization because wasn't able to fetch the values
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        Assertions.assertEquals(response.getBody().code, "271636002");
    }

    @Test
    void testdeleteAll() {
        String url = "http://localhost:" + port + "/manage/deleteAll";
        UploadFileRequest request = new UploadFileRequest("ZIB", "ZIB001", "271636002", "Polsslag regelmatig", "The long description is necessary", "01-01-2019", "", 1);
        UploadFileRequest request2 = new UploadFileRequest("ZIB", "ZIB001", "61086009", "Polsslag onregelmatig", "Something", "01-01-2019", "", 2);
        helper.uploadFile(request);
        helper.uploadFile(request2);
        ResponseEntity<String> response = restTemplate.exchange(url, DELETE, null, String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        Assertions.assertEquals(response.getBody(), "Deleted Successfully");

        //Going to Fetch all the data again and checking the size
        String url2 = "http://localhost:" + port + "/manage/fetchAll";
        ResponseEntity<List<CSVData>> response2 = restTemplate.exchange(url2, org.springframework.http.HttpMethod.GET, null, new ParameterizedTypeReference<List<CSVData>>() {
        });
        assertThat(response2.getStatusCodeValue()).isEqualTo(200);
        Assertions.assertEquals(response2.getBody().size(), 0);
    }

}
