package ts24.com.vn.dal.dao;

import java.util.List;

import ts24.com.vn.dal.model.HddtTracuu;

public interface HddtTracuuDao {

	HddtTracuu save(HddtTracuu obj);

	List<HddtTracuu> searchHddtTracuu(String tkNhan, String maNhanHD) throws Exception;

}
