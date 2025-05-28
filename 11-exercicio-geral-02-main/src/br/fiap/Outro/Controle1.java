package br.fiap.Outro;

import br.fiap.assento.Assento;
import br.fiap.cliente.Cliente;
import br.fiap.cliente.PessoaFisica;
import br.fiap.cliente.PessoaJuridica;
import br.fiap.reserva.Reserva;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.parseInt;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class Controle1 {

    private static List<Cliente> listaClientes = new ArrayList<>();
    private static List<Assento> listaAssento = new ArrayList<>();
    private List<Reserva> listaReserva = new ArrayList<>();
    private Reserva reserva;




    // Tranforme em static para deixar os dados mocados, para não precisar cadastrar
    static {
        // lista de clientes
        listaClientes.add(new PessoaJuridica("Bernardo", "be@.com", "cpjb"));
        listaClientes.add(new PessoaFisica("Davy", "da@.com", "cpfd"));
        listaClientes.add(new PessoaJuridica("Luju", "lu@.com", "cpjl"));
        listaClientes.add(new PessoaFisica("Nima", "ni@.com", "cpfn"));


        // lista de assento
        for(int i = 1; i <= 10; i++){
            listaAssento.add(new Assento(i));
        }
    }


    public void menu() {
        int opcao;

        while(true) {
            try {
                opcao = parseInt(showInputDialog(gerarMenu()));
                switch(opcao) {
                    case 1:
                        reservar();
                        break;
                    case 2:
                        pesquisar();
                        break;
                    case 3:
                        cancelar();
                        break;
                    case 4:
                        return;
                    default:
                        showMessageDialog(null, "Opção inválida");
                }
            }
            catch(NumberFormatException e) {
                showMessageDialog(null, "você deve digitar um número");
            }
        }
    }

    private void cancelar() {
        String id;
      id =  showInputDialog("Informe o indentificador para calcelar a reserva");
        for(Reserva reserva : listaReserva) {
            if (reserva.getCliente().getIdentificador().equalsIgnoreCase(id)) {
               listaReserva.remove(reserva);

                return;

            }
        }
    }

    private void pesquisar() {
        String indentificador = showInputDialog("CPF/CNPF para pesquisa ");
        for(Reserva reserva : listaReserva){
            if (reserva.getCliente().getIdentificador().equalsIgnoreCase(indentificador)) {
                showMessageDialog(null, reserva.toString());
                return;

            }

        }
        showMessageDialog(null, "Reserva não foi encontrada");
    }

    private void reservar() {
        int cadeira;
        Assento assentoVerificacao = null;
        boolean encontrado = false;

        cadeira = parseInt(showInputDialog("Escolha o seu assento"));

        for(Assento assento : listaAssento) {
            if (assento.getNumero() == cadeira) {
                assentoVerificacao = assento;
                encontrado = true;

               break;
              }
            }

        if (!encontrado) {
            showMessageDialog(null, "Esse assento não existe.");
        } else {
            if (assentoVerificacao.isDisponivel() == true){
                  assentoVerificacao.setDisponivel(false);
                  assentoVerificacao.setNumero(cadeira);
                showMessageDialog(null, "Assento reservado com sucesso.");
                double valor = 100;
                Random rd = new Random();
                Cliente cliente = listaClientes.get(rd.nextInt(listaClientes.size()));
                reserva = new Reserva(cliente, assentoVerificacao, valor);
                listaReserva.add(reserva);

            } else {
                showMessageDialog(null, "Assento já está ocupado.");
            }
        }

    }

    private String gerarMenu() {
        String aux = "SISTEMA DE RESERVA DE PASSAGEM AÉREA\n";
        aux += "1. Reservar\n";
        aux += "2. Pesquisar reserva\n";
        aux += "3. Cancelar reserva\n";
        aux += "4. Finalizar";
        return aux;
    }
}
