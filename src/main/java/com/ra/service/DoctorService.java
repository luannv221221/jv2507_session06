package com.ra.service;

import com.ra.model.entity.Doctor;

import javax.print.Doc;
import java.util.List;

public interface DoctorService {
    List<Doctor> getAllDoctors();
    Doctor createDoctor(Doctor doctor);
    Doctor updateDoctor(Doctor doctor, Long id);
    void deleteDoctorById(Long id);
}
