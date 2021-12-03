package com.bsli.processor;

import org.springframework.stereotype.Component;

import org.springframework.batch.item.ItemProcessor;


@Component
public class DataExtractionProcessor implements ItemProcessor<Integer, Integer> {
    @Override
    public Integer process(Integer item) throws Exception {
        return Integer.sum(10,item);
    }
}

