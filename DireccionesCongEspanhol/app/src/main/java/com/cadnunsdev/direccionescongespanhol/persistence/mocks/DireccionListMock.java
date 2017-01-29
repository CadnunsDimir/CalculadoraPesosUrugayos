package com.cadnunsdev.direccionescongespanhol.persistence.mocks;

import com.cadnunsdev.direccionescongespanhol.persistence.contracts.DireccionPersistenceContract;
import com.cadnunsdev.direccionescongespanhol.persistence.contracts.PersistenceBase;
import com.cadnunsdev.direccionescongespanhol.persistence.models.Direccion;

import java.util.ArrayList;

/**
 * Created by Tiago Silva on 19/01/2017.
 */

public class DireccionListMock implements DireccionPersistenceContract {
    private static ArrayList<Direccion> lista = new ArrayList<>();

    @Override
    public void save(Direccion direccion) {
        if(direccion.getId() == 0){
            int id = generateId();
            lista.add(direccion);
        }else {
            for (int x= 0; x < lista.size();x++){
                Direccion item = lista.get(x);
                if(item.getId() == direccion.getId())
                    lista.set(x,direccion);
            }
        }
    }

    private int generateId() {
        int id = 1;
        for (int x= 0; x < lista.size();x++){
            Direccion item = lista.get(x);
            if(item.getId() > id)
                id = item.getId();
        }
        return id;
    }

    @Override
    public ArrayList<Direccion> list() {
        return lista;
    }

    @Override
    public ArrayList<Direccion> list(WhereSelector<Direccion> whereExp) {
        ArrayList<Direccion> filtred = new ArrayList<>();
        for (int x= 0; x < lista.size();x++){
            Direccion item = lista.get(x);
            if(whereExp.q(item))
                filtred.add(item);
        }
        return filtred;
    }
}
