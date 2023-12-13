
package Repository;

import java.util.List;

public abstract class ServiceDAO<EntityType, KeyType> {
    public abstract void insert(EntityType x);
    public abstract void update(EntityType x);
    public abstract void delete(KeyType id);
    
    public abstract List<EntityType> selectAll();
    public abstract List<EntityType> select_Search(KeyType key);
    public abstract List<EntityType> selecBySQL(String sql, Object... args);
//    public abstract String search_Ma_LoadTable (List<EntityType> list, String keyword);
}
