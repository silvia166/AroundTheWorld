package com.example.aroundtheworld.engineering.decorator.decorations;

import com.example.aroundtheworld.engineering.decorator.Quote;

public class LondonEyeDecorator extends Decorator{
    public LondonEyeDecorator(Quote quote) {
        super(quote);
    }

    @Override
    public int getPrice(){
        return super.getPrice()+30;
    }
}
