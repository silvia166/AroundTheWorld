package com.example.aroundtheworld.engineering.decorator.decorations;

import com.example.aroundtheworld.engineering.decorator.Quote;

public class CentralParkDecorator extends Decorator{
    public CentralParkDecorator(Quote quote) {
        super(quote);
    }

    @Override
    public int getPrice(){
        return super.getPrice()+5;
    }
}
