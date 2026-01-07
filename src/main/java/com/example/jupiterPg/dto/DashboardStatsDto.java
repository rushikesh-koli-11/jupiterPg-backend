package com.example.jupiterPg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardStatsDto {

    private long totalPgs;
    private long totalRooms;
    private long totalBeds;

    private long occupiedBeds;
    private long availableBeds;

    private long totalBookings;
    private long totalEnquiries;

    private double monthlyRevenue;
    private double yearlyRevenue;
    private double totalRevenue;
}
