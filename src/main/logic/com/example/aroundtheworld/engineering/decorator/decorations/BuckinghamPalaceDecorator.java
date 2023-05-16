package com.example.aroundtheworld.engineering.decorator.decorations;

import com.example.aroundtheworld.engineering.decorator.Quote;

public class BuckinghamPalaceDecorator extends Decorator{
    public BuckinghamPalaceDecorator(Quote quote) {
        super(quote);
    }

    @Override
    public int getPrice(){
        return super.getPrice()+90;
    }
}
