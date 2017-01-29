package com.cadnunsdev.direccionescongespanhol.persistence.contracts;

import java.util.ArrayList;

/**
 * Created by Tiago Silva on 19/01/2017.
 */

public interface PersistenceBase<Entity> {
    void save(Entity entity);
    ArrayList<Entity> list();
    ArrayList<Entity> list(WhereSelector<Entity> whereExp);

    public interface WhereSelector<Entity>{
        boolean q(Entity entity);
    }
}
