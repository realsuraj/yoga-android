package com.skroyal00000.dailyworkout.Home;

import java.util.List;

public class ParentItem {

    // Declaration of the variables
    private String ParentItemTitle;
    private List<ChildItem> ChildItemList;
    public ParentItem(){}
    // Constructor of the class
    // to initialize the variables
    public ParentItem(
            String ParentItemTitle,
            List<ChildItem> ChildItemList)
    {

        this.ParentItemTitle = ParentItemTitle;
        this.ChildItemList = ChildItemList;
    }

    // Getter and Setter methods
    // for each parameter
    public String getParentItemTitle()
    {
        return ParentItemTitle;
    }

    public void setParentItemTitle(
            String parentItemTitle)
    {
        ParentItemTitle = parentItemTitle;
    }

    public List<ChildItem> getChildItemList()
    {
        return ChildItemList;
    }

    public void setChildItemList(
            List<ChildItem> childItemList)
    {
        this.ChildItemList = childItemList;
    }
}