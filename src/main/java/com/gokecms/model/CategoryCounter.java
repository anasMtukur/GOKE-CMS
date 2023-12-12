package com.gokecms.model;

import com.gokecms.app.CategoryName;

public class CategoryCounter {
	private CategoryName category;
	private long count;

	public CategoryCounter(CategoryName category, long count) {
		this.category = category;
		this.count = count;
	}

	public CategoryName getCategory() {
		return category;
	}

	public void setCategory(CategoryName category) {
		this.category = category;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
}
