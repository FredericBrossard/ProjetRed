package co.projet.filrouge.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/foodsgroup") 
public class GroupController {

	
	@Inject
 	GroupService groupService;

	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Long create(@RequestBody FoodsGroup r) {
		//System.out.println(resource);
	    return groupService.saveGroup(r).getId();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<FoodsGroup> findAll() {
		return groupService.getAll();
	}
 	
	
}
