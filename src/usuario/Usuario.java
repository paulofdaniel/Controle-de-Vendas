package usuario;

import dados.Cliente;
import dados.EstatisticaDeVendas;
import dados.Produto;
import dados.Venda;
import dados.ItemVenda;
import erros.SisVendasException;
import utilitarios.Console;
import utilitarios.LtpUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

import cadastro.ListaDeClientes;
import cadastro.ListaDeProdutos;
import cadastro.ListaDeVendas;

public class Usuario {

		
	//Gravação de arquivo
	public static void main(String[] args) throws SisVendasException {
		
		int flag;
		
		if (new File("CLIENTES.OBJ").exists() && new File("PRODUTOS.OBJ").exists() && new File("VENDAS.OBJ").exists()) {
			lerArquivo();
		}
		
		do{		
			flag = menu();			
		}while(flag == 1);
		
		
		gravarArquivo();
		System.out.println("Programa Finalizado.\n\n");
		
		System.exit(0);
	}
	
	
	//Menu principal
	private static int menu() throws SisVendasException{				
		int opcao = -1;
		
		do{		
			                    
			System.out.println("################################## MENU PRINCIPAL ##################################"
					  +"\n\n 1 - Clientes"
					  +"\n 2 - Produtos"
					  +"\n 3 - Vendas"
					  +"\n 0 - Sair"
					  );
			
			opcao = Console.readInt("\nSelecione uma das opções acima: ");
			
			switch(opcao){
				case 0: 					
					return 0;
				case 1:
					menuClientes();
					break;
				case 2:
					menuProdutos();
					break;
				case 3:
					menuVendas();
					break;
				default:
					System.out.println("\nOpção inválida.\n\n");
					opcao = -1;
			}
			
		}while(opcao == -1);

		return 1;
	}
	
	//Menu clientes
	private static void menuClientes() throws SisVendasException{
		
		int opcao = -1;
		
		do{						  
			System.out.println("\n-------------------------------------- Clientes ------------------------------------"
					  +"\n\n 1 - Adicionar novo cliente"
					  +"\n 2 - Alterar cliente"
					  +"\n 3 - Excluir cliente"
					  +"\n 4 - Consultar cliente por CPF"
					  +"\n 5 - Consultar cliente por nome"
					  +"\n 0 - < Voltar"
					  );
			
			opcao = Console.readInt("\nSelecione uma das opções acima: ");
			
			switch(opcao){
				case 0: 
					return;
				case 1: 
					incluirNovoCliente();								
					break;
				case 2:
					alterarCliente();	
					break;
				case 3: 
					excluirCliente();
					break;
				case 4: 
					consultarClientePorCPF();
					break;
				case 5: 
					consultarClientePeloNome();
					break;
				default:
					System.out.println("\nOpção inválida.\n\n");
					opcao = -1;
			}
			
		}while(opcao == -1);
	}
	
	//Menu produtos
	private static void menuProdutos() throws SisVendasException{

		
		int opcao = -1;
		
		do{						  
			System.out.println("\n-------------------------------------- Produtos ------------------------------------"					
					  +"\n\n 1 - Adicionar novo produto"
					  +"\n 2 - Alterar produto"
					  +"\n 3 - Excluir produto"
					  +"\n 4 - Consultar produto pelo nome"
					  +"\n 0 - < Voltar"
					  );
			
			opcao = Console.readInt("\nSelecione uma das opções acima: ");
			
			switch(opcao){
				case 0: 
					return;
				case 1: 
					incluirNovoProduto();								
					break;
				case 2:
					alterarProduto();
					break;
				case 3: 
					excluirProduto();
					break;
				case 4: 
					consultarProdutoPeloNome();
					break;
				default:
					System.out.println("\nOpção inválida.\n\n");
					opcao = -1;
			}
			
		}while(opcao == -1);
	}	

