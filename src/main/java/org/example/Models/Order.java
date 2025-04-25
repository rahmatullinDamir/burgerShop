package org.example.Models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Order {
    private Long id;
    private Long userId;
    private Long burgerid;
    private Long quantity;
}
