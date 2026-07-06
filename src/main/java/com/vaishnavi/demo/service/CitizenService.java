package com.vaishnavi.demo.service;

import java.util.List;
import com.vaishnavi.demo.ai.GeminiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaishnavi.demo.entity.Citizen;
import com.vaishnavi.demo.repository.CitizenRepository;

import jakarta.validation.Valid;

@Service
public class CitizenService {

    @Autowired
    private CitizenRepository citizenRepository;
    
    @Autowired
    private GeminiService geminiService;

    public Citizen saveCitizen(Citizen citizen) {
        citizen.setStatus("Pending");
        return citizenRepository.save(citizen);
    }

    public List<Citizen> getAllCitizens() {
        return citizenRepository.findAll();
    }
    
    public Citizen updateCitizen(int id, Citizen citizen) {
        citizen.setId(id);
        return citizenRepository.save(citizen);
    }
    
    public void deleteCitizen(int id) {
        citizenRepository.deleteById(id);
    }

	
	public Citizen saveCitizenWithAI(Citizen citizen) {

	    citizen.setStatus("Pending");

	    String aiResponse = geminiService.analyzeComplaint(citizen.getComplaint());

	    citizen.setAiResponse(aiResponse);

	    String[] lines = aiResponse.split("\n");

	    for (String line : lines) {

	        if (line.startsWith("Category:")) {
	            citizen.setCategory(line.replace("Category:", "").trim());
	        }

	        if (line.startsWith("Priority:")) {
	            citizen.setPriority(line.replace("Priority:", "").trim());
	        }

	        if (line.startsWith("Department:")) {
	            citizen.setDepartment(line.replace("Department:", "").trim());
	        }
	    }

	    return citizenRepository.save(citizen);
	}
	public long getTotalComplaints() {
	    return citizenRepository.count();
	}

	public long getPendingComplaints() {
	    return citizenRepository.countByStatus("Pending");
	}

	public long getResolvedComplaints() {
	    return citizenRepository.countByStatus("Resolved");
	}

	public long getHighPriorityComplaints() {
	    return citizenRepository.countByPriority("High");
	}
	
	public List<Citizen> getByStatus(String status) {
	    return citizenRepository.findByStatus(status);
	}

	public List<Citizen> getByPriority(String priority) {
	    return citizenRepository.findByPriority(priority);
	}

	public List<Citizen> getByCategory(String category) {
	    return citizenRepository.findByCategory(category);
	}
	
	public Citizen updateStatus(int id, String status) {

	    Citizen citizen = citizenRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Citizen not found"));

	    citizen.setStatus(status);

	    return citizenRepository.save(citizen);
	}
	
	public List<Citizen> getComplaintsByEmail(String email){

	    return citizenRepository.findByEmail(email);

	}
}