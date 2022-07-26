package by.iba.mapper.common;

import by.iba.dto.AbstractDTO;
import by.iba.entity.AbstractEntity;

import java.util.List;
import java.util.Set;

public interface SimpleObjectMapper<E extends AbstractEntity, D extends AbstractDTO> {
    E toEntity(final D d);

    D toDto(final E e);

    List<D> toDtoList(final List<E> eList);

    Set<E> toEntitySet(Set<D> dtoSet);
}
