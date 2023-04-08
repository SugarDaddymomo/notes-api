package com.ashutosh.notes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FolderDTO {

    private String name;
    private List<String> text;
    private byte[] image;
    private String parent;

    public FolderDTO(String name, List<String> text, byte[] image) {
        this.name = name;
        this.text = text;
        this.image = image;
    }
}
