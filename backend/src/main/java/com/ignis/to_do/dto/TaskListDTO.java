package com.ignis.to_do.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskListDTO {
    private Long id;
    private String name;
    private Long boardId;
}
