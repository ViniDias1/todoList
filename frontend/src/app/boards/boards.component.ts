import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { BoardService } from '../services/board.service';
import { AuthInterceptor } from '../interceptors/auth.interceptor';

interface Board {
  id: string;
  ownerId: string;
  title: string;
}

@Component({
  selector: 'app-boards',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  providers: [
    {
      provide: 'HTTP_INTERCEPTORS',
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  templateUrl: './boards.component.html',
  styleUrls: ['./boards.component.css']
})
export class BoardsComponent implements OnInit {
  boards: Array<Board> = [];

  constructor(private readonly boardService: BoardService) {}

  ngOnInit(): void {
    this.loadBoards();
  }

  loadBoards(): void {
    this.boardService.getAllBoards().subscribe({
      next: (data: Array<Board>) => {
        this.boards = data;
        console.log('Boards loaded:', this.boards);
      },
      error: (err) => {
        console.error('Failed to load boards:', err);
      }
    });

  }

  addBoard() {
    const newBoard: Board = {
      id: Date.now().toString(),
      ownerId: '1', // Exemplo de valor fixo
      title: `New Board ${this.boards.length + 1}`
    };
    this.boards.push(newBoard);
  }

  deleteBoard(id: string) {
    this.boards = this.boards.filter(board => board.id !== id);
  }
}
