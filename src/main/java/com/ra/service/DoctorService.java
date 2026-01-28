package com.ra.service;

import com.ra.model.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.print.Doc;
import java.util.List;

public interface DoctorService {
    List<Doctor> getAllDoctors();
    Doctor createDoctor(Doctor doctor);
    Doctor updateDoctor(Doctor doctor, Long id);
    void deleteDoctorById(Long id);
    Page<Doctor> findAllDoctorsPagination(Pageable pageable);
    Page<Doctor> searchDoctorsPagination(String keyword,Pageable pageable);
    // lay danh sach doctor theo nam kinh nghiem
    List<Doctor> getDoctorsByEx();
    // lay danh sach doctor non kinh nghiem
    List<Doctor> getDoctorsByEx1();
}
