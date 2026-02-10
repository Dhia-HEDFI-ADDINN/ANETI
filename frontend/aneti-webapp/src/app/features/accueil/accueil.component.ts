import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { I18nService } from '@core/services/i18n.service';

@Component({
  selector: 'app-accueil',
  standalone: true,
  imports: [CommonModule, RouterLink, MatCardModule, MatButtonModule, MatIconModule, MatToolbarModule],
  template: `
    <mat-toolbar color="primary" class="header-toolbar">
      <span>{{ i18n.translate('app.title') }}</span>
      <span class="spacer"></span>
      <button mat-button (click)="toggleLanguage()">
        {{ i18n.currentLang() === 'fr' ? 'العربية' : 'Français' }}
      </button>
      <button mat-raised-button color="accent" routerLink="/chercheur/inscription">
        {{ i18n.translate('nav.inscription') }}
      </button>
      <button mat-button routerLink="/chercheur">{{ i18n.translate('btn.connexion') }}</button>
    </mat-toolbar>

    <div class="hero-section">
      <h1>{{ i18n.currentLang() === 'fr' ? 'Bienvenue sur la plateforme ANETI' : 'مرحبا بكم في منصة الوكالة الوطنية للتشغيل' }}</h1>
      <p>{{ i18n.currentLang() === 'fr' 
        ? 'Votre partenaire pour l\\'emploi en Tunisie - Inscription, recherche d\\'offres, accompagnement personnalisé'
        : 'شريكك في التشغيل بتونس - التسجيل، البحث عن عروض الشغل، المرافقة الشخصية' }}</p>
    </div>

    <div class="container">
      <div class="card-grid">
        <mat-card class="portal-card" routerLink="/chercheur">
          <mat-card-header>
            <mat-icon mat-card-avatar>person_search</mat-icon>
            <mat-card-title>{{ i18n.currentLang() === 'fr' ? 'Espace Chercheur d\\'Emploi' : 'فضاء طالب الشغل' }}</mat-card-title>
          </mat-card-header>
          <mat-card-content>
            <p>{{ i18n.currentLang() === 'fr' 
              ? 'Inscrivez-vous, recherchez des offres, postulez et suivez votre parcours d\\'accompagnement.' 
              : 'سجل، ابحث عن عروض الشغل، قدم ترشحك وتابع مسار مرافقتك.' }}</p>
          </mat-card-content>
        </mat-card>

        <mat-card class="portal-card" routerLink="/entreprise">
          <mat-card-header>
            <mat-icon mat-card-avatar>business</mat-icon>
            <mat-card-title>{{ i18n.currentLang() === 'fr' ? 'Espace Entreprise' : 'فضاء المؤسسة' }}</mat-card-title>
          </mat-card-header>
          <mat-card-content>
            <p>{{ i18n.currentLang() === 'fr' 
              ? 'Publiez vos offres, gérez les candidatures et bénéficiez des programmes d\\'aide à l\\'emploi.'
              : 'انشر عروضك، أدر الترشحات واستفد من برامج دعم التشغيل.' }}</p>
          </mat-card-content>
        </mat-card>

        <mat-card class="portal-card" routerLink="/conseiller">
          <mat-card-header>
            <mat-icon mat-card-avatar>support_agent</mat-icon>
            <mat-card-title>{{ i18n.currentLang() === 'fr' ? 'Espace Conseiller' : 'فضاء المستشار' }}</mat-card-title>
          </mat-card-header>
          <mat-card-content>
            <p>{{ i18n.currentLang() === 'fr' 
              ? 'Gérez les usagers, les offres, les rendez-vous et pilotez vos indicateurs de performance.'
              : 'أدر المنتفعين، العروض، المواعيد وتابع مؤشرات أدائك.' }}</p>
          </mat-card-content>
        </mat-card>

        <mat-card class="portal-card" routerLink="/admin">
          <mat-card-header>
            <mat-icon mat-card-avatar>admin_panel_settings</mat-icon>
            <mat-card-title>{{ i18n.currentLang() === 'fr' ? 'Administration' : 'الإدارة' }}</mat-card-title>
          </mat-card-header>
          <mat-card-content>
            <p>{{ i18n.currentLang() === 'fr' 
              ? 'Paramétrage, gestion des utilisateurs, référentiels et tableaux de bord.'
              : 'الإعدادات، إدارة المستخدمين، المراجع ولوحات القيادة.' }}</p>
          </mat-card-content>
        </mat-card>
      </div>
    </div>
  `,
  styles: [`
    .header-toolbar { position: sticky; top: 0; z-index: 100; }
    .spacer { flex: 1 1 auto; }
    .hero-section {
      background: linear-gradient(135deg, #1565c0 0%, #0d47a1 100%);
      color: white;
      padding: 64px 24px;
      text-align: center;
      h1 { font-size: 36px; margin-bottom: 16px; }
      p { font-size: 18px; opacity: 0.9; max-width: 700px; margin: 0 auto; }
    }
    .container { padding: 48px 24px; }
    .portal-card {
      cursor: pointer;
      transition: transform 0.2s, box-shadow 0.2s;
      &:hover { transform: translateY(-4px); box-shadow: 0 8px 24px rgba(0,0,0,0.12); }
      mat-icon { font-size: 40px; width: 40px; height: 40px; color: #1565c0; }
    }
  `]
})
export class AccueilComponent {
  i18n = inject(I18nService);

  toggleLanguage(): void {
    this.i18n.setLanguage(this.i18n.currentLang() === 'fr' ? 'ar' : 'fr');
  }
}
