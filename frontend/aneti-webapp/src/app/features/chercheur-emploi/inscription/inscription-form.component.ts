import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatStepperModule } from '@angular/material/stepper';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { ApiService } from '@core/services/api.service';

@Component({
  selector: 'app-inscription-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatSelectModule, MatButtonModule, MatStepperModule, MatDatepickerModule, MatNativeDateModule],
  template: `
    <div class="page-header"><h1>Inscription Chercheur d'Emploi</h1></div>
    <mat-stepper linear #stepper>
      <mat-step [stepControl]="identiteForm" label="Identité">
        <form [formGroup]="identiteForm">
          <div class="form-row">
            <mat-form-field><mat-label>CIN</mat-label><input matInput formControlName="cin"></mat-form-field>
            <mat-form-field><mat-label>Nom</mat-label><input matInput formControlName="nom"></mat-form-field>
            <mat-form-field><mat-label>Prénom</mat-label><input matInput formControlName="prenom"></mat-form-field>
          </div>
          <div class="form-row">
            <mat-form-field><mat-label>Nom (Arabe)</mat-label><input matInput formControlName="nomAr" dir="rtl"></mat-form-field>
            <mat-form-field><mat-label>Prénom (Arabe)</mat-label><input matInput formControlName="prenomAr" dir="rtl"></mat-form-field>
          </div>
          <div class="form-row">
            <mat-form-field><mat-label>Date de naissance</mat-label><input matInput [matDatepicker]="picker" formControlName="dateNaissance"><mat-datepicker-toggle matIconSuffix [for]="picker"></mat-datepicker-toggle><mat-datepicker #picker></mat-datepicker></mat-form-field>
            <mat-form-field><mat-label>Sexe</mat-label><mat-select formControlName="sexe"><mat-option value="M">Masculin</mat-option><mat-option value="F">Féminin</mat-option></mat-select></mat-form-field>
            <mat-form-field><mat-label>Situation familiale</mat-label><mat-select formControlName="situationFamiliale"><mat-option value="CELIBATAIRE">Célibataire</mat-option><mat-option value="MARIE">Marié(e)</mat-option></mat-select></mat-form-field>
          </div>
          <div class="actions-bar"><button mat-raised-button color="primary" matStepperNext>Suivant</button></div>
        </form>
      </mat-step>

      <mat-step [stepControl]="contactForm" label="Contact & Adresse">
        <form [formGroup]="contactForm">
          <div class="form-row">
            <mat-form-field><mat-label>Email</mat-label><input matInput type="email" formControlName="email"></mat-form-field>
            <mat-form-field><mat-label>Téléphone</mat-label><input matInput formControlName="telephone"></mat-form-field>
          </div>
          <div class="form-row">
            <mat-form-field><mat-label>Adresse</mat-label><input matInput formControlName="adresse"></mat-form-field>
            <mat-form-field><mat-label>Gouvernorat</mat-label><mat-select formControlName="gouvernorat"><mat-option *ngFor="let g of gouvernorats" [value]="g">{{ g }}</mat-option></mat-select></mat-form-field>
          </div>
          <div class="actions-bar"><button mat-button matStepperPrevious>Précédent</button><button mat-raised-button color="primary" matStepperNext>Suivant</button></div>
        </form>
      </mat-step>

      <mat-step [stepControl]="formationForm" label="Formation & Expérience">
        <form [formGroup]="formationForm">
          <div class="form-row">
            <mat-form-field><mat-label>Niveau d'étude</mat-label><mat-select formControlName="niveauEtude"><mat-option value="SANS">Sans diplôme</mat-option><mat-option value="BAC">Baccalauréat</mat-option><mat-option value="LIC">Licence</mat-option><mat-option value="MAST">Master/Ingénieur</mat-option><mat-option value="DOCT">Doctorat</mat-option></mat-select></mat-form-field>
            <mat-form-field><mat-label>Diplôme</mat-label><input matInput formControlName="diplome"></mat-form-field>
          </div>
          <div class="form-row">
            <mat-form-field><mat-label>Spécialité</mat-label><input matInput formControlName="specialite"></mat-form-field>
            <mat-form-field><mat-label>Années d'expérience</mat-label><input matInput type="number" formControlName="experienceAnnees"></mat-form-field>
          </div>
          <div class="actions-bar"><button mat-button matStepperPrevious>Précédent</button><button mat-raised-button color="accent" (click)="soumettre()">Soumettre l'inscription</button></div>
        </form>
      </mat-step>
    </mat-stepper>
  `,
  styles: [`.form-row{display:flex;gap:16px;margin-bottom:8px} .form-row mat-form-field{flex:1}`]
})
export class InscriptionFormComponent {
  private fb = inject(FormBuilder);
  private api = inject(ApiService);

  gouvernorats = ['Tunis','Ariana','Ben Arous','Manouba','Nabeul','Zaghouan','Bizerte','Béja','Jendouba','Le Kef','Siliana','Sousse','Monastir','Mahdia','Sfax','Kairouan','Kasserine','Sidi Bouzid','Gabès','Médenine','Tataouine','Gafsa','Tozeur','Kébili'];

  identiteForm: FormGroup = this.fb.group({
    cin: [''], nom: ['', Validators.required], prenom: ['', Validators.required],
    nomAr: [''], prenomAr: [''], dateNaissance: [''], sexe: [''], situationFamiliale: ['']
  });

  contactForm: FormGroup = this.fb.group({
    email: ['', [Validators.required, Validators.email]], telephone: [''],
    adresse: [''], gouvernorat: ['']
  });

  formationForm: FormGroup = this.fb.group({
    niveauEtude: [''], diplome: [''], specialite: [''], experienceAnnees: [0]
  });

  soumettre(): void {
    const data = { ...this.identiteForm.value, ...this.contactForm.value, ...this.formationForm.value };
    this.api.post('/inscriptions/chercheurs-emploi', data).subscribe({
      next: (res) => console.log('Inscription créée:', res),
      error: (err) => console.error('Erreur:', err)
    });
  }
}