	//Menu vendas
	private static void menuVendas() throws SisVendasException{

	
		int opcao = -1;
		
		do{						  
			System.out.println("\n-------------------------------------- Vendas --------------------------------------"					
					  +"\n\n 1 - Adicionar nova venda"
					  +"\n 2 - Excluir venda"
					  +"\n 3 - Consultar vendas por período"
					  +"\n 4 - Consultar estatística de vendas por cliente"
					  +"\n 0 - < Voltar"
					  );
			
			opcao = Console.readInt("\nSelecione uma das opções acima: ");
			
			switch(opcao){
				case 0: 
					return;
				case 1: 
					incluirVenda();								
					break;
				case 2:
					excluirVenda();
					break;
				case 3: 
					vendasDoPeriodo();
					break;
				case 4: 
					estatisticaDeVendas();
					break;
				default:
					System.out.println("\nOpção inválida.\n\n");
					opcao = -1;
			}
			
		}while(opcao == -1);
	}
		
	
	//Leitura de arquivos
	private static void lerArquivo() throws SisVendasException{
		
		try {
			ObjectInputStream inp = new ObjectInputStream(new FileInputStream("CLIENTES.OBJ"));
			ListaDeClientes.cadClientes = (HashMap<String,Cliente>)inp.readObject();
			inp.close();

			ObjectInputStream inp2 = new ObjectInputStream(new FileInputStream("PRODUTOS.OBJ"));
			ListaDeProdutos.cadProdutos = (HashMap<Integer,Produto>)inp2.readObject();
			inp2.close();
			
			int ultimaPosicaoProduto = ListaDeProdutos.cadProdutos.size() + 1;			
			ListaDeProdutos.passaUltimoCodigo(ultimaPosicaoProduto);
			
			ObjectInputStream inp3 = new ObjectInputStream(new FileInputStream("VENDAS.OBJ"));
			ListaDeVendas.regVendas = (HashMap<Integer,Venda>)inp3.readObject();
			inp3.close();
			
			int ultimaPosicaoVenda = ListaDeVendas.regVendas.size() + 1;
			ListaDeVendas.passaUltimoCodigo(ultimaPosicaoVenda);

			System.out.println(" > Arquivo de dados carregado com sucesso!\n");
			
		} catch (Exception e) {			
			System.out.println(e.getMessage());
		}
		
	}
	
	//Gravação de arquivos
	private static void gravarArquivo() throws SisVendasException{
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("CLIENTES.OBJ"));
			out.writeObject(ListaDeClientes.cadClientes);
			out.close();
	
			ObjectOutputStream out2 = new ObjectOutputStream(new FileOutputStream("PRODUTOS.OBJ"));
			out2.writeObject(ListaDeProdutos.cadProdutos);
			out2.close();
			
			ObjectOutputStream out3 = new ObjectOutputStream(new FileOutputStream("VENDAS.OBJ"));
			out3.writeObject(ListaDeVendas.regVendas);
			out3.close();
	
			System.out.println("\n > Arquivo de dados gravado com sucesso!\n");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	//Inclusão de novo cliente
	private static void incluirNovoCliente() throws SisVendasException{
		
		String cpf = "",
			   nome = "",
			   telefone = "",
			   email ="",
			   opcao;
		
		GregorianCalendar data = new GregorianCalendar();
		
		
		System.out.println("\n-----Cadastro de Cliente-----");
		
		while(true){			
			cpf = Console.readLine("\nEntre com o CPF:").trim();
			if(!LtpUtil.validarCPF(cpf)){
				System.out.println("CPF inválido. Tente novamente\n");
				continue;
			}
			
			try{
				Cliente objCliente = ListaDeClientes.buscarClientePorCPF(cpf);
				System.out.println("O seguinte cliente já possui o CPF digitado:\n" );
				System.out.println(objCliente.toString());
				
			}catch (SisVendasException e){
				break;
			}			
		};
	
		
		while(true){			
			nome = Console.readLine("\nEntre com o nome:").trim();
			if(LtpUtil.contarPalavras(nome) < 2){
				System.out.println("Nome inválido. Tente novamente\n");				
			}else break;
		};
			
		
		while(true){
			telefone = Console.readLine("\nEntre com o telefone:").trim();
			if(LtpUtil.contarPalavras(telefone) <= 0){
				System.out.println("Telefone inválido. Tente novamente\n");
			}else break;
		};
		
		while(true){
			email = Console.readLine("\nEntre com o e-mail").trim();
			if(!LtpUtil.validarEmail(email)){
				System.out.println("E-mail inválido. Tente novamente\n");
			}else break;		
		};
		
		Cliente objCliente = new Cliente(cpf, nome.trim(), telefone, email, data, data);

		while(true){
			System.out.println("\n" + objCliente.toString());
			opcao = Console.readLine("Confirma inclusão do usuário com os dados acima? (S - Sim / N - Não)").trim();
			if(opcao.equalsIgnoreCase("S")){
				ListaDeClientes.incluirCliente(objCliente);
				System.out.println("\n > Cliente adicionado com sucesso!\n\n");
				gravarArquivo();
				return;
			}else if(opcao.equalsIgnoreCase("N")){
				return;
			}
		}	
	}
	
