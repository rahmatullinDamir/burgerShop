package org.example.Models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Burger {
    Long id;
    int price;
    String name;
    String description;

}
