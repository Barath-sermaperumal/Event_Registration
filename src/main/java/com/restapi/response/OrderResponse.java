package com.restapi.response;

import com.restapi.model.BookedUser;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderResponse {
    private Long id;
    private Long userId;
    private Long eventId;
    private int count;
//    private List<BookedUser> bookedUserList;
}
