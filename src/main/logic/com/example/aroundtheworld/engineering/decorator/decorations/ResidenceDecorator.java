package com.example.aroundtheworld.engineering.decorator.decorations;

import com.example.aroundtheworld.engineering.decorator.Quote;

public class ResidenceDecorator extends Decorator {
    int weeks;

    public ResidenceDecorator(Quote quote, int weeks) {
        super(quote);
        this.weeks = weeks;
    }

    @Override
    public int getPrice(){
        return super.getPrice()+(200 * weeks);
    }

}
