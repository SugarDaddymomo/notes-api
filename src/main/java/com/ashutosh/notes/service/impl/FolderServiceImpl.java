package com.ashutosh.notes.service.impl;

import com.ashutosh.notes.dto.FolderDTO;
import com.ashutosh.notes.model.Folder;
import com.ashutosh.notes.repository.FolderRepository;
import com.ashutosh.notes.requests.CreateFolderRequest;
import com.ashutosh.notes.requests.CreateNoteRequest;
import com.ashutosh.notes.responses.CreateFolderResponse;
import com.ashutosh.notes.responses.CreateNoteResponse;
import com.ashutosh.notes.responses.GetFolderResponse;
import com.ashutosh.notes.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService {

    private final FolderRepository folderRepository;

    @Override
    public CreateFolderResponse createFolder(CreateFolderRequest request) {
        if (Objects.nonNull(request) && Objects.nonNull(request.getName())) {
            var folder = folderRepository.findByName(request.getName());
            if (folder.isPresent()) {
                var response = CreateFolderResponse.builder()
                        .folderName(folder.get().getName())
                        .isPrivate(folder.get().isPrivate())
                        .build();
                return response;
            }
            var newFolder = Folder.builder()
                    .name(request.getName())
                    .isPrivate(request.isPrivate())
                    .build();
            folderRepository.save(newFolder);
            var response = CreateFolderResponse.builder()
                    .folderName(newFolder.getName())
                    .isPrivate(newFolder.isPrivate())
                    .build();
            return response;
        }
        return new CreateFolderResponse("Please provide proper request", false);
    }

    @Override
    public CreateNoteResponse createNotes(CreateNoteRequest request) {
        CreateNoteResponse response = new CreateNoteResponse();
        if (
                Objects.nonNull(request)
                && Objects.nonNull(request.getNotes())
                && !request.getNotes().isEmpty()
                && !StringUtils.isEmpty(request.getFolderName())
        ) {
            var name = folderRepository.findByName(request.getFolderName());
            if (name.isPresent()) {
                name.get().setText(request.getNotes());
                folderRepository.save(name.get());
                response.setMessage(String.format("Notes saved in %s folder", request.getFolderName()));
                return response;
            } else {
                response.setMessage(String.format("No folder exists with %s name", request.getFolderName()));
                return response;
            }
        }
        response.setMessage("Please provide request in correct format!");
        return response;
    }

    @Override
    public GetFolderResponse getFolders() {
        List<Folder> all = folderRepository.findAll();
        List<FolderDTO> collect = all.stream().
                map(
                        folder -> new FolderDTO(
                                folder.getName(),
                                folder.getText(),
                                folder.getImage())
                         ).collect(Collectors.toList()
                );
        GetFolderResponse response = new GetFolderResponse();
        if (!collect.isEmpty()) {
            response.setList(collect);
        } else {
            response.setList(Collections.emptyList());
        }
        return response;
    }
}
