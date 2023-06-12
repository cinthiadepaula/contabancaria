package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import conta.util.Cores;
import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.controller.*;

public class Menu {

	public static void main(String[] args) {
		
		ContaController contas = new  ContaController();
		
		
		Scanner in = new Scanner(System.in);
		int option, numero, agencia, tipo, aniversario, numeroDestino;
		String titular;
		float saldo = 0, limite, valor;
		
		
		ContaCorrente cc = new ContaCorrente(2, 123, 1, "Mariana", 10000.0f, 1000.0f);
		ContaPoupanca cp = new ContaPoupanca(3, 123, 2 , "Victor", 100000.0f, 15);
		
		
		
		
		while(true) {
			System.out.println(Cores.ANSI_BLACK_BACKGROUND + Cores.TEXT_YELLOW_BOLD + "**************************************************" );
			System.out.println("                                                  ");
			System.out.println("                  BANCO DO BRAZIL                 ");
			System.out.println("                                                  ");
			System.out.println("**************************************************");
			System.out.println("                                                  ");
			System.out.println("        1 - Criar Conta                           ");
			System.out.println("        2 - Listar todas as Contas                ");
			System.out.println("        3 - Buscar Conta por Numero               ");
			System.out.println("        4 - Atualizar Dados da Conta              ");
			System.out.println("        5 - Apagar Conta                          ");
			System.out.println("        6 - Sacar                                 ");
			System.out.println("        7 - Depositar                             ");
			System.out.println("        8 - Transferir valores entre Contas       ");
			System.out.println("        9 - Sair                                  ");
			System.out.println("                                                  ");
			System.out.println("**************************************************");
			System.out.println("        Entre com a opção desejada:               ");
			System.out.println("                                                  "+ Cores.TEXT_RESET);
			
			
			
			try {
				option = in.nextInt();
				
			} catch(InputMismatchException e) {
				System.out.println("\n Digite Valores inteiros!");
				in.nextLine();
				option = 0;
				
			}
			
			if(option ==9) {
				System.out.println(Cores.TEXT_WHITE_BOLD + "\nBanco do Brazil - O seu Futuro começa aqui!");
				in.close();
				System.exit(0);
			}
			
			switch (option) {
			case 1:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Criar Conta \n\n");
				
				System.out.println("Digite o Numero da Agência: ");
				agencia = in.nextInt();
				System.out.println("Digite o Nome do Titular: ");
				in.skip("\\R?");
				titular = in.nextLine();
				
				do{
					System.out.println("Digite o Tipo da Conta ([1] - CC ou [2] - CP: ");
					tipo = in.nextInt();
				}while(tipo < 1 && tipo > 2);
				
				System.out.println("Digite o saldo da Conta (R$): ");
				saldo = in.nextFloat();
				
				switch(tipo){
					case 1 -> {
						System.out.println("Digite o limite de Crédito (R$): ");
						limite = in.nextFloat();
						contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
					}
					case 2 -> {
						System.out.println("Digite o dia do Aniversario da Conta: ");
						aniversario = in.nextInt();
						contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
					}
				}
				
				
				keyPress();
				break;
			
			case 2:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Listar todas as Contas\n\n");
				contas.listarTodas();
				keyPress();
				break;
				
			case 3:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Consultar dados da Conta - por número \n\n");
				
				System.out.println("Digite o número da Conta: ");
				numero = in.nextInt();
				
				contas.procurarPorNumero(numero);
				
				keyPress();
				break;
				
			case 4:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Atualizar dados da Conta \n\n");
				
				System.out.println("Digite o número da conta: ");
				numero = in.nextInt();
				
				if(contas.buscarNaCollection(numero) != null) {
					System.out.println("Digite o Número da Agência: ");
					agencia = in.nextInt();
					System.out.println("Digite o nome do Titular: ");
					in.skip("\\R");
					titular = in.nextLine();
					
					tipo = contas.retornaTipo(numero);
					
					switch(tipo) {
					case 1 -> {
						System.out.println("Digite o Limite de Crédito (R$): ");
						limite = in.nextFloat();
						contas.atualizar(new ContaCorrente (numero, agencia, tipo, titular, saldo, limite));
					}
					case 2 -> {
						System.out.println("Digite o dia do Aniversario da Conta: ");
						aniversario = in.nextInt();
						contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
					}
					default -> {
						System.out.println("Tipo de conta inválido!");
					}
					}
				}else 
					System.out.println("\n Conta não encontrada!");
				
				keyPress();
				break;
			
			case 5:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Apagar Conta \n\n");
				
				System.out.println("Digite o número da conta");
				numero = in.nextInt();
				
				contas.deletar(numero);
				
				keyPress();
				break;
			
			case 6:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Saque \n\n");
				
				System.out.println("Digite o número da conta");
				numero = in.nextInt();
				
				do {
					System.out.println("Digite o valor do saque (R$): ");
					valor = in.nextFloat();
			
				}while(valor <= 0);
				
				contas.sacar(numero, valor);
				
				keyPress();
				break;
			
			case 7:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Depósito \n\n");
				
				System.out.println("Digite o número da conta: ");
				numero = in.nextInt();
				
				do {
					System.out.println("Digite o valor do depósito (R$): ");
					valor = in.nextFloat();
				}while(valor <= 0);
				
				contas.depositar(numero, valor);
				
				keyPress();
				break;
			
			case 8:
				
				System.out.println(Cores.TEXT_WHITE_BOLD + "Transferência entre Contas \n\n");
				
				System.out.println("Digite o Numero da Conta de Origem: ");
				numero = in.nextInt();
				
				System.out.println("Digite o Numero da Conta de Destino: ");
				numeroDestino = in.nextInt();
				
				do {
					System.out.println("Digite o valor da Transferência (R$): ");
					valor = in.nextFloat();
					}while (valor <= 0);
				
				contas.transferir(numero, numeroDestino, valor);
				
				keyPress();
				break;
		
			default:
				System.out.println(Cores.TEXT_RED_BOLD +"\nOpção Inválida! \n\n");
				keyPress();
				break;

			}
		}
	}
		
	public static void keyPress() {

		try {
			System.out.println(Cores.TEXT_RESET + "\n Pressione Enter para continuar ... ");
			System.in.read();

		} catch (IOException e) {
			System.out.println("voce pressionou uma tecla uma tecla diferente de enter!");
		}

	}
	}


