package com.capstone.request_service.pojo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class RequestPojo {
    private int requestId;
    private UserOutputDataPojo user;
    private CommunityPojo community;
    private String requestReason;
    private Double amount;
    private LocalDateTime requestDate;
    private String status;    
}
