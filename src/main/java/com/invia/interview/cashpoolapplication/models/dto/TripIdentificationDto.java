package com.invia.interview.cashpoolapplication.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
@AllArgsConstructor
public class TripIdentificationDto {

    private @NonNull String tripCode;
    private String tripName;
}
