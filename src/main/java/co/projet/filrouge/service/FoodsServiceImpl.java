package co.projet.filrouge.service;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import co.projet.filrouge.model.Foods;
import co.projet.filrouge.repository.FoodsRepository;
  
@Named
public class FoodsServiceImpl implements FoodsService {

	 @Inject
	    FoodsRepository foodsRepository;
	
	 	public Foods saveFoods(Foods f) {
	    	foodsRepository.save(f);
	    	return f;
	    }

	    public List<Foods> getAll() {
	    	return foodsRepository.findAll();
	    }
	    public Optional<Foods> findbyId(Long id) {
		  return foodsRepository.findById(id);
	    }
	    
	    public void deleteById(Long id) {
	  	  foodsRepository.deleteById(id);
	      }
	    
	    public List<Foods> findByName(String name) {
		  	  return foodsRepository.findByName(name);
		      }
	    	   
	 	public List<Foods> findByNameContaining(String name){
	 		return foodsRepository.findByNameContaining(name);
	 	}
}

