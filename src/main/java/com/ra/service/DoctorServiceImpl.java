package com.ra.service;

import com.ra.model.entity.Doctor;
import com.ra.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(Doctor doctor, Long id) {
        // tim xem co Doctor theo id khong ko co tra loi
        doctor.setId(id);
        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctorById(Long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public Page<Doctor> findAllDoctorsPagination(Pageable pageable) {
        Page<Doctor> doctors = doctorRepository.findAll(pageable);
        return doctors;
    }

    @Override
    public Page<Doctor> searchDoctorsPagination(String keyword, Pageable pageable) {
        return doctorRepository.findByFullNameContainingIgnoreCase(keyword,pageable);
    }

    @Override
    public List<Doctor> getDoctorsByEx() {
        return doctorRepository.findTop10DoctorsByExperienceYears();
    }

    @Override
    public List<Doctor> getDoctorsByEx1() {
        return doctorRepository.findTop5DoctorsByExperienceYears();
    }
}
