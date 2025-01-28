package com.capstone.request_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "request_entity")
public class RequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private int requestId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "community_id", nullable = false)
    private int communityId;

    @Column(name = "request_reason")
    private String requestReason;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "request_date")
    private LocalDateTime requestDate;

    @Column(name = "status")
    private String status;

   }
