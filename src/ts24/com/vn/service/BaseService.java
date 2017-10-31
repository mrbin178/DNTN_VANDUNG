package ts24.com.vn.service;

import java.util.List;

public interface BaseService<T> {
   public List<T> getList(Class<T> clazz) throws Exception;
}
