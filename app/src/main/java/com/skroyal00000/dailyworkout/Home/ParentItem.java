package com.skroyal00000.dailyworkout.Home;

import java.util.List;

public class ParentItem {

   String id, Title;

   public ParentItem() {
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getTitle() {
      return Title;
   }

   public void setTitle(String title) {
      Title = title;
   }

   public ParentItem(String id, String title) {
      this.id = id;
      Title = title;
   }
}
