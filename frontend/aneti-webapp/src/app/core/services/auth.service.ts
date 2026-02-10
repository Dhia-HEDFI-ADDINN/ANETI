import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

export interface UserProfile {
  id: string;
  username: string;
  email: string;
  nom: string;
  prenom: string;
  roles: string[];
  type: 'CHERCHEUR_EMPLOI' | 'ENTREPRISE' | 'CONSEILLER' | 'ADMIN';
}

@Injectable({ providedIn: 'root' })
export class AuthService {
  private tokenKey = 'aneti_token';
  private userSubject = new BehaviorSubject<UserProfile | null>(null);
  public user$ = this.userSubject.asObservable();

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  setToken(token: string): void {
    localStorage.setItem(this.tokenKey, token);
  }

  logout(): void {
    localStorage.removeItem(this.tokenKey);
    this.userSubject.next(null);
  }

  isAuthenticated(): boolean {
    return !!this.getToken();
  }

  hasRole(role: string): boolean {
    const user = this.userSubject.value;
    return user?.roles?.includes(role) ?? false;
  }

  getCurrentUser(): UserProfile | null {
    return this.userSubject.value;
  }

  setCurrentUser(user: UserProfile): void {
    this.userSubject.next(user);
  }
}
