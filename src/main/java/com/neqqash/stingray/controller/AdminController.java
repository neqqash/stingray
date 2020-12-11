package com.neqqash.stingray.controller;

import com.neqqash.stingray.dto.AdminSongDTO;
import com.neqqash.stingray.dto.DetailedSongDTO;
import com.neqqash.stingray.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController implements com.neqqash.stingray.api.AdminApi {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @Override
    public ResponseEntity<String> addSong(DetailedSongDTO detailedSong) {
       return adminService.addSong(detailedSong);
    }

    @Override
    public ResponseEntity<List<AdminSongDTO>> getSongs() {
        return adminService.getSongs();
    }

    @Override
    public ResponseEntity<String> modifySong(DetailedSongDTO detailedSong) {
        return adminService.modifySong(detailedSong);
    }

    @Override
    public ResponseEntity<String> removeSong(DetailedSongDTO detailedSong) {
        return adminService.removeSong(detailedSong);
    }
}
