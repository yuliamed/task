package by.iba.mapper;

import by.iba.dto.req.AbstractReq;
import by.iba.dto.resp.AbstractResp;
import by.iba.entity.AbstractEntity;

public interface FullObjectMapper<E extends AbstractEntity, RESP extends AbstractResp, REQ extends AbstractReq>
        extends SimpleObjectMapper<E, RESP> {
    E toEntityFromReq(final REQ r);

}
