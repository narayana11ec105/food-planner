package com.food.planner.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="meal")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Meal {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private Long recipeId;
	
    @Enumerated(EnumType.ORDINAL)
	private MealType mealType;
}
