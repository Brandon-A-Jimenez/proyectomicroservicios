import { Component, OnInit } from '@angular/core';
import { TaskService } from 'src/app/services/task.service';
import { Task } from 'src/app/models/task.model';
import { TaskHistory } from 'src/app/models/task-history.model';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {

  tasks: Task[] = [];
  selectedTaskHistory: TaskHistory[] = [];

  constructor(private taskService: TaskService) { }

  ngOnInit(): void {
    this.taskService.getTasks().subscribe(data => {
      this.tasks = data;
    });
  }

  deleteTask(id?: number) {
    if (id !== undefined) {
      this.taskService.deleteTask(id).subscribe(() => {
        this.tasks = this.tasks.filter(task => task.id !== id);
      });
    } else {
      console.error('ID is undefined');
    }
  }
  showTaskHistory(taskId: number) {
    this.taskService.getTaskHistory(taskId).subscribe(history => {
      console.log('Received Task History:', history);
      this.selectedTaskHistory = history;
    });
  }
}
