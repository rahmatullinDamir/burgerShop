package org.example.service.impl;

import org.example.Models.Image;
import org.example.service.ImageService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

public class ImageServiceImpl implements ImageService {
    @Override
    public void saveFileToStorage(InputStream file, String originalFileName, String contentType, Long dayId, Long size) {

    }

    @Override
    public void writeFileFromStorage(Long fileId, OutputStream outputStream) {

    }

    @Override
    public Image getFileInfo(Long fileId) {
        return null;
    }

    @Override
    public void deleteImage(Long fileId) throws SQLException, IOException {

    }

    @Override
    public Image getImageByDay(Long dayId) throws SQLException {
        return null;
    }

    @Override
    public Image getImageByParam(String originalName, Long dayId, Long size) throws SQLException {
        return null;
    }
}