	//Alteração de dados de cliente
	private static void alterarCliente() throws SisVendasException{
		
		String cpf = "",
			   nome = "",
			   telefone = "",
			   email ="",
			   opcao;
			
		GregorianCalendar data = new GregorianCalendar();
		GregorianCalendar dataInclusao;
		
		Cliente objCliente = null;
		
		System.out.println("\n-----Alterar Cliente-----");		
	
		do{
			while(true){			
				cpf = Console.readLine("\nEntre com o CPF:").trim();
				if(!LtpUtil.validarCPF(cpf)){
					System.out.println("CPF inválido. Tente novamente\n");
					continue;
				}else break;
			}
			try{
				objCliente = ListaDeClientes.buscarClientePorCPF(cpf);
				System.out.println("\nCliente encontrado:" );
				System.out.println(objCliente.toString());
				break;
				
			}catch (SisVendasException e){
				System.out.println(e.getMessage());
			}
		}while(objCliente == null);	

		
		while(true){
			opcao = Console.readLine("Deseja alterar os dados do cliente acima? (S - Sim / N - Não)").trim();
			
			if(opcao.equalsIgnoreCase("S")){
				
				while(true){			
					nome = Console.readLine("\nEntre com o nome:").trim();
					if(LtpUtil.contarPalavras(nome) < 2){
						System.out.println("Nome inválido. Tente novamente\n");				
					}else break;
				};						
				
				while(true){
					telefone = Console.readLine("\nEntre com o telefone:").trim();
					if(LtpUtil.contarPalavras(telefone) <= 0){
						System.out.println("Telefone inválido. Tente novamente\n");
					}else break;
				};
				
				while(true){
					email = Console.readLine("\nEntre com o e-mail").trim();
					if(!LtpUtil.validarEmail(email)){
						System.out.println("E-mail inválido. Tente novamente\n");
					}else break;
				};
				
				dataInclusao = objCliente.getDataInclusao();
				
				Cliente objClienteNovo = new Cliente(cpf, nome.trim(), telefone, email, dataInclusao, data);	

				while(true){
					System.out.println("\nDados antigos:\n" + objCliente.toString());
					System.out.println("Novos dados:\n" + objClienteNovo.toString());
					opcao = Console.readLine("Confirma alteração dos dados acima? (S - Sim / N - Não)").trim();
					
					if(opcao.equalsIgnoreCase("S")){
						ListaDeClientes.incluirCliente(objClienteNovo);
						System.out.println("\n > Cliente alterado com sucesso!\n\n");
						gravarArquivo();
						return;
					}else if(opcao.equalsIgnoreCase("N")){
						break;
					}else{
						continue;
					}
				}
				break;
					
			}else if(opcao.equalsIgnoreCase("N")){				
				return;
			}else{
				System.out.println("Opção inválida. Tente novamente\n");
				continue;
			}
		}		
	}
	
	
	//Exclusão de cliente
	private static void excluirCliente() throws SisVendasException{
		
		String cpf = "",
			   opcao;
		
		Cliente objCliente = null;
		
		System.out.println("\n-----Excluir Cliente-----");		
	
		do{
			while(true){			
				cpf = Console.readLine("\nEntre com o CPF:").trim();
				if(!LtpUtil.validarCPF(cpf)){
					System.out.println("CPF inválido. Tente novamente\n");
					continue;
				}else break;
			}
			try{
				objCliente = ListaDeClientes.buscarClientePorCPF(cpf);
				System.out.println("Cliente encontrado:\n" );
				System.out.println(objCliente.toString());
				break;
				
			}catch (SisVendasException e){
				System.out.println(e.getMessage());
			}
		}while(objCliente == null);	
				
		if(ListaDeVendas.regVendas.containsValue(objCliente)){
			System.out.println("Existem vendas cadastradas para este cliente, logo ele não pode ser excluído.");
			return;
		}

		while(true){
			opcao = Console.readLine("Deseja excluir o cliente PERMANENTEMENTE? (S - Sim / N - Não)").trim();
			
			if(opcao.equalsIgnoreCase("S")){			

				ListaDeClientes.excluirCliente(objCliente);
				System.out.println("\n > Cliente excluído com sucesso!");
				gravarArquivo();
				return;
					
			}else if(opcao.equalsIgnoreCase("N")){				
				return;
			}else{
				System.out.println("Opção inválida. Tente novamente\n");
				continue;
			}
		}		
	}
	
