package com.lcp.projetos.controller;


import javax.validation.Valid;

import com.lcp.projetos.entities.Visita;
import com.lcp.projetos.services.MocidadeService;
import com.lcp.projetos.services.VisitaService;
import com.lcp.projetos.services.AuxiliarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("visita")
public class VisitaController {

	@Autowired
	private VisitaService visitaService;

	@Autowired
	private MocidadeService mocidadeService;
		
	@Autowired
	private AuxiliarService auxiliarService;
	
	
	@RequestMapping(path = "novo")
	public ModelAndView novaVisita() {
		
		ModelAndView mv = new ModelAndView("visita/form.html");
		mv.addObject("visita", new Visita());
		
		return mv;
	}
	
	@RequestMapping(method =RequestMethod.POST, path = "novo")
	public ModelAndView salvarVisita(@Valid Visita visita, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("visita/form.html");
		
		if(bindingResult.hasErrors()) {
			mv.addObject("visita", visita);
			return mv;
		}
		
		Visita visitaSalva = visitaService.salvarVisita(visita);
		
		
		if(visita.getId() == null) {
			mv.addObject("visita", new Visita());
		} else {
			mv.addObject("visita", visitaSalva);
		}
		
		mv.addObject("mensagem", "visita salva com sucesso");
		
		return mv;
	}
	
	
	@RequestMapping(path = "editar")
	public ModelAndView editarVisita(@RequestParam(required = false) Long id) {

		ModelAndView mv = new ModelAndView("visita/form.html");

		Visita visita;

		if (id == null) {
			visita = new Visita();
		} else {
			try {
				visita = visitaService.obterVisita(id);
			} catch (Exception e) {
				visita = new Visita();
				mv.addObject("mensagem", e.getMessage());
			}
		}

		mv.addObject("visita", visita);
		mv.addObject("listaMocidade", mocidadeService.listarTodosMocidade());
		mv.addObject("listaAuxiliar", auxiliarService.listarTodasAuxiliar());
		


		return mv;

	}
	
	
	@RequestMapping
	public ModelAndView listarVisita(String nome, String mocidade) {
	
		ModelAndView mv = new ModelAndView("visita/listar.html");
		mv.addObject("lista", visitaService.listarVisita(nome, mocidade));
		mv.addObject("nome", nome);
		mv.addObject("mocidade", mocidade);
		
		return mv;
		
	}
	
	
	
	@RequestMapping("/excluir")
	public ModelAndView excluirVisita(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/visita");
		
		try {
			visitaService.excluirVisita(id);
			redirectAttributes.addFlashAttribute("mensagem", "visita excluída com sucesso");
		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir visita" + e.getMessage());
		}
				
		return mv;
	}
	

}
