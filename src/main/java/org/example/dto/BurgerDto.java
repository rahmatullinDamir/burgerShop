package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Models.Image;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BurgerDto {
    private int price;
    private String name;
    private String description;
}
