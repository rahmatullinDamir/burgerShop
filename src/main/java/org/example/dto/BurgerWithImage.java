package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Models.Image;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BurgerWithImage {
    BurgerDto burger;
    Image image;
}
