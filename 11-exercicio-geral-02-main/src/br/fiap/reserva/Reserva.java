package br.fiap.reserva;

import br.fiap.assento.Assento;
import br.fiap.cliente.Cliente;
import br.fiap.desconto.Desconto;

public class Reserva {
    private Cliente cliente;
    private double valorOriginal;
    private double valorFinal;
    private Assento assento;

    public Reserva(Cliente cliente, Assento assento, double valorOriginal) {
        this.cliente = cliente;
        this.assento = assento;
        this.valorOriginal = valorOriginal;
        aplicarDesconto();
    }

    private void aplicarDesconto() {
        if(cliente instanceof Desconto) {
            valorFinal = ((Desconto) cliente).aplicarDesconto(valorOriginal);
            // aqui estou fazendo a conversão, cliente meio que vira desconto
        }
        else {
            valorFinal = valorOriginal;
        }

    }

    public double getValorOriginal() {
        return valorOriginal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public Assento getAssento() {
        return assento;
    }
}
