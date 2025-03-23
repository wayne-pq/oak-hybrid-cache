package oakHybridCacheWithDBData.mapper;

import oakHybridCacheWithDBData.bean.UTaskProgressDO;
import oakHybridCacheWithDBData.dto.UTaskProgressDetailDTO;

import java.util.List;

/**
 * @author qian.pan on 2024/6/25.
 */
public interface UTaskProgressDOMapper {
    int deleteByPrimaryKey(String id);

    int insert(UTaskProgressDO record);

    int insertSelective(UTaskProgressDO record);

    UTaskProgressDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UTaskProgressDO record);

    int updateByPrimaryKey(UTaskProgressDO record);

    int insertBatch(List<UTaskProgressDO> record);

    int selectByUserId(Long userId);

    List<UTaskProgressDetailDTO> selectDetailByUserId(Long userId);

    int deleteByCreateTime();
}