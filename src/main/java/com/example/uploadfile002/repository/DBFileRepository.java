package com.example.uploadfile002.repository;

import com.example.uploadfile002.model.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBFileRepository extends JpaRepository<DBFile, String> {
}
