package com.food.planner.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="recipe")
public class Recipe {
	
	@Id  
    @GeneratedValue
	Long id;
	
	@Column  
	private String name;
	
    @OneToMany(cascade = CascadeType.ALL)
	private List<Item> items;
}
