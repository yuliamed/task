package by.iba.mapper;

import by.iba.dto.req.AbstractReq;
import by.iba.dto.resp.AbstractResp;
import by.iba.entity.AbstractEntity;

import java.util.Objects;

public abstract class FullAbstractMapper<E extends AbstractEntity, P extends AbstractResp, Q extends AbstractReq>
        extends SimpleAbstractMapper<E, P>
        implements FullObjectMapper<E, P, Q> {

    private final Class<E> entity;

    public FullAbstractMapper(Class<E> entity, Class<P> dto) {
        super(entity, dto);
        this.entity = entity;
    }

    @Override
    public E toEntityFromReq(Q r) {
        return Objects.isNull(r)
                ? null
                : mapper.map(r, entity);
    }
}