	//Busca de cliente por nome
	private static void consultarClientePeloNome() throws SisVendasException{
		
		String nome = "";

		ArrayList<Cliente> resposta;
		
		System.out.println("\n-----Consultar Cliente Pelo Nome-----");		
	
		while(true){			
			nome = Console.readLine("\nEntre com o nome:").trim();
			if(LtpUtil.contarPalavras(nome) < 1){
				System.out.println("É necessário digitar um nome ou parte dele. Tente novamente\n");				
			}else break;
		};

			try{
				resposta = ListaDeClientes.buscarClientePorNome(nome);
				System.out.println("\n\n");
				
				for(Cliente objCliente : resposta){
					System.out.println(objCliente.toString());
				}
				
			}catch (SisVendasException e){
				System.out.println("\n" + e.getMessage());
			}
		
	}
	
	//Busca de cliente por CPF
	private static void consultarClientePorCPF() throws SisVendasException{
		
		String cpf = "";
			
			Cliente objCliente = null;
			
			System.out.println("\n-----Consultar Cliente por CPF-----");		
		
			do{
				while(true){			
					cpf = Console.readLine("\nEntre com o CPF:").trim();
					if(!LtpUtil.validarCPF(cpf)){
						System.out.println("CPF inválido. Tente novamente\n");
						continue;
					}else break;
				}
				try{
					objCliente = ListaDeClientes.buscarClientePorCPF(cpf);
					System.out.println("Cliente encontrado:\n" );
					System.out.println(objCliente.toString());
					return;
					
				}catch (SisVendasException e){
					System.out.println("\n" + e.getMessage());
				}
			}while(objCliente == null);						
		
	}
	
	//Incluir produtos
	private static void incluirNovoProduto() throws SisVendasException{
		
		String nome = "";
		double preco = 0;		
		
		GregorianCalendar data = new GregorianCalendar();
		
		
		System.out.println("\n-----Cadastro de Produto-----");
		

		while(true){			
			nome = Console.readLine("\nEntre com o nome:").trim();
			if(LtpUtil.contarPalavras(nome) < 1){
				System.out.println("Nome inválido. Tente novamente\n");				
			}else break;
		};
			
		
		while(true){
			preco = Console.readDouble("\nEntre com o preço:");
			if(preco <= 0){
				System.out.println("O preço deve ser maior que zero. Tente novamente\n");
			}else break;
		};

		Produto objProduto = new Produto(nome, preco, data, data);
		ListaDeProdutos.incluirProduto(objProduto);
		
		System.out.println("\n > Produto adicionado com sucesso!\n");
		gravarArquivo();
		return;		
	}
	
