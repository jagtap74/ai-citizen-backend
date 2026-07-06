package com.vaishnavi.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import com.vaishnavi.demo.service.CitizenService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private CitizenService citizenService;

    @GetMapping("/status")
    public Map<String, Long> getStats() {

        Map<String, Long> stats = new HashMap<>();

        stats.put("totalComplaints", citizenService.getTotalComplaints());
        stats.put("pending", citizenService.getPendingComplaints());
        stats.put("resolved", citizenService.getResolvedComplaints());
        stats.put("highPriority", citizenService.getHighPriorityComplaints());

        return stats;
    }
}