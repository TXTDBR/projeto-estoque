
package model.dao;

import db.DB;
import model.dao.implement.FornecedorDaoJDBC;

public class DaoFactory {
    public static FornecedorDaoJDBC createFornecedorDao(){
        return new FornecedorDaoJDBC(DB.getConnection());
    }
}
