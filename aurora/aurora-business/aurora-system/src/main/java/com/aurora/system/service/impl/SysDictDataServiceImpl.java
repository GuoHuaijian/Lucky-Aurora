package com.aurora.system.service.impl;

import com.aurora.system.common.util.DictUtil;
import com.aurora.system.domain.SysDictData;
import com.aurora.system.mapper.SysDictDataMapper;
import com.aurora.system.service.SysDictDataService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/10/10
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Service
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData> implements SysDictDataService {

    @Autowired
    private SysDictDataMapper dictDataMapper;

    /**
     * 根据条件分页查询字典数据
     *
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataList(SysDictData dictData) {
        return list(new QueryWrapper<>(dictData));
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    @Override
    public String selectDictLabel(String dictType, String dictValue) {
        LambdaQueryWrapper<SysDictData> lambdaQueryWrapper = new LambdaQueryWrapper<SysDictData>()
                .eq(SysDictData::getDictType, dictType)
                .eq(SysDictData::getDictValue, dictValue);
        return getOne(lambdaQueryWrapper).getDictLabel();
    }

    /**
     * 根据字典数据ID查询信息
     *
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    @Override
    public SysDictData selectDictDataById(Long dictCode) {
        return getById(dictCode);
    }

    /**
     * 批量删除字典数据信息
     *
     * @param dictCodes 需要删除的字典数据ID
     * @return 结果
     */
    @Override
    public void deleteDictDataByIds(Long[] dictCodes) {
        for (Long dictCode : dictCodes) {
            SysDictData data = selectDictDataById(dictCode);
            dictDataMapper.deleteById(dictCode);
            DictUtil.setDictCache(data.getDictType(), selectDictDataByType(data.getDictType()));
        }
    }

    /**
     * 新增保存字典数据信息
     *
     * @param data 字典数据信息
     * @return 结果
     */
    @Override
    public boolean insertDictData(SysDictData data) {
        boolean flag = save(data);
        if (flag) {
            DictUtil.setDictCache(data.getDictType(), selectDictDataByType(data.getDictType()));
        }
        return flag;
    }

    /**
     * 修改保存字典数据信息
     *
     * @param data 字典数据信息
     * @return 结果
     */
    @Override
    public boolean updateDictData(SysDictData data) {
        boolean flag = updateById(data);
        if (flag) {
            DictUtil.setDictCache(data.getDictType(), selectDictDataByType(data.getDictType()));
        }
        return flag;
    }

    /**
     * 根据字典类型查询字典数据
     *
     * @param DictType
     * @return
     */
    public List<SysDictData> selectDictDataByType(String DictType) {
        List<SysDictData> sysDictData =
                list(new LambdaQueryWrapper<SysDictData>().eq(SysDictData::getDictType, DictType));
        return sysDictData;
    }
}
