import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-chercheur-dashboard',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatIconModule],
  template: `
    <div class="page-header"><h1>Tableau de bord</h1></div>
    <div class="card-grid">
      <mat-card><mat-card-header><mat-icon>work</mat-icon><mat-card-title>Offres consultées</mat-card-title></mat-card-header><mat-card-content><p class="stat">0</p></mat-card-content></mat-card>
      <mat-card><mat-card-header><mat-icon>send</mat-icon><mat-card-title>Candidatures envoyées</mat-card-title></mat-card-header><mat-card-content><p class="stat">0</p></mat-card-content></mat-card>
      <mat-card><mat-card-header><mat-icon>event</mat-icon><mat-card-title>Rendez-vous à venir</mat-card-title></mat-card-header><mat-card-content><p class="stat">0</p></mat-card-content></mat-card>
      <mat-card><mat-card-header><mat-icon>school</mat-icon><mat-card-title>Actions de formation</mat-card-title></mat-card-header><mat-card-content><p class="stat">0</p></mat-card-content></mat-card>
    </div>
  `,
  styles: [`.stat{font-size:36px;font-weight:700;color:#1565c0;margin:16px 0}`]
})
export class ChercheurDashboardComponent {}
