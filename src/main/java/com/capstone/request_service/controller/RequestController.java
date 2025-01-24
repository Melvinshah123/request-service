package com.capstone.request_service.controller;

import com.capstone.request_service.pojo.RequestPojo;
import com.capstone.request_service.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RequestController {

    @Autowired
    private RequestService requestService;

    // Add a new request
    @PostMapping("/requests")
    public ResponseEntity<RequestPojo> addRequest(@RequestBody RequestPojo requestPojo) {
        RequestPojo createdRequest = requestService.addRequest(requestPojo);
        return ResponseEntity.ok(createdRequest);
    }

    // Get all requests
    @GetMapping("/requests")
    public ResponseEntity<List<RequestPojo>> getAllRequests() {
        List<RequestPojo> requests = requestService.getAllRequests();
        return ResponseEntity.ok(requests);
    }

    // Get request by ID
    @GetMapping("/requests/{requestId}")
    public ResponseEntity<RequestPojo> getRequestById(@PathVariable int requestId) {
        RequestPojo request = requestService.getRequestById(requestId);
        return ResponseEntity.ok(request);
    }

    // Get requests by username
    @GetMapping("/requests/user/{username}")
    public ResponseEntity<List<RequestPojo>> getRequestsByUsername(@PathVariable String username) {
        List<RequestPojo> requests = requestService.getRequestsByUsername(username);
        return ResponseEntity.ok(requests);
    }

    // Get requests by community ID
    @GetMapping("/requests/community/{communityId}")
    public ResponseEntity<List<RequestPojo>> getRequestsByCommunityId(@PathVariable int communityId) {
        List<RequestPojo> requests = requestService.getRequestsByCommunityId(communityId);
        return ResponseEntity.ok(requests);
    }

    // Get requests by status
    @GetMapping("/requests/status/{status}")
    public ResponseEntity<List<RequestPojo>> getRequestsByStatus(@PathVariable String status) {
        List<RequestPojo> requests = requestService.getRequestsByStatus(status);
        return ResponseEntity.ok(requests);
    }

    // Update request status
    @PutMapping("/requests/{id}/status")
    public ResponseEntity<RequestPojo> updateRequestStatus(@PathVariable int id, @RequestParam String status) {
        RequestPojo updatedRequest = requestService.updateRequestStatus(id, status);
        return ResponseEntity.ok(updatedRequest);
    }

    // Delete a request
    @DeleteMapping("/requests/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable int id) {
        requestService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }  

    // Get requests sorted by date
    @GetMapping("/requests/sorted")
    public ResponseEntity<List<RequestPojo>> getRequestsSortedByDate() {
        List<RequestPojo> requests = requestService.getRequestsSortedByDate();
        return ResponseEntity.ok(requests);
    }

    // Get requests pending approval
    @GetMapping("requests/pending-approval")
    public ResponseEntity<List<RequestPojo>> getRequestsPendingApproval(@RequestParam String status) {
        List<RequestPojo> pendingRequests = requestService.getRequestsPendingApproval(status);
        return ResponseEntity.ok(pendingRequests);
    }
}
