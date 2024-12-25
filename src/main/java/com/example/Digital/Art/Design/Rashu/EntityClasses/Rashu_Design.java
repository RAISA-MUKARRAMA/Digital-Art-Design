package com.example.Digital.Art.Design.Rashu.EntityClasses;
import jakarta.persistence.*;

@Entity
@Table(name="design")
public class Rashu_Design {
    @Id
    @Column(name = "Design_Code")
    private Long designCode;

    @Column(name = "Design_Title")
    private String title;

    @Column(name = "Description")
    private String description;
    @Lob
    @Column(name="Content" , columnDefinition = "LONGBLOB")
    private byte[]  content;
    @Column(name = "rating")
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "Designer_Id", referencedColumnName = "designer_id")
    private Rashu_Designer designer;

    @ManyToOne
    @JoinColumn(name = "Ctgr_Code", referencedColumnName = "Ctgr_Code")
    private Rashu_Category category;


    public Long getDesignCode() {
        return designCode;
    }

    public void setDesignCode(Long designCode) {
        this.designCode = designCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Rashu_Designer getDesigner() {
        return designer;
    }

    public void setDesigner(Rashu_Designer designer) {
        this.designer = designer;
    }

    public Rashu_Category getCategory() {
        return category;
    }

    public void setCategory(Rashu_Category category) {
        this.category = category;
    }
}
