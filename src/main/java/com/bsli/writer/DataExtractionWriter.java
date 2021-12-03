package com.bsli.writer;


import org.springframework.batch.item.support.AbstractItemStreamItemWriter;

import java.util.List;

public class DataExtractionWriter extends AbstractItemStreamItemWriter {
    @Override
    public void write(List items) throws Exception {
        items.stream().forEach(System.out::println);
        System.out.println(" ************ writing each chunck ***********");
    }
}

