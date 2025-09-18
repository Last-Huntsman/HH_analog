package ru.zyuzyukov.kurs_3_db.mapper;

import ru.zyuzyukov.kurs_3_db.dto.CRUDable;
import ru.zyuzyukov.kurs_3_db.db.entity.Entitytable;

public interface Mapper<T extends CRUDable,D extends Entitytable> {
     T toDto(D entity);
     D toCreateEntity(T dto);

}
