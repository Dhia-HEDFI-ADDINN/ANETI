import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-profil',
  standalone: true,
  imports: [CommonModule, MatCardModule],
  template: `<div class="page-header"><h1>Mon Profil</h1></div><mat-card><mat-card-content><p>Profil du chercheur d'emploi - Module en cours de développement</p></mat-card-content></mat-card>`
})
export class ProfilComponent {}
