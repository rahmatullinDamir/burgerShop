package org.example.service;

import org.example.Models.Image;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

public interface ImageService {
    void saveFileToStorage(InputStream file, String originalFileName, String contentType, Long burgerId, Long size);

    void writeFileFromStorage(Long fileId, OutputStream outputStream);

    Image getFileInfo(Long fileId);

    void deleteImage(Long fileId) throws SQLException, IOException;

    Image getImageByDay(Long burgerId) throws SQLException;

    Image getImageByParam(String originalName, Long burgerId, Long size) throws SQLException;
}
