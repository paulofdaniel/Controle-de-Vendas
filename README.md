# Controle-de-Vendas

Projeto final de Linguagens e Técnicas de Programação III da Universidade FUMEC. Consiste em um sistema para controle de vendas, feito em Java.


## Recursos aprendidos

### Programação Orientada a Objeto.
### Organização das classes em Dados, Controle e Usuário.
### Gravação e leitura em arquivos.
### Tratamento de exceções.
### Utilização de HashMap.

## Classes

Cliente

```
	public Cliente(String cpf, String nome, String telefone, String email,
			GregorianCalendar dataInclusao, GregorianCalendar dataUltAlteracao ) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.dataInclusao = dataInclusao;
		this.dataUltAlteracao = dataUltAlteracao;
        }
```

Produto

```
	public Produto(String nome, double precoUnitario,GregorianCalendar dataInclusao,
			GregorianCalendar dataUltAlteracao) {
		super();
		this.codigo = cod++;
		this.nome = nome;
		this.precoUnitario = precoUnitario;
		this.dataInclusao = dataInclusao;
		this.dataUltAlteracao = dataUltAlteracao;
        }
```

Venda
```
	public Venda(Cliente cliente, GregorianCalendar dataVenda, ArrayList<ItemVenda> vendaItens, double totalVenda) {
		super();
		this.numVenda = cod++;
		this.cliente = cliente;
		this.dataVenda = dataVenda;
		this.vendaItens = vendaItens;
		this.totalVenda = totalVenda;
	}
```


