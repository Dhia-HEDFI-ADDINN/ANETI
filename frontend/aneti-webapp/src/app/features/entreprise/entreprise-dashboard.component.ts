import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-entreprise-dashboard',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatIconModule, MatToolbarModule, MatButtonModule, RouterLink],
  template: `
    <mat-toolbar color="primary"><span>ANETI - Espace Entreprise</span><span style="flex:1"></span><button mat-button routerLink="/">Accueil</button></mat-toolbar>
    <div class="container" style="padding:24px">
      <div class="page-header"><h1>Espace Entreprise</h1></div>
      <div class="card-grid">
        <mat-card><mat-card-header><mat-icon>edit_note</mat-icon><mat-card-title>Inscription</mat-card-title></mat-card-header><mat-card-content><p>Créer et gérer le profil de votre entreprise</p></mat-card-content></mat-card>
        <mat-card><mat-card-header><mat-icon>work</mat-icon><mat-card-title>Mes Offres</mat-card-title></mat-card-header><mat-card-content><p>Publier et gérer vos offres d'emploi</p></mat-card-content></mat-card>
        <mat-card><mat-card-header><mat-icon>people</mat-icon><mat-card-title>Candidatures</mat-card-title></mat-card-header><mat-card-content><p>Consulter et gérer les candidatures reçues</p></mat-card-content></mat-card>
        <mat-card><mat-card-header><mat-icon>description</mat-icon><mat-card-title>Contrats PAE</mat-card-title></mat-card-header><mat-card-content><p>Suivi des contrats CIVP et autres programmes</p></mat-card-content></mat-card>
      </div>
    </div>
  `
})
export class EntrepriseDashboardComponent {}
