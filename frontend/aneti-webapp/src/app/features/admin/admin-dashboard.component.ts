import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatIconModule, MatToolbarModule, MatButtonModule, RouterLink],
  template: `
    <mat-toolbar color="primary"><span>ANETI - Administration</span><span style="flex:1"></span><button mat-button routerLink="/">Accueil</button></mat-toolbar>
    <div class="container" style="padding:24px">
      <div class="page-header"><h1>Administration du système</h1></div>
      <div class="card-grid">
        <mat-card><mat-card-header><mat-icon>people</mat-icon><mat-card-title>Utilisateurs</mat-card-title></mat-card-header><mat-card-content><p>Gestion des comptes et des rôles</p></mat-card-content></mat-card>
        <mat-card><mat-card-header><mat-icon>location_city</mat-icon><mat-card-title>BETIs</mat-card-title></mat-card-header><mat-card-content><p>Gestion des bureaux d'emploi</p></mat-card-content></mat-card>
        <mat-card><mat-card-header><mat-icon>list_alt</mat-icon><mat-card-title>Référentiels</mat-card-title></mat-card-header><mat-card-content><p>Nomenclatures et paramétrage</p></mat-card-content></mat-card>
        <mat-card><mat-card-header><mat-icon>settings</mat-icon><mat-card-title>Paramètres</mat-card-title></mat-card-header><mat-card-content><p>Configuration générale du système</p></mat-card-content></mat-card>
        <mat-card><mat-card-header><mat-icon>bar_chart</mat-icon><mat-card-title>Reporting</mat-card-title></mat-card-header><mat-card-content><p>Tableaux de bord et indicateurs</p></mat-card-content></mat-card>
        <mat-card><mat-card-header><mat-icon>history</mat-icon><mat-card-title>Journal d'audit</mat-card-title></mat-card-header><mat-card-content><p>Traçabilité des actions</p></mat-card-content></mat-card>
      </div>
    </div>
  `
})
export class AdminDashboardComponent {}
