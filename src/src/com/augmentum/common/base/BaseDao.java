package com.augmentum.common.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public abstract class BaseDao<T, K> extends SqlSessionDaoSupport {

    protected static String SQL_ID_ADD = ".add";
    protected static String SQL_ID_UPDATE = ".update";
    protected static String SQL_ID_DELETE = ".delete";
    protected static String SQL_ID_BY_ID = ".getById";
    //private static final String KEY_OFFSET = "offset";
    //private static final String KEY_ROW_COUNT = "rowCount";

    private Class<T> clz;

    @SuppressWarnings("unchecked")
    protected Class<T> getClz() {
        if (clz == null)
            clz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return clz;
    }

    public Boolean add(T obj) {
        getSqlSession().insert(this.getClz().getName() + SQL_ID_ADD, obj);
        return Boolean.TRUE;
    }

    public Boolean delete(K id) {
        getSqlSession().delete(this.getClz().getName() + SQL_ID_DELETE, id);
        return Boolean.TRUE;
    }

    public Boolean update(T obj) {
        getSqlSession().update(this.getClz().getName() + SQL_ID_UPDATE, obj);
        return Boolean.TRUE;
    }

    @SuppressWarnings("unchecked")
    public T getById(K id) {
        return (T) getSqlSession().selectOne(this.getClz().getName() + SQL_ID_BY_ID, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findByQueryId(String sqlId, Object parameters) {
        return (List<T>) getSqlSession().selectList(this.getClz().getName() + sqlId, parameters);
    }

//    protected Map<String, Object> getParameterMap() {
//        Map<String, Object> parameterMap = null;
//        PaginationDTO<?> paginationDTO = (PaginationDTO<?>) AppContext.getContext().getObject(
//                AppConstants.PAGINATION_DTO);
//        if (paginationDTO == null) {
//            // don't need to pagination
//            parameterMap = new HashMap<String, Object>();
//        } else {
//            parameterMap = paginationDTO.getParameterMap();
//            parameterMap.put(KEY_OFFSET, paginationDTO.getOffset());
//            parameterMap.put(KEY_ROW_COUNT, paginationDTO.getRowCount());
//        }
//        return parameterMap;
//    }
}