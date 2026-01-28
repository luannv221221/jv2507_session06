package com.ra.repository;

import com.ra.model.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    Page<Doctor> findByFullNameContainingIgnoreCase(String keyword, Pageable pageable);

    // lay ra top 10 bac si co so nam kinh nghiem lon hon 5
    @Query("SELECT d FROM Doctor d WHERE d.experienceYears > 5 ORDER BY d.experienceYears DESC LIMIT 10")
    List<Doctor> findTop10DoctorsByExperienceYears();

    // lay ra top 5 bac si so so nam kinh nhiem nho hon5
    @NativeQuery("select * from doctor where experience_years < 5  Order By experience_years ASC limit 5")
    List<Doctor> findTop5DoctorsByExperienceYears();
}
