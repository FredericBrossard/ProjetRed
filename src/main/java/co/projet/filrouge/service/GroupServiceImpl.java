package co.projet.filrouge.service;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

 import co.projet.filrouge.model.FoodsGroup;
 import co.projet.filrouge.repository.GroupRepository;

@Named
public class GroupServiceImpl implements GroupService {

	@Inject
    GroupRepository groupRepository;

 	public FoodsGroup saveGroup(FoodsGroup g) {
    	groupRepository.save(g);
    	return g;
    }

    public List<FoodsGroup> getAll() {
    	return groupRepository.findAll();
    }
    public Optional<FoodsGroup> findById(Long id) {
	  return groupRepository.findById(id);
    }
    
    public void deleteById(Long id) {
  	  groupRepository.deleteById(id);
      }
    
    public FoodsGroup findByName(String name) {
	  return groupRepository.findByName(name);
	 }
	
}
