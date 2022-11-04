package com.food.planner.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="meal_plan")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MealPlan {
	
	@Id
	@GeneratedValue
	@Column
	private Long id;
	
    @OneToMany(cascade = CascadeType.ALL)
	private List<Meal> meals;
	
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column
	private Date mealPlanDay;
}
