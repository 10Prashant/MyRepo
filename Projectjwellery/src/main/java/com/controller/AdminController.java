package com.controller;

import java.util.List;
import java.util.Optional;

import com.advices.CustomerException;
import com.dto.AuthenticationDTO;
import com.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.advices.AdminException;
import com.entity.Admin;
import com.serviceImpl.AdminServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/getalladmins")
    public List<Admin> getAllAdmin() throws Throwable {
        return adminService.getAllAdmin();
    }

    @GetMapping("/getadminbyid/{AdminId}")
    public Optional<Admin> getAdminById(@PathVariable int AdminId) throws Throwable {
        return adminService.getAdminById(AdminId);
    }

    @GetMapping("/getadminbyname/{AdminName}")
    public Optional<Admin> getAdminByName(@PathVariable String AdminName) throws AdminException {
        return adminService.getAdminByName(AdminName);
    }

    /*
     * @GetMapping("/byLocation/{location}") public List<Admin>
     * getAdminByLocation(@PathVariable String location) throws AdminException {
     * return adminService.getAdminByLocation(location); }
     */

    @PostMapping("/addAdmin")
    public ResponseEntity<?> createAdmin(@Valid @RequestBody Admin admin) {
        try {
            Admin admin1 = adminService.createAdmin(admin);
            return new ResponseEntity<>(admin1, HttpStatus.CREATED);
        } catch (CustomerException e) {
            return new ResponseEntity<>("Email exists", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Email exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateadminbyid/{AdminId}")
    public Admin updateAdmin(@PathVariable int AdminId, @Valid @RequestBody Admin updatedAdmin) throws AdminException {
        return adminService.updateAdmin(AdminId, updatedAdmin);
    }

    @DeleteMapping("/deleteadminbyid/{AdminId}")
    public void deleteAdmin(@PathVariable int AdminId) throws Throwable {
        adminService.deleteAdmin(AdminId);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@RequestBody AuthenticationDTO authenticationDTO) {
        Admin admin=adminService.isAuthenticated(authenticationDTO);
        if(admin==null) {
            return new ResponseEntity<>("Invalid email-id or password",HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(admin,HttpStatus.OK);
    }
}
