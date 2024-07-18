package org.file.management.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CSV_Data")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CSVData {
    @Id
    @Column(name = "code")
    public String code;

    @NonNull
    @Column(name = "source")
    private String source;

    @NonNull
    @Column(name = "code_list_code")
    private String codeListCode;

    @NonNull
    @Column(name = "display_value")
    private String displayValue;

    @NonNull
    @Column(name = "long_description")
    private String longDescription;

    @NonNull
    @Column(name = "from_date")
    private String fromDate;

    @Column(name = "to_date")
    private String toDate;

    @Column(name = "sorting_priority")
    private Integer sortingPriority;

}
