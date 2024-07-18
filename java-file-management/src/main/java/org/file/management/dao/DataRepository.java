package org.file.management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<CSVData, String> {
    CSVData findByCode(String code);
}
