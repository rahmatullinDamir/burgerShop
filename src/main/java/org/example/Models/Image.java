package org.example.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class Image {
    private Long id;
    private String originalFileName;
    private String storageFileName;
    private Long size;
    private Long burgerid;
    private String type;
}
