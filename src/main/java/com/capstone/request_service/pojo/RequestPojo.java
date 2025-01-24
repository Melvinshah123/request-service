package com.capstone.request_service.pojo;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestPojo {
    private int requestId;
    private String username;
    private int communityId;
    private String requestReason;
    private Double amount;
    private LocalDateTime requestDate;
    private String status;    
}
