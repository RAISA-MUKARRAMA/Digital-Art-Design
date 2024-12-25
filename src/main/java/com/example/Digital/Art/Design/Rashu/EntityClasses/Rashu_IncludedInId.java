package com.example.Digital.Art.Design.Rashu.EntityClasses;
import java.io.Serializable;
import java.util.Objects;

public class Rashu_IncludedInId implements Serializable {
    private Long contest; // Corresponds to the Contest foreign key
    private Long design;  // Corresponds to the Design foreign key

    public Rashu_IncludedInId() {
    }

    public Rashu_IncludedInId(Long contest, Long design) {
        this.contest = contest;
        this.design = design;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rashu_IncludedInId that = (Rashu_IncludedInId) o;
        return Objects.equals(contest, that.contest) && Objects.equals(design, that.design);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contest, design);
    }
}
