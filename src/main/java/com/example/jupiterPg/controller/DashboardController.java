package com.example.jupiterPg.controller;

import com.example.jupiterPg.dto.DashboardStatsDto;
import com.example.jupiterPg.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    // 🔐 ADMIN DASHBOARD STATS
    @GetMapping("/stats")
    public DashboardStatsDto getStats() {
        return dashboardService.getDashboardStats();
    }
}
