package com.vaishnavi.demo.controller;

import java.util.List;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.vaishnavi.demo.entity.Citizen;
import com.vaishnavi.demo.service.CitizenService;
import com.vaishnavi.demo.ai.GeminiService;

@RestController
@RequestMapping("/citizens")
@CrossOrigin(origins = "http://localhost:3000")
public class CitizenController {

    @Autowired
    private CitizenService citizenService;
    @Autowired
    private GeminiService geminiService;

    @PostMapping
    public Citizen addCitizen(@Valid @RequestBody Citizen citizen) {
        return citizenService.saveCitizenWithAI(citizen);
    }

    @GetMapping
    public List<Citizen> getAllCitizens() {
        return citizenService.getAllCitizens();
    }
    
    @PutMapping("/{id}")
    public Citizen updateCitizen(@PathVariable int id,
                                 @Valid @RequestBody Citizen citizen) {
        return citizenService.updateCitizen(id, citizen);
    }
    
    @DeleteMapping("/{id}")
    public String deleteCitizen(@PathVariable int id) {
        citizenService.deleteCitizen(id);
        return "Citizen deleted successfully";
    }
    
    @PostMapping("/analyze")
    public String analyzeComplaint(@RequestBody Citizen citizen) {
        return geminiService.analyzeComplaint(citizen.getComplaint());
    }
    
    @GetMapping("/status/{status}")
    public List<Citizen> getByStatus(@PathVariable String status) {
        return citizenService.getByStatus(status);
    }

    @GetMapping("/priority/{priority}")
    public List<Citizen> getByPriority(@PathVariable String priority) {
        return citizenService.getByPriority(priority);
    }

    @GetMapping("/category/{category}")
    public List<Citizen> getByCategory(@PathVariable String category) {
        return citizenService.getByCategory(category);
    }
    
    @PutMapping("/{id}/status")
    public Citizen updateStatus(@PathVariable int id,
                                @RequestParam String status) {

        return citizenService.updateStatus(id, status);
    }
    
    @GetMapping("/user/{email}")
    public List<Citizen> getUserComplaints(@PathVariable String email){

        return citizenService.getComplaintsByEmail(email);

    }
}