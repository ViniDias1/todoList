
package com.ignis.to_do.model;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "board")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    @OneToMany(mappedBy = "board")
    private List<TaskList> taskLists;

    public Board(String title, User owner) {
        this.title = title;
        this.owner = owner;
    }   

    public void addTaskList(TaskList taskList) {

        taskList.setBoard(this);
        this.taskLists.add(taskList);
    }
    
    public void removeTaskList(TaskList taskList) {

        taskList.setBoard(null);
        this.taskLists.remove(taskList);
    }
}