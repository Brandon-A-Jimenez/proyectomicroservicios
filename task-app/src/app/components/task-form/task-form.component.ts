import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { TaskService } from 'src/app/services/task.service';
import { Task } from 'src/app/models/task.model';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-task-form',
  templateUrl: './task-form.component.html',
  styleUrls: ['./task-form.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class TaskFormComponent implements OnInit {

  task: Task = {
    title: '',
    description: '',
    dueDate: '',
    createdBy: ''
  };

  constructor(private taskService: TaskService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    if (id) {
      this.taskService.getTaskById(id).subscribe(data => {
        this.task = data;
      });
    }
  }

  saveTask() {
    this.task.dueDate = new Date(this.task.dueDate).toISOString();
    if (this.task.id) {
      this.taskService.updateTask(this.task.id, this.task).subscribe( Response => {
        this.router.navigate(['/tasks']);
      });
    } else {
      this.taskService.createTask(this.task).subscribe(Response => {
        this.router.navigate(['/tasks']);
      });
    }
  }
}
