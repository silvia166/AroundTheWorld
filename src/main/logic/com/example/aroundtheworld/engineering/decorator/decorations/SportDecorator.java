package com.example.aroundtheworld.engineering.decorator.decorations;

import com.example.aroundtheworld.engineering.decorator.Quote;

public class SportDecorator extends Decorator {
    int weeks;

    public SportDecorator(Quote quote, int weeks) {
        super(quote);
        this.weeks = weeks;
    }

    @Override
    public int getPrice(){
        return super.getPrice()+(70 * weeks);
    }
}
