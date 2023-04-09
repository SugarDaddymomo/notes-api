package com.ashutosh.notes.service;

import com.ashutosh.notes.dto.FolderDTO;
import com.ashutosh.notes.requests.CreateFolderRequest;
import com.ashutosh.notes.responses.CreateFolderResponse;
import com.ashutosh.notes.requests.CreateNoteRequest;
import com.ashutosh.notes.responses.CreateNoteResponse;
import com.ashutosh.notes.responses.GetFolderResponse;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface FolderService {
    CreateFolderResponse createFolder(CreateFolderRequest request);
    CreateNoteResponse createNotes(CreateNoteRequest request);
    GetFolderResponse getFolders();
    CreateNoteResponse addImageToFolder(MultipartFile request, String folderName) throws IOException;
    FolderDTO getFolderByName(String folderName);
}
