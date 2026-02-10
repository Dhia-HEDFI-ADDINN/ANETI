import { Routes } from '@angular/router';
export const CONSEILLER_ROUTES: Routes = [
  { path: '', loadComponent: () => import('./conseiller-dashboard.component').then(m => m.ConseillerDashboardComponent) }
];
