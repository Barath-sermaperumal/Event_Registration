package com.restapi.response;

import com.restapi.model.BookedUser;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderResponse {
    private Long id;
    private Long userId;
    private String userName;
    private Long eventId;
    private String eventName;
    private int count;
    private long totalPrice;
    private LocalDate date;
//    private List<BookedUser> bookedUserList;
}
