import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Cliente } from '../../clientes/cliente';
import { ClientesService } from '../../clientes.service'
import { Veiculo } from '../../veiculos/veiculo';
import { VeiculosService } from '../../veiculos.service'
import { Aluguel } from '../aluguel';
import { AlugueisService } from 'src/app/alugueis.service';
import { empty } from 'rxjs';

@Component({
  selector: 'app-alugueis-form',
  templateUrl: './alugueis-form.component.html',
  styleUrls: ['./alugueis-form.component.css']
})
export class AlugueisFormComponent implements OnInit {

  clientes: Cliente[] = []
  veiculos: Veiculo[] = []
  aluguel: Aluguel;
  success: boolean = false;
  mensagemSuccess: string;
  errors: String[];
  cadastrado: boolean = false;

  constructor(
    private clientesService: ClientesService,
    private veiculosService: VeiculosService,
    private alugueisService: AlugueisService,
    private router: Router
    ) { 
      this.aluguel = new Aluguel();
  }

  ngOnInit(): void {
    //Pega clientes para listagem
    this.clientesService.getClientesAtivos()
      .subscribe(response => this.clientes = response);

    //Pega veículos para listagem
    this.veiculosService.getVeiculosDisponiveis()
      .subscribe(response => this.veiculos = response);
  }

  voltarParaListagem() {
    this.router.navigate(['/alugueis-lista']);
  }

  onSubmit() {
    if (this.validarCampos()) {
      this.alugueisService.salvar(this.aluguel)
      .subscribe(response => {
        //Caso dê certo o cadastro
        this.success = true;
        this.errors = null;
        this.aluguel = response;
        this.mensagemSuccess = 'Aluguel efetuado com sucesso! Código para devolução: ' + this.aluguel.idAluguel;
        this.aluguel = new Aluguel();
  
        //Atualiza clientes para listagem
        this.clientesService.getClientesAtivos()
        .subscribe(response => this.clientes = response);
  
        //Atualiza veículos para listagem
        this.veiculosService.getVeiculosDisponiveis()
          .subscribe(response => this.veiculos = response);
      }, errorResponse => {
        this.success = false;
        this.errors = errorResponse.error.errors;
      })
    } 
  }

  validarCampos() {
    if (this.aluguel.idCliente == null) {
      this.errors = ['Selecione o cliente!'];
      return false;
    } else if (this.aluguel.idVeiculo == null) {
      this.errors = ['Selecione o veículo!'];
      return false;
    }

    return true;
  }
}
