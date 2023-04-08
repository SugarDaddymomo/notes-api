package com.ashutosh.notes.controller;

import com.ashutosh.notes.requests.CreateFolderRequest;
import com.ashutosh.notes.responses.CreateFolderResponse;
import com.ashutosh.notes.responses.GetFolderResponse;
import com.ashutosh.notes.service.FolderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notes-api/folder")
@RequiredArgsConstructor
public class FolderController {

    private final FolderService folderService;

    @PostMapping("/create")
    public ResponseEntity<CreateFolderResponse> createFolder(
            @RequestBody @Valid CreateFolderRequest request
            ) {
        return ResponseEntity.ok(folderService.createFolder(request));
    }

    @GetMapping("/folders")
    public ResponseEntity<GetFolderResponse> getFolders() {
        return ResponseEntity.ok(folderService.getFolders());
    }
}