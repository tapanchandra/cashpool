package com.invia.interview.cashpoolapplication.models.dto;

import com.invia.interview.cashpoolapplication.models.Trip;
import lombok.*;
import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripDto {

    private Long Id;

    private String tripCode;

    private Date createdDate;

    private String tripName;

    private String tripLocation;

    private String tripDescription;

    private Date startDate;

    private Date endDate;

    private Trip.Status tripStatus;

    @Singular
    private List<TravellerDto> travellers;

    @Singular
    private List<ExpenseDto> expenses;

    @Singular
    private List<PassbookEntryDto> passbookEntries;
}
