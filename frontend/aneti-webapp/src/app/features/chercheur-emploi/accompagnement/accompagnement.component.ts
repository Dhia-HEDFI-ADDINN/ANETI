import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
@Component({ selector: 'app-accompagnement', standalone: true, imports: [CommonModule, MatCardModule], template: `<div class="page-header"><h1>Mon Accompagnement</h1></div><mat-card><mat-card-content><p>Plan d'accompagnement personnalisé - Module en cours de développement</p></mat-card-content></mat-card>` })
export class AccompagnementComponent {}
