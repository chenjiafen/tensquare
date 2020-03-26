package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private LabelService labelService;

    /**
     * 查询全部
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<Label> labelList = labelService.findAll();
        return new Result(StatusCode.OK, true, "查询成功", labelList);
    }

    /**
     * 根据ID查询标签
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {

        Label label = labelService.findById(id);
        return new Result(StatusCode.OK, true, "查询成功", label);
    }

    /**
     * 保存
     *
     * @param label
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(Label label) {
        labelService.add(label);
        return new Result(StatusCode.OK, true, "保存成功");
    }

    /**
     * 根据参数查询
     *
     * @param label
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findByCondition(@RequestBody Label label) {
        List<Label> labelList = labelService.findByCondition(label);
        return new Result(StatusCode.OK, true, "保存成功", labelList);
    }

    @RequestMapping(value = "/search/{page}/{size}")
    public Result findPageByCondition(@RequestBody Label label, @PathVariable Integer page, @PathVariable Integer size) {
        PageResult<Label> pageRequest = labelService.findPageByCondition(label, page, size);
        return new Result(StatusCode.OK, true, "保存成功", pageRequest);
    }
}
