/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;
import java.util.List;

public abstract class EduSysDao<E, K> {
    public abstract void insert(E entily);
    public abstract void update(E entily);
    public abstract void delete(K id);
    
    public abstract List<E> selectAll();
    public abstract E select_id(K id);
    public abstract List<E> select_sql(String sql, Object... args);
}
