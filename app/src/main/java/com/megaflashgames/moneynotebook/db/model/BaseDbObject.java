package com.megaflashgames.moneynotebook.db.model;

import com.megaflashgames.moneynotebook.db.DatabaseHelper;

import java.io.Serializable;

//TODO: make this object protected or default
public class BaseDbObject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(DatabaseHelper.COMMON_ID)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public boolean hasId() {
        return id != null && id != Long.MIN_VALUE;
    }
}
