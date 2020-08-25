import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { Aluguel } from './alugueis/aluguel';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment'
import { AluguelBusca } from './alugueis/aluguelBusca';

@Injectable({
  providedIn: 'root'
})
export class AlugueisService {

  apiUrl: string = environment.apiUrlAlugueis;

  constructor(private http: HttpClient) { }

  salvar(aluguel: Aluguel): Observable<Aluguel> {
    return this.http.post<Aluguel>(`${this.apiUrl}`, aluguel);
  }

  buscar(filtro: string, idCliente: string): Observable<AluguelBusca[]> {
    const httpParams = new HttpParams().set("filtro", filtro).set("idCliente", idCliente);
    const url = this.apiUrl + "?" + httpParams.toString();

    return this.http.get<any>(url);
  }

  pegaAluguelPorId(idAluguel: number): Observable<AluguelBusca> {
    return this.http.get<any>(`${this.apiUrl}/${idAluguel}`);
  }

  devolver(aluguel: Aluguel): Observable<Aluguel> {
    return this.http.put<Aluguel>(`${this.apiUrl}`, aluguel);
  }
}
