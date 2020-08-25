import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

import { Veiculo } from './veiculos/veiculo';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment'

@Injectable({
  providedIn: 'root'
})
export class VeiculosService {

  apiUrl: string = environment.apiUrlVeiculos;

  constructor(private http: HttpClient) {
    
  }

  salvar(veiculo: Veiculo): Observable<Veiculo> {
    return this.http.post<Veiculo>(`${this.apiUrl}`, veiculo);
  }

  atualizar(veiculo: Veiculo): Observable<any> {
    return this.http.put<Veiculo>(`${this.apiUrl}/${veiculo.idVeiculo}`, veiculo);
  }

  deletar(veiculo: Veiculo): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${veiculo.idVeiculo}`);
  }

  getVeiculos(): Observable<Veiculo[]> {
    return this.http.get<Veiculo[]>(`${this.apiUrl}`);
  }

  getVeiculoById(idVeiculo: number): Observable<Veiculo> {
    return this.http.get<any>(`${this.apiUrl}/${idVeiculo}`);
  }

  getVeiculosDisponiveis(): Observable<Veiculo[]> {
    return this.http.get<Veiculo[]>(`${this.apiUrl}/disponiveis`);
  }
}
