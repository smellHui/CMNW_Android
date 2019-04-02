package com.cmnw.model;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * Author:xch
 * Date:2019/4/2
 * Do:
 */
public class TabEntity implements CustomTabEntity {
    public String title;
    public int selectedIcon;

    public TabEntity(String title) {
        this.title = title;
    }

    public int unSelectedIcon;

    public TabEntity(String title, int selectedIcon, int unSelectedIcon) {
        this.title = title;
        this.selectedIcon = selectedIcon;
        this.unSelectedIcon = unSelectedIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectedIcon;
    }
}
