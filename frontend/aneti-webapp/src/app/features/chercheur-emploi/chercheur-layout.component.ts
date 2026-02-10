import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { I18nService } from '@core/services/i18n.service';

@Component({
  selector: 'app-chercheur-layout',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink, RouterLinkActive, MatSidenavModule, MatToolbarModule, MatListModule, MatIconModule, MatButtonModule],
  template: `
    <mat-toolbar color="primary">
      <button mat-icon-button (click)="sidenav.toggle()"><mat-icon>menu</mat-icon></button>
      <span>{{ i18n.translate('app.title') }} - {{ i18n.currentLang() === 'fr' ? 'Espace Chercheur d\\'Emploi' : 'فضاء طالب الشغل' }}</span>
      <span class="spacer"></span>
      <button mat-button (click)="toggleLang()">{{ i18n.currentLang() === 'fr' ? 'العربية' : 'FR' }}</button>
      <button mat-icon-button><mat-icon>notifications</mat-icon></button>
      <button mat-icon-button><mat-icon>account_circle</mat-icon></button>
    </mat-toolbar>
    <mat-sidenav-container>
      <mat-sidenav #sidenav mode="side" opened>
        <mat-nav-list>
          <a mat-list-item routerLink="dashboard" routerLinkActive="active"><mat-icon>dashboard</mat-icon> {{ i18n.currentLang() === 'fr' ? 'Tableau de bord' : 'لوحة القيادة' }}</a>
          <a mat-list-item routerLink="profil" routerLinkActive="active"><mat-icon>person</mat-icon> {{ i18n.translate('nav.profil') }}</a>
          <a mat-list-item routerLink="offres" routerLinkActive="active"><mat-icon>work</mat-icon> {{ i18n.translate('nav.offres') }}</a>
          <a mat-list-item routerLink="candidatures" routerLinkActive="active"><mat-icon>assignment</mat-icon> {{ i18n.translate('nav.candidatures') }}</a>
          <a mat-list-item routerLink="accompagnement" routerLinkActive="active"><mat-icon>handshake</mat-icon> {{ i18n.translate('nav.accompagnement') }}</a>
          <a mat-list-item routerLink="rdv" routerLinkActive="active"><mat-icon>event</mat-icon> {{ i18n.translate('nav.rdv') }}</a>
        </mat-nav-list>
      </mat-sidenav>
      <mat-sidenav-content><router-outlet></router-outlet></mat-sidenav-content>
    </mat-sidenav-container>
  `,
  styles: [`.spacer{flex:1 1 auto} mat-sidenav{width:260px} mat-sidenav-content{padding:24px} .active{background:rgba(21,101,192,0.1)}`]
})
export class ChercheurLayoutComponent {
  i18n = inject(I18nService);
  toggleLang() { this.i18n.setLanguage(this.i18n.currentLang() === 'fr' ? 'ar' : 'fr'); }
}
