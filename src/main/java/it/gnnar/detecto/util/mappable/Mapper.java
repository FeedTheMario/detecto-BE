package it.gnnar.detecto.util.mappable;

import java.util.List;

public interface Mapper<DTOClass, EntityClass> {

    public DTOClass toDTO(EntityClass document);
    public List<DTOClass> toDTO(List<EntityClass> document);
    public EntityClass toDocument(DTOClass dto);
    public List<EntityClass> toDocument(List<DTOClass> dto);

}
