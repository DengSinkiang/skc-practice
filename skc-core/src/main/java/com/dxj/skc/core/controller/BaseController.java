package com.dxj.skc.core.controller;

import com.dxj.skc.common.domain.vo.PageVo;
import com.dxj.skc.common.util.PageUtil;
import com.dxj.skc.common.util.ResultUtils;
import com.dxj.skc.core.service.BaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author sinkiang
 * @date 2022/4/7 10:32
 */
public abstract class BaseController<E, ID extends Serializable> {

    /**
     * 获取service
     *
     * @return /
     */
    public abstract BaseService<E, ID> getService();

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "通过 id 获取")
    public ResultUtils<E> getById(@PathVariable("id") ID id) {

        E entity = getService().getById(id);
        return ResultUtils.success(entity);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取全部数据")
    public ResultUtils<List<E>> getAll() {

        List<E> list = getService().getAll();
        return ResultUtils.success(list);
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页获取")
    public ResultUtils<Page<E>> getByPage(@ModelAttribute PageVo page) {

        Page<E> data = getService().findAll(PageUtil.initPage(page));
        return ResultUtils.success(data);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存数据")
    public ResultUtils<E> save(@ModelAttribute E entity) {

        E e = getService().save(entity);
        return ResultUtils.success(e);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "更新数据")
    public ResultUtils<E> update(@ModelAttribute E entity) {

        E e = getService().update(entity);
        return ResultUtils.success(e);
    }

    @RequestMapping(value = "/delByIds/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "批量通过 id 删除")
    public ResultUtils<E> delAllByIds(@PathVariable("ids") ID[] ids) {

        for (ID id : ids) {
            getService().delete(id);
        }
        return ResultUtils.success(null, "批量通过 id 删除数据成功");
    }
}

