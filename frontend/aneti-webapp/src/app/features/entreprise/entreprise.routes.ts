import { Routes } from '@angular/router';
export const ENTREPRISE_ROUTES: Routes = [
  { path: '', loadComponent: () => import('./entreprise-dashboard.component').then(m => m.EntrepriseDashboardComponent) }
];
