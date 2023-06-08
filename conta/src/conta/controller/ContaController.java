package conta.controller;

import java.util.ArrayList;

import conta.Repository.ContaRepository;
import conta.model.Conta;

public class ContaController implements ContaRepository{
	
	ArrayList<Conta> listaContas = new ArrayList<Conta>();
	int numero = 0;

	@Override
	public void procurarPorNumero(int numero) {
		var conta = buscarNaCollection(numero);
		
		if (conta != null)
			conta.visualizar();
		else
			System.out.println("\n Conta numero: " + numero + "não foi encontrada!");
		
	}

	@Override
	public void listarTodas() {
	
	
		for (var conta : listaContas){
			conta.visualizar();
		}
		
	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.println("\n A conta numero: " + conta.getNumero() + " foi criada com sucesso!");
		
		
	}

	@Override
	public void atualizar(Conta conta) {
		var buscaConta = buscarNaCollection(conta.getNumero());
		
		if(buscaConta != null) {
			listaContas.set(listaContas.indexOf(buscaConta), conta);
			System.out.println("\n A Conta numero: " + conta.getAgencia() + "foi atualizada com sucesso");
		}else
			System.out.println("\n Conta numero: "+ conta.getNumero() + "não foi encontrada!");
		
	}

	@Override
	public void deletar(int numero) {
		var conta = buscarNaCollection(numero);
		
		if(conta != null) {
			if(listaContas.remove(conta) == true)
				System.out.println("\n A Conta numero: " + numero + "foi deletada com sucesso!");
		}else
			System.out.println("\n Conta numero: "+ numero + "não foi encontrada!");
		
	}

	@Override
	public void sacar(int numero, float valor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void depositar(int numero, float valor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		// TODO Auto-generated method stub
		
	}
	
	
	public int gerarNumero() {
		return ++ numero ;
	}
	
	public Conta buscarNaCollection (int numero) {
		for (var conta: listaContas) {
			if(conta.getNumero() == numero) {
				return conta;
			}
		}
		
		return null;
	}
	
	public int retornaTipo(int numero) {
		for (var conta: listaContas) {
			if(conta.getNumero() == numero) {
				return conta.getTipo();
			}
		}
		return 0;
	}
	

}
