package co.projet.filrouge.service;

import java.util.List;
import java.util.Optional;

import javax.inject.Named;

import co.projet.filrouge.model.FoodsGroup;

@Named
public interface GroupService {
	public FoodsGroup saveGroup(FoodsGroup f);
	public List<FoodsGroup> getAll();
	public Optional<FoodsGroup> findById(Long id);
 	public FoodsGroup findByName(String name);
	public void deleteById(Long id);
}
