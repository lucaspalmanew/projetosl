package com.lcp.projetos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lcp.projetos.entities.Auxiliar;
import com.lcp.projetos.services.AuxiliarService;
import com.lcp.projetos.services.VisitaService;
import com.lcp.projetos.services.MocidadeService;

@Controller
@RequestMapping("auxiliar")
public class AuxiliarController {
	
	@Autowired
	private AuxiliarService auxiliarService;
	
	@Autowired
	private MocidadeService mocidadeService;
	
	@Autowired
	private VisitaService visitaService;
		
	@RequestMapping(path = "editar")
	public ModelAndView editarAuxiliar(@RequestParam(required = false) Long id) {
		
		ModelAndView mv = new ModelAndView("auxiliar/form.html");
		
		Auxiliar auxiliar;
		
		if(id==null) {
			auxiliar = new Auxiliar();
		}else {
			try {
				auxiliar = auxiliarService.obterAuxiliar(id);
			}catch(Exception e) {
				auxiliar = new Auxiliar();
				mv.addObject("mensagem", e.getMessage());
			}
		}
		
		mv.addObject("auxiliar", auxiliar);
		mv.addObject("listaMocidade", mocidadeService.listarTodosMocidade());
		mv.addObject("listaVisita", visitaService.listarTodasVisita());
		
		
		return mv;
		
	}
	
	@RequestMapping(path = "novo")
	public ModelAndView novaAuxiliar() {
		
		ModelAndView mv = new ModelAndView("auxiliar/form.html");
		mv.addObject("auxiliar", new Auxiliar());
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST , path = "novo")
	public ModelAndView salvarAuxiliar(@Valid Auxiliar auxiliar, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("auxiliar/form.html");
		
		boolean novo = true;
		
		if(auxiliar.getId() != null) {
			novo = false;
		}
		
		if(bindingResult.hasErrors()) {
			mv.addObject("auxiliar", auxiliar);
			return mv;
		}
		
		auxiliarService.salvarAuxiliar(auxiliar);
	
		if(novo) {
			mv.addObject("auxiliar", new Auxiliar());
		}else {
			mv.addObject("auxiliar", auxiliar);
		}
		
		mv.addObject("mensagem", "Auxiliar salvo com sucesso");
		mv.addObject("listaMocidade", mocidadeService.listarTodosMocidade());
		mv.addObject("listaVisita", visitaService.listarTodasVisita());		
		
		return mv;
		
	}
	
	@RequestMapping
	public ModelAndView listarAuxiliar(String nome) {
		
		ModelAndView mv = new ModelAndView("auxiliar/listar.html");
		
		mv.addObject("lista", auxiliarService.listarAuxiliar(nome));
		
		return mv;
		
	}
	
	@RequestMapping("/excluir")
	public ModelAndView excluirAuxiliar(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/auxiliar");

		try {
			auxiliarService.excluirAuxiliar(id);
			 redirectAttributes.addFlashAttribute("mensagem", "Auxiliar excluído com sucesso.");
		}catch(Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir Auxiliar!"+e.getMessage());
		}
				
		return mv;
	}
}
