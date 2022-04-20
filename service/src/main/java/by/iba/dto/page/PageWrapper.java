package by.iba.dto.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@Getter
@Setter
public class PageWrapper <T>{
    private List<T> objects;
    private int totalPages;
    private long totalElements;
    private int page;
    private int elementsCount;

    public static <T> PageWrapper<T> of(List<T> objects,
                                        int totalPages,
                                        long totalElements,
                                        int page,
                                        int elementsCount) {
        return new PageWrapper<>(objects, totalPages, totalElements, page, elementsCount);
    }
}
