package model.dao.implement;

import db.DB;
import db.DbException;
import entities.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import model.dao.ProdutoDao;

public class ProdutoDaoJDBC implements ProdutoDao {

    private Connection conn = null;

    public ProdutoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Produto obj) {
        PreparedStatement st = null;
        
        String sql = "INSERT INTO seller \r\n" + "(nome, preco, qtd, produtoId)  \r\n"
                + "VALUES  \r\n" + "(?, ?, ?, ?) ";
        try {
            st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getNome());
            st.setDouble(2, obj.getPreco());
            st.setInt(3, obj.getQtd());
            st.setInt(4, obj.getFornecedor().getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Erro inesperado! Nenhuma linha afetada!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Produto obj) {
        PreparedStatement st = null;
        String sql = "UPDATE produto  \r\n"
                + "SET nome = ?, preco = ?, qtd = ?, FornecedorId = ? \r\n" + "WHERE Id = ? ";
        try{
            st = conn.prepareCall(sql);
            st.setString(1, obj.getNome());
            st.setDouble(2, obj.getPreco());
            st.setInt(3, obj.getQtd());
            st.setInt(4, obj.getFornecedor().getId());
            st.setInt(5, obj.getId());
            
            st.executeUpdate();
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        String sql = "DELETE FROM produto WHERE id = ?";
        try {
            st = conn.prepareCall(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Produto findById(Integer id) {
        return null;

    }

    @Override
    public List<Produto> findAll() {
        return null;

    }

}
