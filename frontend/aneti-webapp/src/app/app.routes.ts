import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'accueil',
    pathMatch: 'full'
  },
  {
    path: 'accueil',
    loadComponent: () => import('@features/accueil/accueil.component').then(m => m.AccueilComponent)
  },
  {
    path: 'chercheur',
    loadChildren: () => import('@features/chercheur-emploi/chercheur-emploi.routes').then(m => m.CHERCHEUR_ROUTES)
  },
  {
    path: 'entreprise',
    loadChildren: () => import('@features/entreprise/entreprise.routes').then(m => m.ENTREPRISE_ROUTES)
  },
  {
    path: 'conseiller',
    loadChildren: () => import('@features/conseiller/conseiller.routes').then(m => m.CONSEILLER_ROUTES)
  },
  {
    path: 'admin',
    loadChildren: () => import('@features/admin/admin.routes').then(m => m.ADMIN_ROUTES)
  },
  {
    path: '**',
    redirectTo: 'accueil'
  }
];
