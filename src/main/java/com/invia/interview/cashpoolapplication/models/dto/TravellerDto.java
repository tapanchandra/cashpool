package com.invia.interview.cashpoolapplication.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravellerDto {

    private long id;

    private String email;

    private String name;

}
