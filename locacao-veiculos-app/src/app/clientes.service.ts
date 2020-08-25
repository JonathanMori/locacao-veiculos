import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

import { Cliente } from './clientes/cliente';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment'

@Injectable({
  providedIn: 'root'
})
export class ClientesService {

  apiUrl: string = environment.apiUrlClientes;

  constructor(private http: HttpClient) {
    
  }

  salvar(cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(`${this.apiUrl}`, cliente);
  }

  atualizar(cliente: Cliente): Observable<any> {
    return this.http.put<Cliente>(`${this.apiUrl}/${cliente.idCliente}`, cliente);
  }

  deletar(cliente: Cliente): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${cliente.idCliente}`);
  }

  getClientes(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(`${this.apiUrl}`);
  }

  getClienteById(idCliente: number): Observable<Cliente> {
    return this.http.get<any>(`${this.apiUrl}/${idCliente}`);
  }

  getClientesAtivos(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(`${this.apiUrl}/ativos`);
  }
}
