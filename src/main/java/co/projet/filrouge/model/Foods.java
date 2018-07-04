package co.projet.filrouge.model;

import java.util.Calendar;
import java.util.TimeZone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullFields;
import org.springframework.lang.Nullable;


@Entity
@Table(name = "FOODS")
@NamedQueries({
	@NamedQuery(name = "Foods.findAll2", query = " SELECT f.foodsGroup.name, f.name FROM Foods f ORDER BY f.name desc ")})
	
	 

public class Foods {
	
	 
 		
		@Id
 		@GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "foods_generator")
	 	@SequenceGenerator(name="foods_generator", sequenceName = "foods_seq", allocationSize=1)
		@Column(name = "ID")
		private Long id;
		
		@Column(name = "NAME",nullable=false)
		private String name;
		
		//Option LAZY permet de ne pas créer de table de liaison 
		//entre food et foodgroup
  		@ManyToOne(fetch = FetchType.LAZY)
  		//@ManyToOne
		@JoinColumn(name = "foodsGroup")
		private FoodsGroup foodsGroup;
		
		@Column(name = "GLYCINDEX",nullable=false)
		private float glycIndex;
		
		@Column(name = "ENERGY",nullable=false)
		private float energy;
		
		@Column(name = "CARBOHYDRATES",nullable=false)
		private float carboHydrates;
		
		@Column(name = "PROTEINS",nullable=false)
		private float proteins;
		
		@Column(name = "LIPIDS",nullable=false)
		private float lipids;
		
		@Column(name = "COMMENT")
		private String comment;
		
		@Column(name = "CREATEDATE")
		@CreatedDate
		private final Calendar CreateDate = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		//private final Calendar CreateDate;
		
		public Foods() {
		}
		
		public Foods(long id) {
			this.id= id;
		}

		public Foods(String name) {
			this.name= name;
		}
		 
		
		public Foods(String name, FoodsGroup foodsGroup, float glycIndex, float energy, float carboHydrates,
				float proteins, float lipids, String comment) {
 			this.name = name;
			this.foodsGroup = foodsGroup;
			this.glycIndex = glycIndex;
			this.energy = energy;
			this.carboHydrates = carboHydrates;
			this.proteins = proteins;
			this.lipids = lipids;
			this.comment = comment;
		}

		 

		public Foods(Long id, String name, FoodsGroup foodsGroup, float glycIndex, float energy, float carboHydrates,
				float proteins, float lipids, String comment) {
			super();
			this.id = id;
			this.name = name;
			this.foodsGroup = foodsGroup;
			this.glycIndex = glycIndex;
			this.energy = energy;
			this.carboHydrates = carboHydrates;
			this.proteins = proteins;
			this.lipids = lipids;
			this.comment = comment;
		}

		public Long getId() {
			// TODO Auto-generated method stub
			return id;
		}
		public void setId(Long id) {
			this.id= id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name= name;
		}
		
		public FoodsGroup getFoodsGroup() {
			return this.foodsGroup;
		}

		public void setFoodsGroup(FoodsGroup foodsGroup) {
			this.foodsGroup = foodsGroup;
	}
		
		public float getGlycIndex() {
			return glycIndex;
		}

		public void setGlycIndex(float glycIndex) {
			this.glycIndex = glycIndex;
		}

		public float getEnergy() {
			return energy;
		}

		public void setEnergy(float energy) {
			this.energy = energy;
		}

		public float getCarboHydrates() {
			return carboHydrates;
		}

		public void setCarboHydrates(float carboHydrates) {
			this.carboHydrates = carboHydrates;
		}

		public float getProteins() {
			return proteins;
		}

		public void setProteins(float proteins) {
			this.proteins = proteins;
		}

		public float getLipids() {
			return lipids;
		}

		public void setLipids(float lipids) {
			this.lipids = lipids;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public Calendar getCreateDate() {
			return CreateDate;
		}

		@Override
		public String toString() {
			return "Foods [id=" + id + ", name=" + name + ", group=" + foodsGroup + ", glycIndex=" + glycIndex + ", energy="
					+ energy + ", carboHydrates=" + carboHydrates + ", proteins=" + proteins + ", lipids=" + lipids
					+ ", comment=" + comment + ", CreateDate=" + CreateDate + "]";
		}

		
			
	}

