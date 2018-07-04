package co.projet.filrouge.service;

import java.util.List;
import java.util.Optional;

import javax.inject.Named;

import co.projet.filrouge.model.Foods;
 
@Named
public interface FoodsService {
	public Foods saveFoods(Foods f);
	public List<Foods> getAll();
	public Optional<Foods> findbyId(Long id);
 	public List<Foods> findByName(String name);
 	public List<Foods> findByNameContaining(String name);

	public void deleteById(Long id);
}

