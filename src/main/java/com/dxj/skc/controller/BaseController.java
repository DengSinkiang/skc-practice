package com.dxj.skc.controller;

import com.dxj.skc.domain.vo.PageVo;
import com.dxj.skc.domain.vo.Result;
import com.dxj.skc.service.BaseService;
import com.dxj.skc.util.PageUtil;
import com.dxj.skc.util.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: Sinkiang
 * @Date: 2020/3/27 15:49
 * @CopyRight: 2020 sk-admin all rights reserved.
 */
public abstract class BaseController<E, ID extends Serializable> {

    /**
     * 获取service
     *
     * @return
     */
    public abstract BaseService<E, ID> getService();

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "通过 id 获取")
    public Result<E> getById(@PathVariable("id") ID id) {

        E entity = getService().getById(id);
        return new ResultUtil<E>().setData(entity);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取全部数据")
    public Result<List<E>> getAll() {

        List<E> list = getService().getAll();
        return new ResultUtil<List<E>>().setData(list);
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页获取")
    public Result<Page<E>> getByPage(@ModelAttribute PageVo page) {

        Page<E> data = getService().findAll(PageUtil.initPage(page));
        return new ResultUtil<Page<E>>().setData(data);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存数据")
    public Result<E> save(@ModelAttribute E entity) {

        E e = getService().save(entity);
        return new ResultUtil<E>().setData(e);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "更新数据")
    public Result<E> update(@ModelAttribute E entity) {

        E e = getService().update(entity);
        return new ResultUtil<E>().setData(e);
    }

    @RequestMapping(value = "/delByIds/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "批量通过 id 删除")
    public Result<E> delAllByIds(@PathVariable("ids") ID[] ids) {

        for (ID id : ids) {
            getService().delete(id);
        }
        return new ResultUtil<E>().setSuccessMsg("批量通过 id 删除数据成功");
    }
}

