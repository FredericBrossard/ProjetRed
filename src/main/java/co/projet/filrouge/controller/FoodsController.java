package co.projet.filrouge.controller;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.Column;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import co.projet.filrouge.model.Foods;
import co.projet.filrouge.model.FoodsGroup;
import co.projet.filrouge.service.FoodsService;
import co.projet.filrouge.service.GroupService;
  
@Controller
@RequestMapping("/foods") 
public class FoodsController {
	@Inject
	FoodsService foodsService;
 
	
	
 	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Long create(@RequestBody Foods ressource) {
		//System.out.println(resource);
	    return foodsService.saveFoods(ressource).getId();
	}
	
	
 	@RequestMapping(method = RequestMethod.GET)
 	@CrossOrigin(origins = {"http://localhost:4200"})
	@ResponseBody
	public List<Foods> findAll() {
		return foodsService.getAll();
	}
 	
	
 



}
