package com.ignis.to_do.service;

public interface TaskReminder {
    
    public String checkOverdueTasks(Long taskId);
    public void sendTaskReminder();


}
