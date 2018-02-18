package cadastro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.GregorianCalendar;

import dados.Cliente;
import dados.EstatisticaDeVendas;
import dados.Venda;
import erros.SisVendasException;

public class ListaDeVendas {

public static HashMap<Integer,Venda> regVendas = new HashMap<Integer,Venda>();

	
	public static void incluirVenda(Venda objVenda){
		
		regVendas.put(objVenda.getNumVenda(), objVenda);		
	}
	
	
	public static void excluirVenda(Venda objVenda){
		
		regVendas.remove(objVenda.getNumVenda());		
	}
	
	
	public static Venda buscarVendaPorCodigo(int numero) throws SisVendasException{
		
		if(regVendas.containsKey(numero)) {
			
			return regVendas.get(numero);			
			
		}else{
			
			throw new SisVendasException("Não existe venda para este número.");
		}
	}	
	
	public static void passaUltimoCodigo(int novoCod){
		
		Venda.setCod(novoCod);		
	}
	
	public static ArrayList<Venda> buscarVendasPorCliente(Cliente objCliente) throws SisVendasException{
		
		ArrayList<Venda> listaVendas = new ArrayList<Venda>();
		
		for(Venda objVenda : regVendas.values()){
			
			if(objVenda.getCliente().equals(objCliente)){
				
				listaVendas.add(objVenda);
				
			}			
		}
		
		if(listaVendas.isEmpty()){
			
			throw new SisVendasException("Não existe nenhuma venda para este cliente.");
			
		}else{		
			Collections.sort(listaVendas, ORDENA_VENDAS_DATA);
			return listaVendas;
		}			
	}
	
	
	public static ArrayList<Venda> vendasDoPeriodo(GregorianCalendar dataInicial, GregorianCalendar dataFinal) throws SisVendasException{
		
		ArrayList<Venda> listaVendas = new ArrayList<Venda>();
		
		
		for(Venda objVenda : regVendas.values()){
			
			if((objVenda.getDataVenda().compareTo(dataInicial) >= 0 && objVenda.getDataVenda().compareTo(dataFinal) <= 0)){
				
				listaVendas.add(objVenda);				
			}			
		}
		
		if(listaVendas.isEmpty()){
			
			throw new SisVendasException("Não existe nenhuma venda para este período.");
			
		}else{	
			
			Collections.sort(listaVendas, ORDENA_VENDAS_DATA);
			Collections.sort(listaVendas, ORDENA_VENDAS_CLIENTE);			
			return listaVendas;
		}	
	}
	
	
	public static ArrayList<EstatisticaDeVendas> estatisticaDeVendasDoPeriodo(GregorianCalendar dataInicial, GregorianCalendar dataFinal) throws SisVendasException{
		
		
		HashMap<Integer, Venda> vendasDoPeriodo = new HashMap<Integer,Venda>();
		
		for(Venda objVenda : regVendas.values()){
			
			if((objVenda.getDataVenda().compareTo(dataInicial) >= 0 && objVenda.getDataVenda().compareTo(dataFinal) <= 0)){
				
				vendasDoPeriodo.put(objVenda.getNumVenda(), objVenda);				
			}			
		}
		
		HashMap<String,EstatisticaDeVendas> resultadoEstatistica = new HashMap<String,EstatisticaDeVendas>();
		
		for(Venda objVenda : vendasDoPeriodo.values()){
			
			if(resultadoEstatistica.containsKey(objVenda.getCliente().getCpf())){
				
				EstatisticaDeVendas objEsta = resultadoEstatistica.get(objVenda.getCliente().getCpf());
				
				String cpf = objEsta.getCpf();
				String nome = objEsta.getNome();
				int numeroCompras = objEsta.getNumVendas() + 1;
				Double valorVendas = objEsta.getValorVendas() + objVenda.getTotalVenda();
				
				objEsta = new EstatisticaDeVendas(cpf, nome, numeroCompras, valorVendas);
				
				resultadoEstatistica.put(objEsta.getCpf(), objEsta);
				
				
			}else{				
				String cpf = objVenda.getCliente().getCpf();
				String nome = objVenda.getCliente().getNome();
				int numeroCompras = 1;
				Double valorCompras = objVenda.getTotalVenda();
				
				EstatisticaDeVendas objEsta = new EstatisticaDeVendas(cpf, nome, numeroCompras, valorCompras);
				
				resultadoEstatistica.put(objEsta.getCpf(), objEsta);				
			}			
		}
		
		ArrayList<EstatisticaDeVendas> resultado = new ArrayList<EstatisticaDeVendas>();
		
		for(Map.Entry<String, EstatisticaDeVendas> objEstatistica : resultadoEstatistica.entrySet()){
			
			EstatisticaDeVendas objEstaTemp = objEstatistica.getValue();
			resultado.add(objEstaTemp);
			
		}
		
		Collections.sort(resultado, ORDENA_ESTATISTICA_CLIENTE);	
		return resultado;
				
	}	
	
	public static final Comparator<Venda> ORDENA_VENDAS_DATA = new Comparator<Venda>() {
		public int compare(Venda e1, Venda e2) {
			return e2.getDataVenda().compareTo(e1.getDataVenda());
		}
	};
	
	public static final Comparator<Venda> ORDENA_VENDAS_CLIENTE = new Comparator<Venda>() {
		public int compare(Venda e1, Venda e2) {
			return e1.getCliente().getNome().compareTo(e2.getCliente().getNome());
		}
	};
	public static final Comparator<EstatisticaDeVendas> ORDENA_ESTATISTICA_CLIENTE = new Comparator<EstatisticaDeVendas>() {
		public int compare(EstatisticaDeVendas e1, EstatisticaDeVendas e2) {
			return e1.getNome().compareTo(e2.getNome());
		}
	};

}











