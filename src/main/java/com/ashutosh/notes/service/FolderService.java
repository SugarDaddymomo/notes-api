package com.ashutosh.notes.service;

import com.ashutosh.notes.dto.FolderDTO;
import com.ashutosh.notes.requests.CreateFolderRequest;
import com.ashutosh.notes.responses.CreateFolderResponse;
import com.ashutosh.notes.requests.CreateNoteRequest;
import com.ashutosh.notes.responses.CreateNoteResponse;
import com.ashutosh.notes.responses.GetFolderResponse;

import java.util.List;

public interface FolderService {
    CreateFolderResponse createFolder(CreateFolderRequest request);

    CreateNoteResponse createNotes(CreateNoteRequest request);

    GetFolderResponse getFolders();
}
