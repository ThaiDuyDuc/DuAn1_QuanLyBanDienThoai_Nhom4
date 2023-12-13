/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public interface DAOInterface <E, K>{
    public ArrayList<E> getAll();
    public int insert(E e);
    public int update(E e);
    public int delete(K k);
    public E getByID(K k);
}