	//Alteração de produto
	private static void alterarProduto() throws SisVendasException{
		
		String nome = "",
			   opcao;
		double preco = 0;
		int codigo;
		
		GregorianCalendar data = new GregorianCalendar();	
		
		Produto objProduto = null;
		Produto objProdutoNovo;
		
		System.out.println("\n-----Alterar Produto-----");
		
		do{
			while(true){			
				codigo = Console.readInt("\nEntre com o codigo do produto:");
				if(codigo < 0){
					System.out.println("Código inválido. Tente novamente\n");				
				}else break;
			}
			try{
				objProduto = ListaDeProdutos.buscarProdutoPorCodigo(codigo);
				System.out.println("Produto encontrado:\n" );
				System.out.println(objProduto.toString());
				break;
				
			}catch (SisVendasException e){
				System.out.println(e.getMessage());
			}
		}while(objProduto == null);	
		
		while(true){			
			nome = Console.readLine("\nEntre com o nome:").trim();
			if(LtpUtil.contarPalavras(nome) < 1){
				System.out.println("Nome inválido. Tente novamente\n");				
			}else break;
		};			
		
		while(true){
			preco = Console.readDouble("\nEntre com o preço:");
			if(preco <= 0){
				System.out.println("O preço deve ser maior que zero. Tente novamente\n");
			}else break;
		};
		
		objProdutoNovo = new Produto(nome, preco, data, data);
		
		while(true){
			System.out.println("\nDados antigos:\n" +objProduto.toString());
			System.out.println("Novos dados:\n" + objProdutoNovo.toString());
			opcao = Console.readLine("Confirma alteração dos dados acima? (S - Sim / N - Não)").trim();
			
			if(opcao.equalsIgnoreCase("S")){
				ListaDeProdutos.incluirProduto(objProduto);
				System.out.println("\n > Produto alterado com sucesso!\n\n");
				gravarArquivo();
				return;
			}else if(opcao.equalsIgnoreCase("N")){
				break;
			}else{
				continue;
			}
		}

	}
	
	
	//Exclusão de produtos
	private static void excluirProduto() throws SisVendasException{
		
		String opcao;
		int codigo;	
		
		Produto objProduto = null;
		
		System.out.println("\n-----Excluir Produto-----");
		
		do{
			while(true){			
				codigo = Console.readInt("\nEntre com o codigo do produto:");
				if(codigo < 0){
					System.out.println("Código inválido. Tente novamente\n");				
				}else break;
			}
			try{
				objProduto = ListaDeProdutos.buscarProdutoPorCodigo(codigo);
				System.out.println("Produto encontrado:\n" );
				System.out.println(objProduto.toString());
				break;
				
			}catch (SisVendasException e){
				System.out.println(e.getMessage());
			}
		}while(objProduto == null);	
		
		if(ListaDeVendas.regVendas.containsValue(objProduto)){
			System.out.println("Existem vendas cadastradas para este produto, logo ele não pode ser excluído.");
			return;
		}
		
		while(true){
			opcao = Console.readLine("\nConfirma exclusao PERMANENTE dos dados acima? (S - Sim / N - Não)").trim();
			
			if(opcao.equalsIgnoreCase("S")){
				ListaDeProdutos.excluirProduto(objProduto);
				System.out.println("\n > Produto excluído com sucesso!\n\n");
				gravarArquivo();
				return;
			}else if(opcao.equalsIgnoreCase("N")){
				break;
			}else{
				continue;
			}
		}

	}
	
	//Buscar produto por nome
	private static void consultarProdutoPeloNome() throws SisVendasException{
		
		String nome = "";

		ArrayList<Produto> resposta;
		
		System.out.println("\n-----Consultar Produto Pelo Nome-----");		
	
		while(true){			
			nome = Console.readLine("\nEntre com o nome:").trim();
			if(LtpUtil.contarPalavras(nome) < 1){
				System.out.println("É necessário digitar um nome ou parte dele. Tente novamente\n");				
			}else break;
		};

			try{
				resposta = ListaDeProdutos.buscarProdutoPorNome(nome);
				System.out.println("\n\n");
				
				for(Produto objProduto : resposta){
					System.out.println(objProduto.toString());
				}
				
			}catch (SisVendasException e){
				System.out.println("\n" + e.getMessage());
			}
		
	}
	
