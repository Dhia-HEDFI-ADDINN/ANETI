import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
@Component({ selector: 'app-candidatures-list', standalone: true, imports: [CommonModule, MatCardModule], template: `<div class="page-header"><h1>Mes Candidatures</h1></div><mat-card><mat-card-content><p>Liste des candidatures - Module en cours de développement</p></mat-card-content></mat-card>` })
export class CandidaturesListComponent {}
