package com.ashutosh.notes.controller;

import com.ashutosh.notes.requests.AddImageRequest;
import com.ashutosh.notes.requests.CreateNoteRequest;
import com.ashutosh.notes.responses.CreateNoteResponse;
import com.ashutosh.notes.service.FolderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notes-api/note")
@RequiredArgsConstructor
public class NotesController {

    private final FolderService folderService;

    @PostMapping("/create-note")
    public ResponseEntity<CreateNoteResponse> createNote(
            @RequestBody @Valid CreateNoteRequest request
            ) {
        return ResponseEntity.ok(folderService.createNotes(request));
    }

    @PostMapping("/add-image")
    public ResponseEntity<CreateNoteResponse> addImageToFolder(
            @RequestBody @Valid AddImageRequest request
            ) {
        return null;
    }
}