package com.restapi.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReportResponse {
    private String eventName;
    private LocalDate localDate;
    private int tickets_sold;
}
