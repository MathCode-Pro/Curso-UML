package com.matheusol.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.matheusol.cursomc.domain.Categoria;
import com.matheusol.cursomc.domain.Cidade;
import com.matheusol.cursomc.domain.Cliente;
import com.matheusol.cursomc.domain.Endereco;
import com.matheusol.cursomc.domain.Estado;
import com.matheusol.cursomc.domain.ItemPedido;
import com.matheusol.cursomc.domain.Pagamento;
import com.matheusol.cursomc.domain.PagamentoComBoleto;
import com.matheusol.cursomc.domain.PagamentoComCartao;
import com.matheusol.cursomc.domain.Pedido;
import com.matheusol.cursomc.domain.Produto;
import com.matheusol.cursomc.domain.enums.EstadoPagamento;
import com.matheusol.cursomc.domain.enums.TipoCliente;
import com.matheusol.cursomc.repositories.CategoriaRepository;
import com.matheusol.cursomc.repositories.CidadeRepository;
import com.matheusol.cursomc.repositories.ClienteRepository;
import com.matheusol.cursomc.repositories.EnderecoRepository;
import com.matheusol.cursomc.repositories.EstadoRepository;
import com.matheusol.cursomc.repositories.ItemPedidoRepository;
import com.matheusol.cursomc.repositories.PagamentoRepository;
import com.matheusol.cursomc.repositories.PedidoRepository;
import com.matheusol.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoMcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursoMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(0, "Informática");
		Categoria cat2 = new Categoria(0, "Escritório");

		Produto p1 = new Produto(0, "Computador", 2000.00);
		Produto p2 = new Produto(0, "Impressora", 800.00);
		Produto p3 = new Produto(0, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		Estado est1 = new Estado(0, "Minas Gerais");
		Estado est2 = new Estado(0, "São Paulo");

		Cidade c1 = new Cidade(0, "Uberlândia", est1);
		Cidade c2 = new Cidade(0, "São Paulo", est2);
		Cidade c3 = new Cidade(0, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		Cliente cli1 = new Cliente(0, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(0, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(0, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		Pedido ped1 = new Pedido(0, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(0, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(0, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(0, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItems().addAll(Arrays.asList(ip1, ip2));
		ped2.getItems().addAll(Arrays.asList(ip3));

		p1.getItems().addAll(Arrays.asList(ip1));
		p2.getItems().addAll(Arrays.asList(ip3));
		p3.getItems().addAll(Arrays.asList(ip2));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