	//Inclusão de venda
	private static void incluirVenda() throws SisVendasException{
		
		String cpf = "";
		int contaProdutos = 1;
		double quantidade,
			   totalVenda = 0;
		
		Cliente objCliente = null;
		
		GregorianCalendar dataVenda = new GregorianCalendar();;
		
		ArrayList<ItemVenda> itensDaVenda = new ArrayList<>();
		
		System.out.println("\n-----Nova Venda-----");		
	
		do{
			while(true){			
				cpf = Console.readLine("\nEntre com o CPF do cliente:").trim();
				if(!LtpUtil.validarCPF(cpf)){
					System.out.println("CPF inválido. Tente novamente\n");
					continue;
				}else break;
			}
			try{
				objCliente = ListaDeClientes.buscarClientePorCPF(cpf);
				System.out.println("\nCliente encontrado:" );
				System.out.println(objCliente.toString());
				break;
				
			}catch (SisVendasException e){
				System.out.println(e.getMessage());
			}
		}while(objCliente == null);	
		
		while(true){
			String opcao = Console.readLine("Deseja realizar uma venda para o cliente acima? (S - Sim / N - Não)");
			
			if(opcao.equalsIgnoreCase("S")){
				
				while (true) {
					String venda = Console.readLine("\nData da venda (dd/mm/AAAA): ").trim();
					
					if ( !LtpUtil.validarData(venda, dataVenda)) {
						System.out.println("Data da venda inválida.");
						continue;
					}
					if (dataVenda.after(new GregorianCalendar())) {
						System.out.println("Data da venda superior a data de hoje. Tente novamente.\n");
					} else break;
				}
				
				while(true){					
					
					if(contaProdutos <= 1){
						opcao = Console.readLine("\nDeseja adicionar um produto? (S - Sim / N - Não)").trim();
					}else{
						opcao = Console.readLine("\nDeseja adicionar outro produto? (S - Sim / N - Não)").trim();
					}
					
					if(opcao.equalsIgnoreCase("S")){
						
						int codigo;	
						
						Produto objProduto = null;
						
						System.out.println("\nProduto #" + contaProdutos++);
						
						do{
							while(true){			
								codigo = Console.readInt("\nEntre com o codigo do produto (0 - Cancelar):");
								if(codigo == 0){
									System.out.println("Venda cancelada.\n");
									return;
								}else if(codigo < 0){
									System.out.println("Código inválido. Tente novamente\n");				
								}else break;
							}
								
							try{
								objProduto = ListaDeProdutos.buscarProdutoPorCodigo(codigo);
								System.out.println("\nProduto encontrado:" );
								System.out.println(objProduto.toString());								
								
							}catch (SisVendasException e){
								System.out.println(e.getMessage());
							}
							
							for(ItemVenda item : itensDaVenda){
								if(item.getProduto().equals(objProduto)){
									System.out.println("Este produto já foi incluído nesta compra. Tente novamente.\n");
									objProduto = null;
								}
							}
							
						}while(objProduto == null);		
						
						
						while(true){
							quantidade = Console.readDouble("Entre com a quantidade");							
							if(quantidade <= 0){
								System.out.println("A quantidade deve ser maior que zero. Tente novamente.\n");
							}else break;
						}
						
						totalVenda += quantidade*objProduto.getPrecoUnitario();
	
						ItemVenda objItemVenda = new ItemVenda(objProduto, objProduto.getPrecoUnitario(), quantidade, quantidade*objProduto.getPrecoUnitario());
						itensDaVenda.add(objItemVenda);	
						
						System.out.println("Produto \t\t\t\tVal Un R$ \t\tQtd \tVal Total R$");
						System.out.println(objItemVenda.toString());
						System.out.println("\nItem Adicionado com sucesso.\n");
						
					}else if(opcao.equalsIgnoreCase("N")){
						
						if(contaProdutos == 0){
							System.out.println("Venda cancelada.\n");
							return;
						}else{
							Venda novaVenda = new Venda(objCliente, dataVenda, itensDaVenda, totalVenda);							
							
							System.out.println("\nProduto \t\t\t\tVal Un R$ \t\tQtd \tVal Total R$");
							for(ItemVenda obj : itensDaVenda){
								System.out.println(obj.toString());
							}
							
							opcao = Console.readLine("\nConfirma inclusão da venda acima? (S - Sim / N - Não)").trim();
							
							if(opcao.equalsIgnoreCase("S")){
								
								ListaDeVendas.incluirVenda(novaVenda);
								System.out.println("Venda efetuada com sucesso!\n");
								gravarArquivo();
							}else if(opcao.equalsIgnoreCase("N")){
								System.out.println("Venda Cancelada.\n");
								return;
							}else continue;		
						}						
						return;
					}else continue;
					
				}
			}else if(opcao.equalsIgnoreCase("N")){
				return;
			}else continue;			
		}
		
	}
	
