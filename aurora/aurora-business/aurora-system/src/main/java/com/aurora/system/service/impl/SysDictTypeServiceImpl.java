package com.aurora.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.aurora.common.core.exception.ServiceException;
import com.aurora.common.core.utils.DictUtil;
import com.aurora.common.core.utils.StringUtil;
import com.aurora.system.constant.SystemConstants;
import com.aurora.system.domain.SysDictData;
import com.aurora.system.domain.SysDictType;
import com.aurora.system.mapper.SysDictDataMapper;
import com.aurora.system.mapper.SysDictTypeMapper;
import com.aurora.system.service.SysDictDataService;
import com.aurora.system.service.SysDictTypeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
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
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType> implements SysDictTypeService {

    @Autowired
    private SysDictDataService dictDataService;

    @Autowired
    private SysDictDataMapper dictDataMapper;

    /**
     * 项目启动时，初始化字典到缓存
     */
    @PostConstruct
    public void init() {
        loadingDictCache();
    }

    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeList(SysDictType dictType) {
        return list(new QueryWrapper<>(dictType));
    }

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeAll() {
        return list();
    }

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataByType(String dictType) {
        List<com.aurora.common.core.utils.domain.SysDictData> dictDatas = DictUtil.getDictCache(dictType);
        if (StringUtil.isNotEmpty(dictDatas)) {
            return dictDataConversion(dictDatas);
        }

        List<SysDictData> dictData = dictDataMapper.selectList(new LambdaQueryWrapper<SysDictData>().eq(SysDictData::getDictType, dictType));
        if (StringUtil.isNotEmpty(dictData)) {
            DictUtil.setDictCache(dictType, dictDataConver(dictData));
            return dictData;
        }
        return null;
    }

    /**
     * 根据字典类型ID查询信息
     *
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    @Override
    public SysDictType selectDictTypeById(Long dictId) {
        return getById(dictId);
    }

    /**
     * 根据字典类型查询信息
     *
     * @param dictType 字典类型
     * @return 字典类型
     */
    @Override
    public SysDictType selectDictTypeByType(String dictType) {
        return getOne(new LambdaQueryWrapper<SysDictType>().eq(SysDictType::getDictType, dictType));
    }

    /**
     * 批量删除字典类型信息
     *
     * @param dictIds 需要删除的字典ID
     * @return 结果
     */
    @Override
    public void deleteDictTypeByIds(Long[] dictIds) {
        for (Long dictId : dictIds) {
            SysDictType dictType = selectDictTypeById(dictId);
            int count = dictDataService.count(new LambdaQueryWrapper<SysDictData>().eq(SysDictData::getDictType,
                    dictType.getDictType()));
            if (count > 0) {
                throw new ServiceException(String.format("%1$s已分配,不能删除", dictType.getDictName()));
            }
            removeById(dictId);
            DictUtil.removeDictCache(dictType.getDictType());
        }
    }

    /**
     * 加载字典缓存数据
     */
    @Override
    public void loadingDictCache() {
        List<SysDictType> dictTypeList = list();
        for (SysDictType dictType : dictTypeList) {
            List<SysDictData> dictDatas =
                    dictDataService.list(new LambdaQueryWrapper<SysDictData>().eq(SysDictData::getDictType,
                            dictType.getDictType()));
            DictUtil.setDictCache(dictType.getDictType(), dictDataConver(dictDatas));
        }
    }

    /**
     * 清空字典缓存数据
     */
    @Override
    public void clearDictCache() {
        DictUtil.clearDictCache();
    }

    /**
     * 重置字典缓存数据
     */
    @Override
    public void resetDictCache() {
        clearDictCache();
        loadingDictCache();
    }

    /**
     * 新增保存字典类型信息
     *
     * @param dict 字典类型信息
     * @return 结果
     */
    @Override
    public boolean insertDictType(SysDictType dict) {
        boolean flag = save(dict);
        if (flag) {
            DictUtil.setDictCache(dict.getDictType(), null);
        }
        return flag;
    }

    /**
     * 修改保存字典类型信息
     *
     * @param dict 字典类型信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean updateDictType(SysDictType dict) {
        SysDictType oldDict = getById(dict.getDictId());
        dictDataMapper.updateDictDataType(oldDict.getDictType(), dict.getDictType());
        boolean flag = updateById(dict);
        if (flag) {
            List<SysDictData> dictDatas =
                    dictDataService.list(new LambdaQueryWrapper<SysDictData>().eq(SysDictData::getDictType, dict.getDictType()));
            DictUtil.setDictCache(dict.getDictType(), dictDataConver(dictDatas));
        }
        return flag;
    }

    /**
     * 校验字典类型称是否唯一
     *
     * @param dict 字典类型
     * @return 结果
     */
    @Override
    public String checkDictTypeUnique(SysDictType dict) {
        Long dictId = StringUtil.isNull(dict.getDictId()) ? -1L : dict.getDictId();
        SysDictType dictType = getOne(new LambdaQueryWrapper<SysDictType>().eq(SysDictType::getDictType, dict.getDictType()));
        if (StringUtil.isNotNull(dictType) && dictType.getDictId().longValue() != dictId.longValue()) {
            return SystemConstants.NOT_UNIQUE;
        }
        return SystemConstants.UNIQUE;
    }

    public List<SysDictData> dictDataConversion(List<com.aurora.common.core.utils.domain.SysDictData> dictData) {
        List<SysDictData> dictDatas = Lists.newArrayList();
        BeanUtil.copyProperties(dictData, dictDatas);
        return dictDatas;
    }

    public List<com.aurora.common.core.utils.domain.SysDictData> dictDataConver(List<SysDictData> dictData) {
        List<com.aurora.common.core.utils.domain.SysDictData> dictDatas = Lists.newArrayList();
        BeanUtil.copyProperties(dictDatas, dictData);
        return dictDatas;
    }

}
