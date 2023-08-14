package com.lcp.projetos.controller;

import javax.validation.Valid;

import com.lcp.projetos.entities.Mocidade;
import com.lcp.projetos.services.MocidadeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("mocidade")
public class MocidadeController {
	
	@Autowired
	private MocidadeService mocidadeService;
	

	
	
	@RequestMapping(path = "novo")
	public ModelAndView novaMocidade() {
		
		ModelAndView mv = new ModelAndView("mocidade/form.html");
		mv.addObject("mocidade", new Mocidade());
		
		return mv;
	}
	
	@RequestMapping(method =RequestMethod.POST, path = "novo")
	public ModelAndView salvarMocidade(@Valid Mocidade mocidade, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("mocidade/form.html");
		
		if(bindingResult.hasErrors()) {
			mv.addObject("mocidade", mocidade);
			return mv;
		}
		
		Mocidade mocidadeSalva = mocidadeService.salvarMocidade(mocidade);
		
		
		if(mocidade.getId() == null) {
			mv.addObject("mocidade", new Mocidade());
		} else {
			mv.addObject("mocidade", mocidadeSalva);
		}
		
		mv.addObject("mensagem", "mocidade salvo com sucesso");
		
		return mv;
	}
	
	@RequestMapping
	public ModelAndView listarTodosMocidade(String nomepesquisa, String mesNasc) {
	
		ModelAndView mv = new ModelAndView("mocidade/listar.html");
		mv.addObject("lista", mocidadeService.listarMocidade(nomepesquisa, mesNasc));
		mv.addObject("nomepesquisa", nomepesquisa);
		mv.addObject("mesNasc", mesNasc);
		
		return mv;
		
	}
	
	@RequestMapping("/editar")
	public ModelAndView editarMocidade(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView("mocidade/form.html");
		Mocidade mocidade;
		
		try {
			mocidade = mocidadeService.obterMocidade(id);
		} catch(Exception e) {
			mocidade = new Mocidade();
			mv.addObject("mensagem", e.getMessage());
		}
		
		mv.addObject("mocidade", mocidade);
		
		return mv;
	}
	
	@RequestMapping("/excluir")
	public ModelAndView excluirMocidade(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/mocidade");
		
		try {
			mocidadeService.excluirMocidade(id);
			redirectAttributes.addFlashAttribute("mensagem", "mocidade excluída com sucesso");
		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir mocidade" + e.getMessage());
		}
				
		return mv;
	}
	
	/*@PostMapping("/pesquisarmocidade")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {
		
		ModelAndView mv = new ModelAndView("/mocidade/listar.html");
		mv.addObject("lista", mocidadeService.listarmocidadePorNome(nomepesquisa));
		return mv;
	}*/
	

}
