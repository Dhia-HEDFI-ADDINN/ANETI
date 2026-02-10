import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-conseiller-dashboard',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatIconModule, MatToolbarModule, MatButtonModule, RouterLink],
  template: `
    <mat-toolbar color="primary"><span>ANETI - Espace Conseiller</span><span style="flex:1"></span><button mat-button routerLink="/">Accueil</button></mat-toolbar>
    <div class="container" style="padding:24px">
      <div class="page-header"><h1>Tableau de bord Conseiller</h1></div>
      <div class="card-grid">
        <mat-card><mat-card-header><mat-icon>people</mat-icon><mat-card-title>Mon portefeuille</mat-card-title></mat-card-header><mat-card-content><p>0 chercheurs d'emploi</p></mat-card-content></mat-card>
        <mat-card><mat-card-header><mat-icon>event</mat-icon><mat-card-title>Rendez-vous du jour</mat-card-title></mat-card-header><mat-card-content><p>0 RDV planifiés</p></mat-card-content></mat-card>
        <mat-card><mat-card-header><mat-icon>assignment</mat-icon><mat-card-title>Tâches en cours</mat-card-title></mat-card-header><mat-card-content><p>0 tâches</p></mat-card-content></mat-card>
        <mat-card><mat-card-header><mat-icon>work</mat-icon><mat-card-title>Offres à traiter</mat-card-title></mat-card-header><mat-card-content><p>0 offres</p></mat-card-content></mat-card>
        <mat-card><mat-card-header><mat-icon>assessment</mat-icon><mat-card-title>Profilage en attente</mat-card-title></mat-card-header><mat-card-content><p>0 sessions</p></mat-card-content></mat-card>
        <mat-card><mat-card-header><mat-icon>description</mat-icon><mat-card-title>Contrats PAE</mat-card-title></mat-card-header><mat-card-content><p>0 contrats actifs</p></mat-card-content></mat-card>
      </div>
    </div>
  `
})
export class ConseillerDashboardComponent {}
