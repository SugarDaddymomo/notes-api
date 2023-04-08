package com.ashutosh.notes.responses;

import com.ashutosh.notes.dto.FolderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetFolderResponse {
    private List<FolderDTO> list;
}
