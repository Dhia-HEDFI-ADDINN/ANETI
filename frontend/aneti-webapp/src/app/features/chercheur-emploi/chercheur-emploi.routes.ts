import { Routes } from '@angular/router';

export const CHERCHEUR_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () => import('./chercheur-layout.component').then(m => m.ChercheurLayoutComponent),
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'dashboard', loadComponent: () => import('./dashboard/chercheur-dashboard.component').then(m => m.ChercheurDashboardComponent) },
      { path: 'inscription', loadComponent: () => import('./inscription/inscription-form.component').then(m => m.InscriptionFormComponent) },
      { path: 'profil', loadComponent: () => import('./profil/profil.component').then(m => m.ProfilComponent) },
      { path: 'offres', loadComponent: () => import('./offres/offres-list.component').then(m => m.OffresListComponent) },
      { path: 'candidatures', loadComponent: () => import('./candidatures/candidatures-list.component').then(m => m.CandidaturesListComponent) },
      { path: 'accompagnement', loadComponent: () => import('./accompagnement/accompagnement.component').then(m => m.AccompagnementComponent) },
      { path: 'rdv', loadComponent: () => import('./rdv/rdv-list.component').then(m => m.RdvListComponent) },
    ]
  }
];
