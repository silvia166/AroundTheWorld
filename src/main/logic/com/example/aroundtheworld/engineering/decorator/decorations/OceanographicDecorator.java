package com.example.aroundtheworld.engineering.decorator.decorations;

import com.example.aroundtheworld.engineering.decorator.Quote;

public class OceanographicDecorator extends Decorator {
    public OceanographicDecorator(Quote quote) {
        super(quote);
    }

    @Override
    public int getPrice(){
        return super.getPrice()+40;
    }
}

