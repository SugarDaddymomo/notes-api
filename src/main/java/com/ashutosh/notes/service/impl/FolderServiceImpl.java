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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService {

    private final FolderRepository folderRepository;

    @Transactional
    @Override
    public FolderDTO getFolderByName(String folderName) {
        FolderDTO response = new FolderDTO();
        if (Objects.nonNull(folderName) && !StringUtils.isEmpty(folderName)) {
            var folder = folderRepository.findByName(folderName);
            if (folder.isPresent()) {
                response.setName(folder.get().getName());
                response.setText(folder.get().getText());
                response.setImage(folder.get().getImage());
                response.setParent(Objects.nonNull(folder.get().getParent()) ? folder.get().getParent().getName() : null);
                return response;
            } else {
                response.setName(String.format("No folder exists with %s name!", folderName));
                return response;
            }
        } else {
            response.setName("Please provide the name of the folder!");
        }
        return response;
    }

    @Override
    public CreateFolderResponse createFolder(CreateFolderRequest request) {
        CreateFolderResponse response = new CreateFolderResponse();
        if (Objects.nonNull(request) && Objects.nonNull(request.getName()) && Objects.nonNull(request.getParent())) {
            if (!StringUtils.isEmpty(request.getParent())) {
                var parent = folderRepository.findByName(request.getParent());
                if (parent.isPresent()) {
                    var folder = Folder.builder()
                            .name(request.getName())
                            .isPrivate(request.isPrivate())
                            .parent(parent.get())
                            .build();
                    folderRepository.save(folder);
                    response.setMessage(String.format("Sub-folder created under %s parent folder with name %s and it's %b.", request.getParent(), request.getName(), request.isPrivate()));
                    return response;
                } else {
                    response.setMessage(String.format("No parent folder found with %s name!", request.getParent()));
                }
            } else {
                var dbFolder = folderRepository.findByName(request.getName());
                if (dbFolder.isPresent()) {
                    response.setMessage(String.format("Folder already exists with %s name and it's %b.", request.getName(), request.isPrivate()));
                    return response;
                } else {
                    var folder = Folder.builder()
                            .name(request.getName())
                            .isPrivate(request.isPrivate())
                            .build();
                    folderRepository.save(folder);
                    response.setMessage(String.format("New Folder created with %s name and it's %b.", request.getName(), request.isPrivate()));
                    return response;
                }
            }
        } else {
            response.setMessage("Please provide proper request");
        }
        return response;
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
        } else {
            response.setMessage("Please provide request in correct format!");
        }
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
                                folder.getImage(),
                                Objects.nonNull(folder.getParent()) ? folder.getParent().getName() : null)
                ).collect(Collectors.toList());
        GetFolderResponse response = new GetFolderResponse();
        if (!collect.isEmpty()) {
            response.setList(collect);
        } else {
            response.setList(Collections.emptyList());
        }
        return response;
    }

    @Override
    public CreateNoteResponse addImageToFolder(MultipartFile request, String folderName) throws IOException {
        CreateNoteResponse response = new CreateNoteResponse();
        if (!request.isEmpty() && !StringUtils.isEmpty(folderName)) {
            var folder = folderRepository.findByName(folderName);
            if (folder.isPresent()) {
                folder.get().setImage(request.getBytes());
                folderRepository.save(folder.get());
                response.setMessage(String.format("Image saved to folder %s", folderName));
            } else {
                response.setMessage(String.format("No Folder exists by %s name!", folderName));
            }
        } else {
            response.setMessage("Please provide request in correct format!");
        }
        return response;
    }
}
