package by.iba.mapper;

import by.iba.dto.AbstractDTO;
import by.iba.entity.AbstractEntity;

import java.util.List;

public interface SimpleObjectMapper <E extends AbstractEntity, D extends AbstractDTO>{
    E toEntity(final D d);

    D toDto(final E e);

    List<D> toDtoList(final List<E> eList);
}
