package org.file.management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileRequest {
    @NotBlank(message = "Source shouldn't be blank")
    private String source;
    @NotBlank(message = "CodeListCode shouldn't be blank")
    private String codeListCode;
    @NotBlank(message = "Code shouldn't be blank")
    private String code;
    @NotBlank(message = "Display Message shouldn't be blank")
    private String displayValue;
    @NotBlank(message = "Description shouldn't be blank")
    private String longDescription;
    @NotBlank(message = "From Date shouldn't be blank")
    private String fromDate;
    private String toDate;
    private Integer sortingPriority;
}
