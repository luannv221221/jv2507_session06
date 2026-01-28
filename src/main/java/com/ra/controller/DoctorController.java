package com.ra.controller;

import com.ra.model.entity.Doctor;
import com.ra.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    @Autowired
    private DoctorService  doctorService;
    @GetMapping("")
    public ResponseEntity<?> getAllDoctors(){
        List<Doctor> doctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<?> getDoctorsByName(
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "limit",defaultValue = "10") int limit,
            @RequestParam(name = "keyword",defaultValue = "") String keyword
    ){
        Pageable pageable = PageRequest.of(page, limit);
        Page<Doctor> doctors = doctorService.searchDoctorsPagination(keyword,pageable);
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
    @GetMapping("/paginate")
    public ResponseEntity<?> getDoctorsPagination(
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "limit",defaultValue = "3") int limit,
            @RequestParam(name = "sortBy",defaultValue = "id") String sortBy,
            @RequestParam(name = "orderBy",defaultValue = "asc") String orderBy
    ){
        Sort sort = orderBy.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page,limit,sort);
        Page<Doctor> doctors = doctorService.findAllDoctorsPagination(pageable);
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
    @GetMapping("top-doctors")
    public ResponseEntity<?> getTopDoctors(){
        List<Doctor> doctors = doctorService.getDoctorsByEx();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
    @GetMapping("top-doctors-5")
    public ResponseEntity<?> getTopDoctors5(){
        List<Doctor> doctors = doctorService.getDoctorsByEx1();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<?> createDoctor(@RequestBody Doctor doctor) {
        Doctor createdDoctor = doctorService.createDoctor(doctor);
        if (createdDoctor != null) {
            return new ResponseEntity<>(createdDoctor, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        Doctor doctorUpdate = doctorService.updateDoctor(doctor, id);
        if (doctorUpdate != null) {
            return new ResponseEntity<>(doctorUpdate, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctorById(@PathVariable("id") Long id) {
        doctorService.deleteDoctorById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
