package com.example.Digital.Art.Design.Rashu.EntityClasses;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Rashu_WorksForCompositeKey implements Serializable {
    private Long designer;
    private int category;

    public Rashu_WorksForCompositeKey() {
        // Default no-argument constructor
    }

    public Rashu_WorksForCompositeKey(Long designer, int category) {
        this.designer = designer;
        this.category = category;
    }

    public Long getDesigner() {
        return designer;
    }

    public void setDesigner(Long designer) {
        this.designer = designer;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
    @Override
    public int hashCode() {
        return Objects.hash(designer, category);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rashu_WorksForCompositeKey that = (Rashu_WorksForCompositeKey) o;
        return Objects.equals(designer, that.designer) &&
                Objects.equals(category, that.category);
    }

}
