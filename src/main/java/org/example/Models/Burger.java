package org.example.Models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Burger {
    private Long id;
    private int price;
    private String name;
    private String description;

}
