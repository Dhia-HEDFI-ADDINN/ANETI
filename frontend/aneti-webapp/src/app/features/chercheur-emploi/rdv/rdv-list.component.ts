import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
@Component({ selector: 'app-rdv-list', standalone: true, imports: [CommonModule, MatCardModule], template: `<div class="page-header"><h1>Mes Rendez-vous</h1></div><mat-card><mat-card-content><p>Planning des rendez-vous - Module en cours de développement</p></mat-card-content></mat-card>` })
export class RdvListComponent {}
