import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatChipsModule } from '@angular/material/chips';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginatorModule } from '@angular/material/paginator';
import { FormsModule } from '@angular/forms';
import { ApiService } from '@core/services/api.service';

@Component({
  selector: 'app-offres-list',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatChipsModule, MatButtonModule, MatFormFieldModule, MatInputModule, MatSelectModule, MatIconModule, MatPaginatorModule, FormsModule],
  template: `
    <div class="page-header"><h1>Offres d'emploi</h1></div>
    <mat-card class="search-card">
      <mat-card-content>
        <div class="search-row">
          <mat-form-field><mat-label>Rechercher</mat-label><input matInput [(ngModel)]="searchTitre" placeholder="Titre, métier..."><mat-icon matSuffix>search</mat-icon></mat-form-field>
          <mat-form-field><mat-label>Gouvernorat</mat-label><mat-select [(ngModel)]="searchGouvernorat"><mat-option value="">Tous</mat-option><mat-option *ngFor="let g of gouvernorats" [value]="g">{{ g }}</mat-option></mat-select></mat-form-field>
          <button mat-raised-button color="primary" (click)="rechercher()">Rechercher</button>
        </div>
      </mat-card-content>
    </mat-card>
    <div class="offres-list">
      <mat-card *ngFor="let offre of offres" class="offre-card">
        <mat-card-header>
          <mat-card-title>{{ offre.titre }}</mat-card-title>
          <mat-card-subtitle>{{ offre.secteurActivite }} - {{ offre.gouvernorat }}</mat-card-subtitle>
        </mat-card-header>
        <mat-card-content>
          <p>{{ offre.description | slice:0:200 }}...</p>
          <mat-chip-set><mat-chip>{{ offre.typeContrat }}</mat-chip><mat-chip>{{ offre.niveauEtudeRequis }}</mat-chip></mat-chip-set>
        </mat-card-content>
        <mat-card-actions><button mat-raised-button color="accent">Postuler</button><button mat-button>Détails</button></mat-card-actions>
      </mat-card>
    </div>
  `,
  styles: [`.search-row{display:flex;gap:16px;align-items:center} .offres-list{display:flex;flex-direction:column;gap:16px;margin-top:16px} .offre-card{cursor:pointer}`]
})
export class OffresListComponent implements OnInit {
  private api = inject(ApiService);
  offres: any[] = [];
  searchTitre = '';
  searchGouvernorat = '';
  gouvernorats = ['Tunis','Ariana','Ben Arous','Manouba','Nabeul','Sfax','Sousse','Bizerte'];

  ngOnInit() { this.rechercher(); }

  rechercher() {
    this.api.get<any>('/offres', { titre: this.searchTitre || null, gouvernorat: this.searchGouvernorat || null, page: 0, size: 20 })
      .subscribe({ next: (res) => { if (res.data?.content) this.offres = res.data.content; }, error: () => {} });
  }
}
