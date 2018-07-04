package co.projet.filrouge.repository;

import java.util.List;

import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;

import co.projet.filrouge.model.Foods;

@Named 
public interface FoodsRepository extends JpaRepository<Foods, Long> {
 	public List<Foods> findByName(String name);
 	public List<Foods> findByNameContaining(String name);

}
