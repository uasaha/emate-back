package me.emate.mateback.category.dummy;

import me.emate.mateback.category.entity.Category;

public class CategoryDummy {

  public static Category dummy() {
    return new Category(10, "dummy", false);
  }
}
