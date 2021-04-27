package produtos.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import produtos.model.DepartamentoProduto;
import produtos.model.Produto;
import produtos.repository.ProdutoFilter;
import produtos.repository.Produtos;
import produtos.service.CadastroProdutoService;

@Controller
@RequestMapping("/produtos")

public class ProdutoController {

	@Autowired
	private Produtos produtos;

	@Autowired
	private CadastroProdutoService cadastroProdutoService;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroProduto");
		mv.addObject(new Produto());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Produto produto, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return "CadastroProduto";
		}

		try {
			cadastroProdutoService.salvar(produto);
			attributes.addFlashAttribute("mensagem", "Produto salvo com sucesso!");

			return "redirect:/produtos/novo";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataNascimento", null, e.getMessage());
			return "CadastroProduto";
		}
	}

	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") ProdutoFilter filtro) {

		List<Produto> todosProdutos = cadastroProdutoService.filtrar(filtro);

		ModelAndView mv = new ModelAndView("PesquisaProdutos");
		mv.addObject("produtos", todosProdutos);
		return mv;
	}

	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Long codigoProduto) {
		Produto produto = produtos.getOne(codigoProduto);
		ModelAndView mv = new ModelAndView("CadastroProduto");
		mv.addObject(produto);
		return mv;
	}

	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		cadastroProdutoService.excluir(codigo);
		attributes.addFlashAttribute("mensagem", "Produto excluído com sucesso!");
		return "redirect:/produtos";
	}
	
	@ModelAttribute("todosDepartamentos")
	public List<DepartamentoProduto> todosDepartamentos() {
		return Arrays.asList(DepartamentoProduto.values());
	}
}