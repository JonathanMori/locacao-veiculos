import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router'

import { Cliente } from '../cliente'
import { ClientesService } from '../../clientes.service'
import { Observable } from 'rxjs';
@Component({
  selector: 'app-clientes-form',
  templateUrl: './clientes-form.component.html',
  styleUrls: ['./clientes-form.component.css']
})
export class ClientesFormComponent implements OnInit {

  cliente: Cliente;
  success: boolean = false;
  errors: String[];
  idCliente: number;
  cadastrado: boolean = false;

  constructor(
    private service: ClientesService,
    private router: Router,
    private activatedRoute: ActivatedRoute) { 
    this.cliente = new Cliente();
  }

  ngOnInit(): void {
    let params: Observable<Params> = this.activatedRoute.params;
    params.subscribe(urlParams => {
      this.idCliente = urlParams['idCliente'];
      if (this.idCliente) {
        this.service.getClienteById(this.idCliente)
          .subscribe(
            response => {
              this.cliente = response
              this.cadastrado = true;
            },
            errorResponse => this.cliente = new Cliente())
      }
    })
  }

  voltarParaListagem() {
    this.router.navigate(['/clientes-lista']);
  }

  onSubmit() {
    if(this.idCliente) {
      //Atualiza Cliente
      this.service.atualizar(this.cliente)
        .subscribe(response => {
          this.success = true;
          this.errors = null;
        }, errorResponse => {
          this.success = false;
          this.errors = ['Erro ao atualizar o cliente.'];
        })
    } else {
      //Salva Cliente
      this.service.salvar(this.cliente)
        .subscribe(response => {
          this.success = true;
          this.errors = null;
          this.cliente = response;
          this.cadastrado = true;
        }, errorResponse => {
          this.success = false;
          this.errors = errorResponse.error.errors;
        })
    }
  }

}
