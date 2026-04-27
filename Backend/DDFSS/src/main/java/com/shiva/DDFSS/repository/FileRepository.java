package com.shiva.DDFSS.repository;

import com.shiva.DDFSS.model.FileData;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileRepository {

    private final JdbcTemplate jdbcTemplate;

    public FileRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveFile(FileData file) {
        String sql = "INSERT INTO files (original_file_name,unique_file_name, filepath, username) VALUES (?,?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                file.getOriginalFileName(),
                file.getUniqueFileName(),
                file.getFilepath(),
                file.getUsername()
        );
    }


    public List<FileData> getFilesByUsername(String username) {
        String sql = "SELECT * FROM files WHERE username = ?";
        return jdbcTemplate.query(sql, new Object[]{username}, (rs, rowNum) ->
                new FileData(
                        rs.getInt("id"),
                        rs.getString("original_file_name"),
                        rs.getString("unique_file_name"),
                        rs.getString("filepath"),
                        rs.getString("username")
                )
        );
    }


    public FileData findById(int id) {
        String sql = "SELECT * FROM files WHERE id = ?";
        List<FileData> list = jdbcTemplate.query(
                sql,
                new Object[]{id},
                (rs, rowNum) -> new FileData(
                        rs.getInt("id"),
                        rs.getString("original_file_name"),
                        rs.getString("unique_file_name"),
                        rs.getString("filepath"),
                        rs.getString("username")
                )
        );
        return list.isEmpty() ? null : list.get(0);
    }
}

