import { Injectable, signal } from '@angular/core';

export type Language = 'fr' | 'ar';

@Injectable({ providedIn: 'root' })
export class I18nService {
  currentLang = signal<Language>('fr');

  private translations: Record<Language, Record<string, string>> = {
    fr: {
      'app.title': 'ANETI - Agence Nationale pour l\'Emploi et le Travail Indépendant',
      'nav.accueil': 'Accueil',
      'nav.offres': 'Offres d\'emploi',
      'nav.inscription': 'Inscription',
      'nav.profil': 'Mon profil',
      'nav.candidatures': 'Mes candidatures',
      'nav.rdv': 'Rendez-vous',
      'nav.accompagnement': 'Accompagnement',
      'nav.pae': 'Programmes PAE',
      'nav.admin': 'Administration',
      'btn.soumettre': 'Soumettre',
      'btn.valider': 'Valider',
      'btn.rejeter': 'Rejeter',
      'btn.annuler': 'Annuler',
      'btn.enregistrer': 'Enregistrer',
      'btn.rechercher': 'Rechercher',
      'btn.connexion': 'Connexion',
      'btn.deconnexion': 'Déconnexion',
      'statut.brouillon': 'Brouillon',
      'statut.soumis': 'Soumis',
      'statut.valide': 'Validé',
      'statut.rejete': 'Rejeté',
      'statut.en_cours': 'En cours',
      'statut.actif': 'Actif',
    },
    ar: {
      'app.title': 'الوكالة الوطنية للتشغيل والعمل المستقل',
      'nav.accueil': 'الرئيسية',
      'nav.offres': 'عروض الشغل',
      'nav.inscription': 'التسجيل',
      'nav.profil': 'ملفي الشخصي',
      'nav.candidatures': 'ترشحاتي',
      'nav.rdv': 'المواعيد',
      'nav.accompagnement': 'المرافقة',
      'nav.pae': 'برامج التشغيل',
      'nav.admin': 'الإدارة',
      'btn.soumettre': 'إرسال',
      'btn.valider': 'المصادقة',
      'btn.rejeter': 'رفض',
      'btn.annuler': 'إلغاء',
      'btn.enregistrer': 'حفظ',
      'btn.rechercher': 'بحث',
      'btn.connexion': 'تسجيل الدخول',
      'btn.deconnexion': 'تسجيل الخروج',
      'statut.brouillon': 'مسودة',
      'statut.soumis': 'مُرسل',
      'statut.valide': 'مُصادق عليه',
      'statut.rejete': 'مرفوض',
      'statut.en_cours': 'قيد التنفيذ',
      'statut.actif': 'نشط',
    }
  };

  translate(key: string): string {
    return this.translations[this.currentLang()][key] || key;
  }

  setLanguage(lang: Language): void {
    this.currentLang.set(lang);
    document.documentElement.dir = lang === 'ar' ? 'rtl' : 'ltr';
    document.documentElement.lang = lang;
  }
}
