package com.food.planner.models;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Groceries {
	private List<Item> items;
}
