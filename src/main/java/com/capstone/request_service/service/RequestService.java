package com.capstone.request_service.service;

import com.capstone.request_service.entity.RequestEntity;
import com.capstone.request_service.exception.ResourceNotFoundException;
import com.capstone.request_service.pojo.CommunityPojo;
import com.capstone.request_service.pojo.RequestPojo;
import com.capstone.request_service.pojo.UserOutputDataPojo;
import com.capstone.request_service.repository.RequestRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestService {
    @Autowired
    RequestRepository requestRepository;

    private RequestPojo convertEntityToPojo(RequestEntity requestEntity) {
        RequestPojo requestPojo = new RequestPojo();
        BeanUtils.copyProperties(requestEntity, requestPojo);
        RestClient restClient = RestClient.create();
         UserOutputDataPojo responseUser = restClient.get()
                    .uri("http://localhost:5001/api/users/email/" + requestEntity.getEmail())
                    .retrieve().body(UserOutputDataPojo.class);
            requestPojo.setUser(responseUser);
            CommunityPojo responseCommunity = restClient.get()
                    .uri("http://localhost:5002/api/communities/" + requestEntity.getCommunityId())
                    .retrieve().body(CommunityPojo.class);
            requestPojo.setUser(responseUser);

        return requestPojo;
    }

    public RequestPojo addRequest(RequestPojo requestPojo) {
        RequestEntity requestEntity = new RequestEntity();
        BeanUtils.copyProperties(requestPojo, requestEntity);
        RequestEntity savedEntity = requestRepository.save(requestEntity);
        return convertEntityToPojo(savedEntity);
    }

    // Get All Requests
    public List<RequestPojo> getAllRequests() {
        List<RequestEntity> requestEntities = requestRepository.findAll();
        List<RequestPojo> requestPojos = new ArrayList<>();
        for (RequestEntity requestEntity : requestEntities) {
            requestPojos.add(convertEntityToPojo(requestEntity));
        }
        return requestPojos;
    }

    // Get Request by ID
    public RequestPojo getRequestById(int requestId) {
        RequestEntity requestEntity = requestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found with id " + requestId));
        return convertEntityToPojo(requestEntity);
    }

    // Get Requests by Username
    public List<RequestPojo> getRequestsByEmail(String email) {
        List<RequestEntity> requestEntities = requestRepository.findByEmail(email);
        List<RequestPojo> requestPojos = new ArrayList<>();
        for (RequestEntity requestEntity : requestEntities) {
            requestPojos.add(convertEntityToPojo(requestEntity));
        }
        return requestPojos;
    }

    // Get Requests by Community ID
    public List<RequestPojo> getRequestsByCommunityId(int communityId) {
        List<RequestEntity> requestEntities = requestRepository.findByCommunityId(communityId);
        List<RequestPojo> requestPojos = new ArrayList<>();
        for (RequestEntity requestEntity : requestEntities) {
            requestPojos.add(convertEntityToPojo(requestEntity));
        }
        return requestPojos;
    }

    // Get Requests by Status
    public List<RequestPojo> getRequestsByStatus(String status) {
        List<RequestEntity> requestEntities = requestRepository.findByStatus(status);
        List<RequestPojo> requestPojos = new ArrayList<>();
        for (RequestEntity requestEntity : requestEntities) {
            requestPojos.add(convertEntityToPojo(requestEntity));
        }
        return requestPojos;
    }

    // Update Request Status
    public RequestPojo updateRequestStatus(int requestId, String status) {
        RequestEntity requestEntity = requestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found with id " + requestId));
        requestEntity.setStatus(status);
        RequestEntity updatedEntity = requestRepository.save(requestEntity);
        return convertEntityToPojo(updatedEntity);
    }

    // Delete Request
    public void deleteRequest(int requestId) {
        if (!requestRepository.existsById(requestId)) {
            throw new ResourceNotFoundException("Request not found with id " + requestId);
        }
        requestRepository.deleteById(requestId);
    }

    // Get Requests Sorted by Date
    public List<RequestPojo> getRequestsSortedByDate() {
        List<RequestEntity> requestEntities = requestRepository.findAll(Sort.by(Sort.Order.asc("requestDate")));
        List<RequestPojo> requestPojos = new ArrayList<>();
        for (RequestEntity requestEntity : requestEntities) {
            requestPojos.add(convertEntityToPojo(requestEntity));
        }
        return requestPojos;
    }

    // Get Requests Pending Approval
    public List<RequestPojo> getRequestsPendingApproval(String status) {
        List<RequestEntity> requestEntities = requestRepository.findByStatus(status);
        List<RequestPojo> requestPojos = new ArrayList<>();
        for (RequestEntity requestEntity : requestEntities) {
            requestPojos.add(convertEntityToPojo(requestEntity));
        }
        return requestPojos;
    }

}
