import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router'

import { Veiculo } from '../veiculo'
import { VeiculosService } from '../../veiculos.service'
import { Observable } from 'rxjs';
@Component({
  selector: 'app-veiculos-form',
  templateUrl: './veiculos-form.component.html',
  styleUrls: ['./veiculos-form.component.css']
})
export class VeiculosFormComponent implements OnInit {

  veiculo: Veiculo;
  success: boolean = false;
  errors: String[];
  idVeiculo: number;
  cadastrado: boolean = false;

  constructor(
    private service: VeiculosService,
    private router: Router,
    private activatedRoute: ActivatedRoute) { 
    this.veiculo = new Veiculo();
  }

  ngOnInit(): void {
    let params: Observable<Params> = this.activatedRoute.params;
    params.subscribe(urlParams => {
      this.idVeiculo = urlParams['idVeiculo'];
      if (this.idVeiculo) {
        this.service.getVeiculoById(this.idVeiculo)
          .subscribe(
            response => {
              this.veiculo = response;
              this.cadastrado = true;
            },
            errorResponse => this.veiculo = new Veiculo())
      }
    })
  }

  voltarParaListagem() {
    this.router.navigate(['/veiculos-lista']);
  }

  onSubmit() {
    //Verifica se campo de ano/modelo foram preenchidos corretamente.
    if (this.verificaAnoModelo()) {
      if (this.idVeiculo) {
        //Atualiza Veículo
        this.service.atualizar(this.veiculo)
          .subscribe(response => {
            this.success = true;
            this.errors = null;
          }, errorResponse => {
            this.success = false;
            this.errors = ['Erro ao atualizar o veículo.'];
          })
      } else {
        //Salva Veículo
        this.service.salvar(this.veiculo)
          .subscribe(response => {
            this.success = true;
            this.errors = null;
            this.veiculo = response;
            this.cadastrado = true;
          }, errorResponse => {
            this.success = false;
            this.errors = errorResponse.error.errors;
          })
      }
    }
  }

  verificaAnoModelo(): boolean {
    if (this.veiculo.ano != null && this.veiculo.ano.toString() != '' && this.veiculo.modelo != null && this.veiculo.modelo.toString() != '') {
      if (!(this.veiculo.ano > 2000 && this.veiculo.ano <= 2100)) {
        this.errors = ['O Ano do veículo deve estar entre 2001~2100'];
        return false;
      }

      if (!(this.veiculo.modelo >= this.veiculo.ano && this.veiculo.modelo <= 2100)) {
        this.errors = ['O Modelo do veículo deve ser maior que o Ano do veículo e menor ou igual a 2100'];
        return false;
      }
    } else if (this.veiculo.ano != null && this.veiculo.ano.toString() != '') {
      if (!(this.veiculo.ano > 2000 && this.veiculo.ano <= 2100)) {
        this.errors = ['O Ano do veículo deve estar entre 2001~2100'];
        return false;
      }
    } else if (this.veiculo.modelo != null && this.veiculo.modelo.toString() != '') {
      if (!(this.veiculo.modelo > 2000 && this.veiculo.modelo <= 2100)) {
        this.errors = ['O Modelo do veículo deve estar entre 2001~2100'];
        return false;
      }
    }
    return true;
  }

}
