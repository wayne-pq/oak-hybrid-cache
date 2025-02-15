package oakHybridCacheWithDBData.mapper;

import oakHybridCacheWithDBData.bean.UTaskDetailDO;

/**
 * @author qian.pan on 2024/7/4.
 */
public interface UTaskDetailDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UTaskDetailDO record);

    int insertSelective(UTaskDetailDO record);

    UTaskDetailDO selectByPrimaryKey(Long id);

    int selectByTaskCode(Long userId);

    int updateByPrimaryKeySelective(UTaskDetailDO record);

    int updateByPrimaryKey(UTaskDetailDO record);
}