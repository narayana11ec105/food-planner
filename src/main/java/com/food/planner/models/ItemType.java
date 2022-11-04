package com.food.planner.models;

public enum ItemType {
	VEGETABLE{
		@Override
		public boolean isPerishable() {
			return true;
		}
	}, 
	FRUIT{
		@Override
		public boolean isPerishable() {
			return true;
		}
	}, 
	OTHER{
		@Override
		public boolean isPerishable() {
			return false;
		}
	};

	public boolean isPerishable() {
		return false;
	}
}
