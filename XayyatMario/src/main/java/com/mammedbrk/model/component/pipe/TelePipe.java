package com.mammedbrk.model.component.pipe;

import com.mammedbrk.model.Section;

public class TelePipe extends Pipe {
    protected Section section;

    public TelePipe() {
    }

    public TelePipe(Section section) {
        this.section = section;
    }

    // Getters and setters

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
