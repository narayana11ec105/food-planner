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
	DAIRY{
		@Override
		public boolean isPerishable() {
			return false;
		}
	},
	GRAIN{
		@Override
		public boolean isPerishable() {
			return false;
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