package com.mammedbrk.model.component.pipe;

import com.mammedbrk.model.game.Section;

public abstract class TelePipe extends Pipe {
    protected Section section;

    public TelePipe() {
    }

    public TelePipe(double x, double y, Section section) {
        super(x, y);
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
