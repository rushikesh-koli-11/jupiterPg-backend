package com.example.jupiterPg.service;

import com.example.jupiterPg.dto.DashboardStatsDto;
import com.example.jupiterPg.model.Resident;
import com.example.jupiterPg.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final PgRepository pgRepository;
    private final RoomRepository roomRepository;
    private final BedRepository bedRepository;
    private final BookingRepository bookingRepository;
    private final EnquiryRepository enquiryRepository;
    private final ResidentRepository residentRepository;

    public DashboardStatsDto getDashboardStats() {

        long totalPgs = pgRepository.count();
        long totalRooms = roomRepository.count();
        long totalBeds = bedRepository.count();

        long occupiedBeds = bedRepository.countByStatus("OCCUPIED");
        long availableBeds = bedRepository.countByStatus("AVAILABLE");

        long totalBookings = bookingRepository.count();
        long totalEnquiries = enquiryRepository.count();

        Revenue revenue = calculateRevenue();

        return new DashboardStatsDto(
                totalPgs,
                totalRooms,
                totalBeds,
                occupiedBeds,
                availableBeds,
                totalBookings,
                totalEnquiries,
                revenue.monthly,
                revenue.yearly,
                revenue.total
        );
    }

    /* ================= REVENUE LOGIC ================= */

    private Revenue calculateRevenue() {

        List<Resident> residents = residentRepository.findAll();

        double monthly = 0;
        double yearly = 0;
        double total = 0;

        LocalDate now = LocalDate.now();

        for (Resident r : residents) {

            // ✅ SAFE NULL CHECK
            if (r.getRent() == null || r.getCheckInDate() == null) {
                continue;
            }

            total += r.getRent();

            if (r.getCheckInDate().getYear() == now.getYear()) {
                yearly += r.getRent();

                if (r.getCheckInDate().getMonth() == now.getMonth()) {
                    monthly += r.getRent();
                }
            }
        }

        return new Revenue(monthly, yearly, total);
    }


    /* ================= HELPER ================= */

    private static class Revenue {
        double monthly;
        double yearly;
        double total;

        Revenue(double m, double y, double t) {
            this.monthly = m;
            this.yearly = y;
            this.total = t;
        }
    }
}
