package produtos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import produtos.model.Produto;

public interface Produtos extends JpaRepository<Produto, Long> {

	public List<Produto> findByDescricaoContaining(String descricao);
}
