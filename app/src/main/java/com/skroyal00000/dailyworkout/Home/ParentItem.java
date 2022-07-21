package com.skroyal00000.dailyworkout.Home;

import java.util.List;

public class ParentItem {

   String Title;
   private List<ChildItem> ChildItemList;

   public ParentItem() {
   }

   public ParentItem(String title, List<ChildItem> childItemList) {
      Title = title;
      ChildItemList = childItemList;
   }

   public String getTitle() {
      return Title;
   }

   public void setTitle(String title) {
      Title = title;
   }

   public List<ChildItem> getChildItemList() {
      return ChildItemList;
   }

   public void setChildItemList(List<ChildItem> childItemList) {
      ChildItemList = childItemList;
   }
}
