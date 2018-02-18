package cadastro;

import dados.Cliente;
import erros.SisVendasException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 
 * @author Paulo
 *
 */
public class ListaDeClientes {

	public static HashMap<String,Cliente> cadClientes = new HashMap<String,Cliente>(); 
	
	public static void incluirCliente(Cliente objCliente){
		
		cadClientes.put(objCliente.getCpf(), objCliente);		
	}
	
	
	public static void excluirCliente(Cliente objCliente){
		
		cadClientes.remove(objCliente.getCpf());		
	}
	
	
	public static Cliente buscarClientePorCPF(String cpf) throws SisVendasException{

		
		if(cadClientes.containsKey(cpf)) {
			
			return cadClientes.get(cpf);			
			
		}else{
			
			throw new SisVendasException("Não existe cliente para este CPF.");
		}
	}
	
	
	public static ArrayList<Cliente> buscarClientePorNome(String nome) throws SisVendasException{
		
		ArrayList<Cliente> resultadoBusca = new ArrayList<Cliente>();
		
		for(Cliente objCliente : cadClientes.values()){
			
			if(objCliente.getNome().toUpperCase().contains(nome.toUpperCase())){	
				
				resultadoBusca.add(objCliente);				
			}			
		}
		
		if(resultadoBusca.isEmpty()){
			
			throw new SisVendasException("Não existe nenhum cliente para o nome.");
			
		}else{					
			Collections.sort(resultadoBusca, ORDENA_CLIENTES);
			return resultadoBusca;

		}			
	}
	
	public static final Comparator<Cliente> ORDENA_CLIENTES = new Comparator<Cliente>() {
		public int compare(Cliente e1, Cliente e2) {
			return e1.getNome().compareTo(e2.getNome());
		}
	};

}
