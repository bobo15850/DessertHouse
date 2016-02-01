package edu.nju.desserthouse.dao;

import java.util.List;

import org.hibernate.Session;

import edu.nju.desserthouse.util.ResultMessage;

public interface BaseDao {
	public Session getSession();

	public Session getNewSession();

	public ResultMessage flush();

	public ResultMessage clear();

	public ResultMessage save(Object bean);// 增加

	public ResultMessage delete(Object bean);// 删除

	public ResultMessage delete(Class<?> c, int id);// 删除

	public ResultMessage update(Object bean);// 修改

	// 以下为各种形式的查找
	public <T> T get(Class<T> c, int id);

	public <T> T load(Class<T> c, int id);

	public Long getTotalCount(Class<?> c);

	public <T> List<T> getAllList(Class<T> c);

	public <T> List<T> findByColumns(Class<T> c, String[] columns, Object[] values);
}
