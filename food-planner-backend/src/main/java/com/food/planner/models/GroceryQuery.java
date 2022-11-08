package com.food.planner.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroceryQuery {

	private Integer numberOfPersons;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private String startDate;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private String endDate;
}
