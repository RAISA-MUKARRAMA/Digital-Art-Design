package com.example.Digital.Art.Design.Rashu.EntityClasses;

public class UpdateSelectionRequest {
        private Rashu_IncludedIn includedIn;
        private Integer selection;



        public Rashu_IncludedIn getIncludedIn() {
            return includedIn;
        }

        public void setIncludedIn(Rashu_IncludedIn includedIn) {
            this.includedIn = includedIn;
        }

        public Integer getSelection() {
            return selection;
        }

        public void setSelection(Integer selection) {
            this.selection = selection;
        }
}
