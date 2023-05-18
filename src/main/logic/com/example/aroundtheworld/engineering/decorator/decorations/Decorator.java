package com.example.aroundtheworld.engineering.decorator.decorations;

import com.example.aroundtheworld.engineering.decorator.Quote;

public abstract class Decorator extends Quote {

    protected Quote quote;
    protected Decorator(Quote quote){
        this.quote = quote;
    }
    @Override
    public int getPrice() {
        return quote.getPrice();
    }
}
