
package model.dao;

import entities.Fornecedor;
import java.util.List;

public interface FornecedorDao {
    void insert(Fornecedor obj);
	void update(Fornecedor obj);
	void deleteById(Integer id);
	Fornecedor findById(Integer id);
	List<Fornecedor> findAll();
	
}
