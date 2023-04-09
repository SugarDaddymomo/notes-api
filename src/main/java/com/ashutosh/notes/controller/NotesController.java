package com.ashutosh.notes.controller;

import com.ashutosh.notes.requests.CreateNoteRequest;
import com.ashutosh.notes.responses.CreateNoteResponse;
import com.ashutosh.notes.service.FolderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/notes-api/note")
@RequiredArgsConstructor
public class NotesController {

    private final FolderService folderService;

    @PostMapping("/create-note")
    public ResponseEntity<CreateNoteResponse> createNote(@RequestBody @Valid CreateNoteRequest request) {
        return ResponseEntity.ok(folderService.createNotes(request));
    }

    @PostMapping(value = "/add-image", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<CreateNoteResponse> addImageToFolder(@RequestParam("image") MultipartFile request, @RequestParam("folder-name") String folderName) throws IOException {
        return ResponseEntity.ok(folderService.addImageToFolder(request, folderName));
    }
}