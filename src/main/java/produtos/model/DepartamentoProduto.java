package produtos.model;

public enum DepartamentoProduto {

	CAMAMSESAEBANHO("Cama, Mesa e Banho"), CONSTRUCAO("Construção"), HIGIENE("Higiene"),
	LIMPEZA("Limpeza"), ALIMENTO("Alimento"), ELETRONICO("Eletrônico");

	private String descricao;

	DepartamentoProduto(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