	//Exclusão de venda
	private static void excluirVenda() throws SisVendasException{
		
		String opcao;
		int codigo;	
		
		Venda objVenda = null;
		
		System.out.println("\n-----Excluir Venda-----");
		
		do{
			while(true){			
				codigo = Console.readInt("\nEntre com o codigo da venda (0 - Cancelar):");
				if(codigo == 0){
					System.out.println("\n > Busca cancelada.\n");
					return;
				}else if(codigo < 0){
					System.out.println("Código inválido. Tente novamente\n");				
				}else break;
			}
			try{
				objVenda = ListaDeVendas.buscarVendaPorCodigo(codigo);
				System.out.println("Venda encontrada:\n" );
				System.out.println(objVenda.toString().replace(",", "").replace("[", "").replace("]", ""));
				break;
				
			}catch (SisVendasException e){
				System.out.println(e.getMessage());
			}
		}while(objVenda == null);	
		
		while(true){
			opcao = Console.readLine("\nConfirma exclusão PERMANENTE da venda acima? (S - Sim / N - Não)").trim();
			
			if(opcao.equalsIgnoreCase("S")){
				ListaDeVendas.excluirVenda(objVenda);
				System.out.println("\n > Venda excluída com sucesso!\n\n");
				gravarArquivo();
				return;
			}else if(opcao.equalsIgnoreCase("N")){
				System.out.println("\n > Exclusão cancelada.\n" );
				return;
			}else{
				continue;
			}
		}		

	}
	
	//Busca de vendas em um período
	private static void vendasDoPeriodo() throws SisVendasException{
		
		GregorianCalendar dataInicial = new GregorianCalendar();
		GregorianCalendar dataFinal = new GregorianCalendar();
		
		ArrayList<Venda> listaVendas = new ArrayList<Venda>();
		
		while (true) {
			
			String dataIn = Console.readLine("\nData incial de pesquisa (dd/mm/AAAA): ").trim();
			
			if ( !LtpUtil.validarData(dataIn, dataInicial)) {
				System.out.println("Data da venda inválida.");
				continue;
			}else break;
		}
		
		while (true) {
			
			String dataFin = Console.readLine("\nData final de pesquisa (dd/mm/AAAA): ").trim();
			
			if ( !LtpUtil.validarData(dataFin, dataFinal)) {
				System.out.println("Data da venda inválida. Tente Novamente.");
				continue;
			}else if(dataFinal.before(dataInicial)){
				System.out.println("A data final é inferior a data inicial. Tente novamente.");
				continue;
			}else break;
		}
		
		try{
			
			listaVendas = ListaDeVendas.vendasDoPeriodo(dataInicial, dataFinal);
			
			System.out.println("Resultado:\n");		
			
			for(Venda objVenda : listaVendas){		
				System.out.println(objVenda.toString().replace(",", "").replace("[", "").replace("]", ""));	
			}						
			
		}catch (SisVendasException e){
			System.out.println(e.getMessage());
		}
		
		return;		
	}
	
	//Estatística de veendas
	private static void estatisticaDeVendas() throws SisVendasException{
		
		ArrayList<EstatisticaDeVendas> resultado;
		
		GregorianCalendar dataInicial = new GregorianCalendar();
		GregorianCalendar dataFinal = new GregorianCalendar();
		
		while (true) {
			
			String dataIn = Console.readLine("\nData incial de pesquisa (dd/mm/AAAA): ").trim();
			
			if ( !LtpUtil.validarData(dataIn, dataInicial)) {
				System.out.println("Data da venda inválida.");
				continue;
			}else break;
		}
		
		while (true) {
			
			String dataFin = Console.readLine("\nData final de pesquisa (dd/mm/AAAA): ").trim();
			
			if ( !LtpUtil.validarData(dataFin, dataFinal)) {
				System.out.println("Data da venda inválida. Tente Novamente.");
				continue;
			}else if(dataFinal.before(dataInicial)){
				System.out.println("A data final é inferior a data inicial. Tente novamente.");
				continue;
			}else break;
		}
		
		resultado = ListaDeVendas.estatisticaDeVendasDoPeriodo(dataInicial, dataFinal);	
		
		System.out.println("\n-----Estatística de vendas-----");
		
		if(resultado.isEmpty()){
			System.out.println("Não há vendas para o período selecionado.\n\n");
		}else{
			
			for(EstatisticaDeVendas objEstatistica : resultado){
				
				System.out.println(objEstatistica.toString());
				
			}			
		}	
	}
}
