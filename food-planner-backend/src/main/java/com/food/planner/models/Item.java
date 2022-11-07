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
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private Integer cost;
	
	@Column
	private Integer quantity;
	
    @Enumerated(EnumType.ORDINAL)
	private ItemType type;
    
    //How many days once this purchase is done, null for non perishable
    @Column
    private Integer purchaseFrequency;
}
