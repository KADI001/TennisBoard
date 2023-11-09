package org.kadirov.mapper;

public interface Mapper<TSource, TMapped> {
    TMapped map(TSource source) throws RuntimeException;
}
