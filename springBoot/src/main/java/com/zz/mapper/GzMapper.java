package com.zz.mapper;

import org.apache.ibatis.annotations.Insert;
import com.zz.model.*;

/**
 * Created by dell on 2017/10/21.
 */
public interface GzMapper {
    @Insert({
            "insert into day (riqi,vlues) values (#{riqi,jdbcType=VARCHAR},#{vlues,jdbcType=VARCHAR})"
    })
    int intsert(Gz gz);
}
