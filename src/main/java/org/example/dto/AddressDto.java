package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {
    private Long id;
    private String street;
    private String city;
    private Long house;
    private Long flat;
}
