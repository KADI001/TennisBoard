package org.kadirov.dto;

import org.kadirov.util.PageUtil;

import java.util.List;

public record Pagination<T> (
        int from,
        int to,
        int size,
        int amount,
        int maxAmount,
        List<T> data
) {

    public List<T> getAtPage(int page){
        int offset = PageUtil.clamp((page - 1) * size, 0, (to - 1) * size);
        return data.stream().skip(offset).limit(size).toList();
    }
}
