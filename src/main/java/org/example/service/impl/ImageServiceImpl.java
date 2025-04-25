package org.example.service.impl;

import org.example.Models.Image;
import org.example.repositories.ImageRepository;
import org.example.service.ImageService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void saveFileToStorage(InputStream file, String originalFileName, String contentType, Long burgerid, Long size) {
        Image image = Image.builder()
                .originalFileName(originalFileName)
                .storageFileName(UUID.randomUUID().toString())
                .size(size)
                .burgerid(burgerid)
                .type(contentType)
                .build();

        try {
            Files.copy(file, Paths.get("C://Users//kim85//IdeaProjects//orisSemestr//src//main//webapp//image/" + image.getStorageFileName() + "." + image.getType().split("/")[1]));
            imageRepository.save(image);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public void writeFileFromStorage(Long fileId, OutputStream outputStream) {
        Image image = null;
        try {
            Optional<Image> imageOptional = imageRepository.findById(fileId);
            if (imageOptional.isPresent()) {
                image = imageOptional.get();
                File file = new File("C://Users//kim85//IdeaProjects//orisSemestr//src//main//webapp//image/" + image.getStorageFileName() + "." + image.getType().split("/")[1]);
                Files.copy(file.toPath(), outputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Image getFileInfo(Long fileId) {
        Optional<Image> imageOptional = imageRepository.findById(fileId);
        if (imageOptional.isPresent()) {
            return imageOptional.get();
        }
        return null;
    }

    @Override
    public void deleteImage(Long fileId) throws IOException {
        Optional<Image> imageOptional = imageRepository.findById(fileId);
        if (imageOptional.isPresent()) {
            Image image = imageOptional.get();
            String filePath = "C://Users//kim85//IdeaProjects//orisSemestr//src//main//webapp//image/" + image.getStorageFileName() + "." + image.getType().split("/")[1];
            Files.delete(Paths.get(filePath));
            imageRepository.remove(fileId);

        }
    }

    @Override
    public Image getImageByBurger(Long burgerid) {
        Optional<Image> imageOptional = imageRepository.findBurgerById(burgerid);
        if (imageOptional.isPresent()) {
            return imageOptional.get();
        }
        return null;
    }

    @Override
    public Image getImageByParam(String originalName, Long burgerid, Long size) {
        Optional<Image> imageOptional = imageRepository.findIdByImage(originalName, burgerid, size);
        if (imageOptional.isPresent()) {
            return imageOptional.get();
        }
        return null;
    }
}
