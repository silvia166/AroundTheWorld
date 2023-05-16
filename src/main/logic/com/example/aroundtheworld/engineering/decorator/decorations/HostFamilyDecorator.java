package com.example.aroundtheworld.engineering.decorator.decorations;

import com.example.aroundtheworld.engineering.decorator.Quote;

public class HostFamilyDecorator extends Decorator {
    int weeks;

    public HostFamilyDecorator(Quote quote, int weeks) {
        super(quote);
        this.weeks = weeks;

    }
    @Override
    public int getPrice(){
        return super.getPrice()+(150 * weeks);
    }
}
