package com.${packageName}.controller;

import com.github.pagehelper.PageInfo;
import com.star.commons.support.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.${packageName}.core.service.DemoService;
import com.${packageName}.bean.bo.DemoBo;
import com.${packageName}.bean.DO.DemoDo;
import java.util.List;

/**
 * demo Controller 就是一些规范
 * produces	For example, "application/json, application/xml"
 * consumes	For example, "application/json, application/xml"
 * protocols	Possible values: http, https, ws, wss.
 * hidden	配置为true 将在文档中隐藏
 * tags	如果设置这个值、value的值会被覆盖
 * authorizations	高级特性认证时配置
 */
@Slf4j
@RestController
@RequestMapping("/demo")
@Api(value = "demo API",tags = {"demo api操作接口"}, protocols = "http",consumes = "",produces = "")
public class DemoController {

    @Autowired
    public DemoService demoService;

    @ApiOperation(value = "新增数据", notes = "新增数据 api")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "demoDo", value = "新增对象实体",  required = true, dataType = "DemoDo", paramType = "body")
    })
    @PostMapping("/v1/add")
    public ResponseResult<Void> add(@RequestBody DemoDo demoDo){
        demoService.insertSelective(demoDo);
        return ResponseResult.success();
    }

    @ApiOperation(value = "修改数据", notes = "修改数据 api")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "demoDo", value = "修改对象实体", required = true, dataType = "DemoDo", paramType = "body")
    })
    @PostMapping(value = "/v1/update")
    public ResponseResult<Void> update(@RequestBody DemoDo demoDo){
        demoService.updateByPrimaryKeySelective(demoDo);
        return ResponseResult.success();
    }

    @ApiOperation(value="通过id删除数据", notes="通过id删除数据 api")
    @ApiImplicitParam(name = "id",  value = "删除实体的id", required = true, dataType = "Long", paramType="path")
    @DeleteMapping("/v1/delete/{id}")
    public ResponseResult<Void> delete(@PathVariable("id") Long id){
        demoService.deleteByPrimaryKey(id);
        return ResponseResult.success();
    }

    @ApiOperation(value="通过id查询详情信息", notes="通过id查询详情信息 api")
    @ApiImplicitParam(name = "id",  value = "查询实体的id", required = true, dataType = "Long", paramType="path")
    @GetMapping("/v1/get/{id}")
    public ResponseResult<DemoDo> get(@PathVariable("id") Long id){
        DemoDo result= demoService.selectByPrimaryKey(id);
        return ResponseResult.success(result);
    }

    @ApiOperation(value = "通过条件查询列表", notes = "通过条件查询列表 api")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "demoBo", value = "修改对象实体", required = true, dataType = "DemoBo", paramType = "body")
    })
    @GetMapping(value = "/v1/list")
    public ResponseResult<List<DemoDo>> list(@RequestBody @Valid DemoBo demoBo){
        List<DemoDo> list = demoService.selectByPrimaryList(demoBo);
        return ResponseResult.success(list);
    }

    @ApiOperation(value = "通过条件查询分页数据", notes = "通过条件查询分页数据 api")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "demoBo", value = "查询条件实体", required = true, dataType = "DemoBo", paramType = "body"),
        @ApiImplicitParam(name = "pageIndex", value = "第几页", required = true, dataType = "Integer", paramType = "query"),
        @ApiImplicitParam(name = "pageSize", value = "每页多少条", required = true, dataType = "Integer", paramType = "query")
    })
    @GetMapping(value = "/v1/listByPage")
    public ResponseResult<PageInfo<DemoDo>> listByPage(@RequestBody @Valid DemoBo demoBo,
                                                       @RequestParam("pageIndex") Integer pageIndex,
                                                       @RequestParam("pageSize") Integer pageSize){
        PageInfo<DemoDo> pageInfo = demoService.selectByPrimaryPage(demoBo,pageIndex,pageSize);
        return ResponseResult.success(pageInfo);
    }
}