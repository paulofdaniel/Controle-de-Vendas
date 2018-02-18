package cadastro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import dados.Produto;
import erros.SisVendasException;

public class ListaDeProdutos {

public static HashMap<Integer,Produto> cadProdutos = new HashMap<Integer,Produto>();

	
	public static void incluirProduto(Produto objProduto){

		cadProdutos.put(objProduto.getCodigo(), objProduto);		
	}
	
	
	public static void excluirProduto(Produto objProduto){
		
		cadProdutos.remove(objProduto.getCodigo());		
	}
	
	
	public static Produto buscarProdutoPorCodigo(int codigo) throws SisVendasException{

		
		if(cadProdutos.containsKey(codigo)) {
			
			return cadProdutos.get(codigo);			
			
		}else{
			
			throw new SisVendasException("Não existe produto para este código.");
		}
	}
	
	public static void passaUltimoCodigo(int novoCod){
		
		Produto.setCod(novoCod);		
	}
	
	
	public static ArrayList<Produto> buscarProdutoPorNome(String nome) throws SisVendasException{
		
		ArrayList<Produto> resultadoBusca = new ArrayList<Produto>();
		
		for(Produto objProduto : cadProdutos.values()){
			
			if(objProduto.getNome().contains(nome)){	
				
				resultadoBusca.add(objProduto);				
			}			
		}
		
		if(resultadoBusca.isEmpty()){
			
			throw new SisVendasException("Não existe nenhum produto para o nome.");
			
		}else{			
			Collections.sort(resultadoBusca, ORDENA_PRODUTOS);
			return resultadoBusca;

		}		
	}
	
	public static final Comparator<Produto> ORDENA_PRODUTOS = new Comparator<Produto>() {
		public int compare(Produto e1, Produto e2) {
			return e1.getNome().compareTo(e2.getNome());
		}
	};		
}
