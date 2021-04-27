package produtos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import produtos.model.Produto;
import produtos.repository.ProdutoFilter;
import produtos.repository.Produtos;

@Service
public class CadastroProdutoService {

	@Autowired
	private Produtos produtos;

	public void salvar(Produto produto) {
		try {
			produtos.save(produto);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido");
		}
	}

	public void excluir(Long codigo) {
		produtos.deleteById(codigo);

	}

	public List<Produto> filtrar(ProdutoFilter filtro) {

		String descricao = filtro.getDescricao() == null ? "" : filtro.getDescricao();
		return produtos.findByDescricaoContaining(descricao);

	}

}
