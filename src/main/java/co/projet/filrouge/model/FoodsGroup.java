package co.projet.filrouge.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "FOODSGROUP")
public class FoodsGroup {

	

	@Id
 	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "foodsgroup_generator")
 	@SequenceGenerator(name="foodsgroup_generator", sequenceName = "foodsgroup_seq", allocationSize=1)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME", nullable=false)
	private String name;
	
	public FoodsGroup() {
	}

	public FoodsGroup(Long id) {
 		this.id = id;
 	}
	
	public FoodsGroup(String name) {
 		this.name = name;
	}

	public FoodsGroup(Long id,String name) {
		this.id = id;
 		this.name = name;
	}
	
	//@OneToMany(cascade=CascadeType.ALL)
	//@JoinColumn(name="group_id")
	//private Set<Foods> f;
	
	@OneToMany(mappedBy = "foodsGroup", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Foods> foods;
	
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "FoodsGroup [id= " + id + ", name= " + name + ", lg= " + foods.size() + "]";
	}

	
	
	
}
